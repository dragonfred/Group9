<?php


class ObjectData {
  /* 
     POST: UUID (in nice format), object
  */ 
  function getObjectPOST($post, $user, $db) {
    return trim($this->getObjectByUUID($user['UUID'], $db->uuidStrToBin($post['uuid']), $db));
  }

  function getObjectByUUID($userUUID, $objUUID, $db) {
    $obdata = $db->oneRow("SELECT * FROM Objects WHERE UUID=".
			  $db->uuidBinToSql($objUUID));
    $ownerUUID = $obdata['ownerUUID'];
    $visibility = $obdata['Visibility'];
    
    if($ownerUUID == $userUUID || $visibility == 'public') {
      return $db->sqlToObject($obdata['Object']);
    }
    
    else if($visibility == 'friends') {
      $friends = $db->oneRow("SELECT Friends FROM User WHERE UUID=".
			     $db->uuidBinToSql($ownerUUID));
      $friends = str_split($owner['Friends'],16);
      if(in_array($friends, $userUUID)) 
	return $db->sqlToObject($obdata['Object']);
      else {
	die("ERR: No access: Not a friend");
      }
    }

    else die("ERR: No access.");
  }

  function putObjectPOST($post, $user, $db) {
    if(!isset($post['uuid']) ||
       !isset($post['object']))
      die("ERR: Invalid putObject action.");
    if(!isset($post['visibility']))
      $post['visibility'] = 'private';

    $this->putObject($user['UUID'],
		     $db->uuidStrToBin($post['uuid']), 
		     $post['object'], 
		     $post['visibility'],
		     $db);
  }

  function putObject($userUUID, $UUID, $obj, $vis, $db) {
    $query = "SELECT UUID FROM Objects WHERE UUID=".
      $db->uuidBinToSql($UUID);
    $res = $db->squery($query);
    $count = $res->num_rows;
    $row = $res->fetch_assoc(); 
    if($row['ownerUUID'] == $userUUID) { //replace if user owns it
      $query="DELETE FROM Objects WHERE UUID=".
		 $db->uuidBinToSql($UUID);
      $db->squery($query);
    }
    if($count == 0) { // new object
      $query = "INSERT INTO Objects (`UUID`, `Object`, `ownerUUID`, `Visibility`) VALUES (".
		 $db->uuidBinToSql($UUID).', '.
		 "'".$db->objectToSql($obj)."', ".
		 $db->uuidBinToSql($userUUID).', '.
		 "'$vis')";
      $db->squery($query);
    }
  }

  function deleteObject($userUUID, $UUID, $db) {
    $db->squery("DELETE FROM Objects WHERE ".
	       "UUID=".$db->uuidBinToSql($UUID).' AND '.
	       "ownerUUID=".$db->uuidBinToSql($userUUID));
    // this is safe.	       
  }

  function deleteObjectPost($post, $user, $db) {
    $this->deleteObject($user['UUID'], $post['uuid'], $db);
  }

}
  
