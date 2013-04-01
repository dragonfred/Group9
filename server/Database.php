<?php

class Database extends mysqli {

  public static $connection;
  
  public function __construct($DB_HOST, $DB_USER, $DB_PASS, $DB_DB)  {
    parent::__construct($DB_HOST, $DB_USER, $DB_PASS, $DB_DB);
    if (mysqli_connect_error()) {
      die('ERR: Connect Error (' . mysqli_connect_errno() . ') '
	  . mysqli_connect_error());
    }
  }


  function hexToBin($x) {
    $s='';
    foreach(explode("\n",trim(chunk_split($x,2))) as $h) $s.=chr(hexdec($h));
    return($s);
  }
  
  function BinToHex($x) {
    $s='';
    foreach(str_split($x) as $c) $s.=sprintf("%02X",ord($c));
    return($s);
  } 

  function uuidBinToStr($x) {
    $x = $this->BinToHex($x);
    $x = strtolower($x);
    $x = 
      substr($x, 0, 8).'-'.
      substr($x, 8, 4).'-'.
      substr($x, 12, 4).'-'.
      substr($x, 16, 4).'-'.
      substr($x, 20, 12);
    return($x);
  }
  function genUuidBin() {
    $randval = openssl_random_pseudo_bytes(16);
    $query = "SELECT * FROM Objects WHERE UUID=".$this->uuidBinToSql($randval);
    $res = $this->squery($query);
    if($res->num_rows != 0) return $this->genUuidBin();
    else return $randval;
  } 
  function uuidStrToHex($x) {
    return strtoupper(str_replace('-','',$x));
  }
  function uuidStrToBin($x) {
    $x = $this->uuidStrToHex($x);
    return $this->hexToBin($x);
  }
  function uuidStrToSql($x) {
    return '0x'.$this->uuidStrToHex($x);
  }
  function uuidBinToSql($x) {
    return '0x'.$this->BinToHex($x);
  }
  function verifyUser($username, $password) {
    return $this->oneRow("SELECT * FROM Users WHERE Login = '".
			$this->san($username).
			"' AND Password = '".
			$this->san($password).
			"' LIMIT 1");
  }

  function sqlToObject($x) {
    return ($x);
  }
  function objectToSql($x) {
    return ($x);
  }
  function oneRow($query) {
    $res = $this->squery($query);
    if($res->num_rows != 1) return FALSE;
    else return $res->fetch_assoc();
  }

  function squery($query) {
    $res = $this->query($query);
    if($res) return $res;
    else die("ERR: ".$this->error);
  }

  function san($s) {
    return $this->real_escape_string($s);
  }
  
}
