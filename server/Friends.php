<?php

/*
CREATE TABLE `FriendMap` (
		`Source` varchar(20) DEFAULT NULL,
		`Dest` varchar(20) DEFAULT NULL,
		`Status` enum('req','conf') DEFAULT NULL,
		`SID` int(11) NOT NULL AUTO_INCREMENT,
		PRIMARY KEY (`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$
*/

class FriendData {
	
	function areFriends($user1, $user2) {
		$query = "SELECT * FROM FriendMap WHERE ((`From`='$user1' AND `To`='$user2') OR (`From`='$user2' AND `To`='$user1')) AND Status='conf'";
		$res = $db->squery($query);
		return ($res->num_rows != 0);
	}
	function findFriend($post, $user, $db) {
		$friendID = $db->san($post['friend']);
		$query = "SELECT * FROM Users WHERE Login='$friendID'";
		$res = $db->oneRow($query);
		if($res !== FALSE) {
			return ("MSG: Found ".$friendID);
		} else {
			return ("ERR: No ".$friendID);
		}
	}
	function addFriend($post, $user, $db){
		$friendID = $db->san($post['friend']);
		$thisUser = $user['Login'];
		$query = "SELECT * FROM FriendMap WHERE `From`='$thisUser' AND `To`='$friendID'";
		if($db->oneRow($query) !== FALSE) die("ERR: Already added as friend.");
		$query = "INSERT INTO FriendMap (`From`, `To`, Status) VALUES ('$thisUser', '$friendID', 'req')";
		$db->squery($query);
		return "MSG: Sent Request";
	}
	
	function deleteFriend($post, $user, $db){
		$friendID = $db->san($post['delete']);
		$thisUser = $user['Login'];
		$query = "DELETE FROM FriendMap WHERE (`From`='$thisUser' AND `To`='$friendID') OR (`To`='$friendID' AND `From`='$thisUser')";
		$db->squery($query);
		return "MSG: Friend deleted";
	}
	
	function messageFriend($post, $user, $db){
		$friendID = $db->san($post['friend']);
		$thisUser = $user['Login'];
		$message = $db->san($post['message']);
		$date = date('Y-m-d H:i:s');
		areFriends($thisUser, $friendID) or die("ERR: I'm not your friend, buddy!");
		$query = "INSERT INTO Messages (`From`, `To`, Message, Time) VALUES ('$thisUser', '$friendID', '$message', '$time'";
		$db->squery($query);
		return("MSG: Sent");
	}
	function confirmFriend($post, $user, $db) {
		
	}
	function getUnconfirmedFriends($post, $user, $db) {
		
	}
	function getMessages($post, $user, $db) {
		
	}
	function getFriends($post, $user, $db) {
		
	}
}