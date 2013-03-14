<?php

class UserData extends ObjectData {
  function newUser($post, $db) {
    $sqluuid = $db->uuidStrToSql($post['uuid']);

    $username = $db->san($post['username']);
    $password = $db->san($post['password']);
    $object = $db->san($post['object']);

    $query = "SELECT Users.Login, Objects.UUID, Users.UUID FROM Users, Objects WHERE ".
      "Users.Login = '$username' OR ". 
      "Objects.UUID = $sqluuid OR ".
      "Users.UUID = $sqluuid";

    if($db->oneRow($query)) {
      die("ERR: User or UUID already exists.");
    }

    if(strlen($password) < 6 ||
       strlen($password) > 64)
      die("ERR: Password length bad.");


    $query = "INSERT INTO Users (`Login`, `Password`, `UUID`) VALUES (".
      "'$username', '$password', $sqluuid)";
    $db->squery($query);
    $query = "INSERT INTO Objects (`UUID`, `ownerUUID`, `Visibility`, `Object`) VALUES (".
      "$sqluuid, $sqluuid, '".$db->objectToSql($object)."', 'private')";
    $db->squery($query);
    return TRUE;
  }
  
  function getUser($post, $db) {
  	$username = $db->san($post['username']);
  	$password = $db->san($post['password']);

  	$query = "SELECT Users.Login, Users.Password, Objects.Object, Users.UUID from Users, Objects WHERE ".
    	"Users.Login = '$username' AND Objects.UUID = Users.UUID";
  	
  	if(($userdata = $db->oneRow($query)) === FALSE) {
  		die("ERR: Error retrieving user.");
  	}
  	
  	if($userdata['Password'] != $post['password']) {
  		die("ERR: Password incorrect.");
  	}
  	
  	return $db->sqlToObject($userdata['Object']);
  }
}
