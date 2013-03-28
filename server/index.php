<?php

error_reporting(E_ALL);
ini_set("display_errors", 1);
require('Database.php');
require('ObjectData.php');
require('UserData.php');
require('RestaurantData.php');
require('PWReset.php');
require('Friends.php');
require('cred.php');
$database = new Database($DB_HOST, $DB_USER, $DB_PASS, $DB_DB);

if(isset($_POST['pwreset'])) {
  $pwreset = new PWReset();
  $pwreset->sendResetEmail($_POST, $database);
  exit();
}

if(isset($_GET['pwreset'])) {
  $pwreset = new PWReset();
  $pwreset->validateResetRequest($_GET, $database);
  exit();
}

if(isset($_GET['genUUID'])) {
  $binUuid = $database->genUuidBin();
  echo("<pre>\nUUID sql:\n".$database->uuidBinToSql($binUuid).
       "\nUUID str:\n".$database->uuidBinToStr($binUuid)."\n</pre>");
  exit();
}

if(!isset($_POST['username']) || !isset($_POST['password'])) {
  die("ERR: no login");
}



if(!isset($_POST['action'])) {
  die("ERR: Must specify action");
}

if(isset($_POST['uuid'])) 
  if(!preg_match('/[0-9a-fA-F-]{32,36}/', $_POST['uuid']) ||
     preg_match('/[^0-9a-fA-F-]/', $_POST['uuid']) ||
     preg_match('/.*-.*-.*-.*-.*-.*/', $_POST['uuid']))
    die("ERR: Invalid UUID");


if($_POST['action'] == 'newUser') {
  $userdata = new UserData();
  if($userdata->newUser($_POST, $database))
    echo "MSG: User created.";
  else
    echo "ERR: User not created.";
  exit;
}


if(($user = $database->verifyUser($_POST['username'], 
				  $_POST['password'])) 
   === FALSE) {
  die("ERR: Invalid user/password");
}

switch($_POST['action']) {
case 'readObject': 
  $objectdata = new ObjectData();
  echo $objectdata->getObjectPOST($_POST, $user, $database); break;
case 'writeObject': 
  $objectdata = new ObjectData();
  echo "MSG: writing Object.";
  $objectdata->putObjectPOST($_POST, $user, $database); break;
case 'deleteObject': 
  $objectdata = new ObjectData();
  $objectdata->deleteObjectPOST($_POST, $user, $database); break;
case 'getUserData': 
  $userdata = new UserData();
  echo $userdata->getUser($_POST, $database); break;
case 'putUserData': 
  $userdata = new UserData();
  $userdata->putUser($_POST, $user, $database); break;
case 'newUser':
  $userdata = new UserData();
  $userdata->newUser($_POST, $database); break;
case 'changePassword':
  $userdata = new UserData();
  $userdata->changePassword($_POST, $user, $database); break;
case 'getRestaurants':
  $restaurantdata = new RestaurantData();
  echo $restaurantdata->getRestaurants($_POST, $user, $database); break;
case 'getOneRestaurant':
	$restaurantdata = new RestaurantData();
	echo $restaurantdata->getOneRestaurant($_POST, $user, $database); break;
case 'getUserReviews':
  $reviewdata = new RestaurantData();
  echo $reviewdata->getUserReviews($_POST, $user, $database); break;
case 'getRestaurantReviews':
  $reviewdata = new RestaurantData();
  echo $reviewdata->getRestaurantReviews($_POST, $user, $database); break;
case 'putRestaurantReview':
  $reviewdata = new RestaurantData();
  echo $reviewdata->putRestaurantReview($_POST, $user, $database); break;
case 'addFriend':
  $frienddata = new FriendData();
  echo $frienddata->addFriend($_POST, $user, $database); break;
case 'deleteFriend':
  $frienddata = new FriendData();
  echo $frienddata->deleteFriend($_POST, $user, $database); break;
case 'messageFriend':
  $frienddata = new FriendData();
  echo $frienddata->messageFriend($_POST, $user, $database); break;
  
default: die("ERR: Invalid command"); break;
}

  //  ($_POST, $user, $database); break;
