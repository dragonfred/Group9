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

class FriendsData {
	
	function findFriend($post, $user, $db) {
		$friendID = $db->san($post['friend']);
		$query = "SELECT * FROM User WHERE Login='$friendID'";
		$res = $db->oneRow($query);
		if($res !== FALSE) {
			return ("MSG: Found "+$friendID);
		} else {
			return ("ERR: No "+$friendID);
		}
	}
	function addFriend($post, $user, $db){
		return "ERR: Unimplemented";
	}
	
	function deleteFriend($post, $user, $db){
		return "ERR: Unimplemented";
	}
	
	function messageFriend($post, $user, $db){
		return "ERR: Unimplemented";
	}
}