<?php

require('cred.php');
require('Database.php');

$db = new Database($DB_HOST, $DB_USER, $DB_PASS, $DB_DB);

if(!isset($_POST['username']) || 
   !isset($_POST['hp']) ||
   !isset($_POST['newpassword']) ||
   !isset($_POST['confirm'])) {
     die("ERR: Invalid form submission.");
}

   if($_POST['newpassword'] != $_POST['confirm']) {
     die("ERR: Passwords do not match.");
   }

   $username = $db->san($_POST['username']);
   $hp = $db->san($_POST['hp']);
   $newpw = $db->san($_POST['newpassword']);

   $userdata = $db->oneRow("SELECT * FROM Users WHERE Login='$username'");

   if($userdata === FALSE) {
     die("ERR: User not found.");
   }

   $myhash = md5($userdata['Password'].$userdata['Email']);

   if($myhash != $hp) {
     die("ERR: Invalid hash value. Have you already changed the password?");
   }

   $db->squery("UPDATE Users SET Password='$newpw' WHERE Login='$username'");

   echo("Password updated.");
