<?php

class UserData extends ObjectData {
  function newUser($post, $db) {


    if(!isset($post['username'])) {
    	die("ERR: Username not supplied");
    }
    if(!isset($post['password'])) {
    	die("ERR: Password not supplied");
    }
    if(!isset($post['object'])) {
    	die("ERR: User object not supplied");
    }
    if(!isset($post['email'])) {
    	die("ERR: User email not supplied");
    }
    if(!isset($post['uuid'])) {
    	die("ERR: UUID not supplied");
    }
    
    $sqluuid = $db->uuidStrToSql($post['uuid']);
    $username = $db->san($post['username']);
    $password = $db->san($post['password']);
    $object = $db->san($post['object']);
	$email = $db->san($post['email']);
	
	if(strstr($username, ',') !== FALSE) die ("ERR: Invalid character in username.");
    $query = "SELECT Users.Login, Objects.UUID, Users.UUID, Users.Email FROM Users, Objects WHERE ".
      "Users.Login = '$username' OR ". 
      "Objects.UUID = $sqluuid OR ".
      "Users.UUID = $sqluuid";

    if($db->oneRow($query)) {
      die("ERR: User or UUID already exists.");
    }

    if(strlen($password) < 6 ||
       strlen($password) > 64)
      die("ERR: Password length bad.");


    $query = "INSERT INTO Users (`Login`, `Password`, `UUID`, `Email`) VALUES (".
      "'$username', '$password', $sqluuid, '$email')";
    $db->squery($query);
    $query = "INSERT INTO Objects (`UUID`, `ownerUUID`, `Visibility`, `Object`) VALUES (".
      "$sqluuid, $sqluuid, 'private', '".$db->objectToSql($object)."')";
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
  
  function changePassword($post, $user, $db) {
  	$newpassword = $db->san($post['newpassword']);
  	$login = $user['Login'];
  	$query = "UPDATE Users SET Password='$newpassword' WHERE Login='$login'";
  	echo "$@$query@$";
  	$db->squery($query);
  	return "MSG: Password changed.";
  }
}
