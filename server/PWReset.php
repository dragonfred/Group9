<?php

class PWReset {
  function sendResetEmail($post, $db) {
    if(!isset($post['pwreset'])) {
      die('ERR: No username supplied.');
    }

    $username = $db->san($post['pwreset']);
    $userdata = $db->oneRow("SELECT * FROM Users WHERE Login='$username'");

    if($userdata === FALSE) {
      die("ERR: User not found");
    }

    $hashp = md5($userdata['Password'].$userdata['Email']);
    $url = 'http://';
    $url .= $_SERVER['HTTP_HOST'];
    $url .= $_SERVER['SCRIPT_NAME'];
    $url .= "?pwreset=$username&hp=$hashp";

    $message = <<<EOS
A password reset request has been created for your Restaurant App account, $username. If you did 
 not request a password reset, you may ignore this message.
Otherwise, please click the link below to reset your password.
    
$url
EOS;

    $to      = $userdata['Email'];
    $subject = 'Restaurant App Password Reset';
    $headers = "From: noreply@{$_SERVER['HTTP_HOST']}" . "\r\n" .
      "Reply-To: noreply@{$_SERVER['HTTP_HOST']}" . "\r\n" .
      'X-Mailer: PHP/' . phpversion();
    
    if(mail($to, $subject, $message, $headers)) {
      die("MSG: Mail sent successfully.");
    } else {
      die("ERR: Mail send failure.");
    }
} // end of sendResetEmail

function validateResetRequest($get, $db) {
  if(!isset($get['pwreset']) || !isset($get['hp'])) {
      die("ERR: Invalid Password Reset URL");
  }
    
  $pwreset = $db->san($get['pwreset']);
  $hp = $db->san($get['hp']);

  include("PWReset_Page1.php");
}

} // end of class
