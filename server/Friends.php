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
	
	function areFriends($user1, $user2, $db) {
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
		$query = "DELETE FROM FriendMap WHERE (`From`='$thisUser' AND `To`='$friendID') OR (`From`='$friendID' AND `To`='$thisUser')";
		$db->squery($query);
		return "MSG: Friend deleted";
	}
	
	function messageFriend($post, $user, $db){
		$friendID = $db->san($post['friend']);
		$thisUser = $user['Login'];
		$message = $db->san($post['message']);
		//check for separators
		if(
			(strstr($message, '!#!') !== FALSE) || 
			(strstr($message, ':&:') !== FALSE)
				) {
			die("ERR: Invalid character in message.");
		}
		$date = date('Y-m-d H:i:s');
		$this->areFriends($thisUser, $friendID, $db) or die("ERR: I'm not your friend, buddy!");
		$query = "INSERT INTO Messages (`From`, `To`, Message, Time) VALUES ('$thisUser', '$friendID', '$message', '$date')";
		$db->squery($query);
		return("MSG: Sent");
	}
	function confirmFriend($post, $user, $db) {
		/*
		 * action = confirmFriend
		 * friend = friendID
		 * 
		 */
		$friendID = $db->san($post['friend']);
		$thisUser = $user['Login'];
		$query = "SELECT * FROM FriendMap WHERE (`From` = '$friendID') AND (`To` = '$thisUser') AND Status = 'req'";
		$row = $db->oneRow($query);
		if($row === FALSE) {
			die("ERR: No request from ".$friendID);
		}
		$query = "UPDATE FriendMap SET Status = 'conf' WHERE (`From` = '$friendID') AND (`To` = '$thisUser')";
		$db->squery($query);
		// check for reverse
		$query = "SELECT * FROM FriendMap WHERE (`To` = '$friendID') AND (`From` = '$thisUser')";
		$row = $db->oneRow($query);
		if($row === FALSE) {
			$query = "INSERT INTO FriendMap (`From`, `To`, `Status`) VALUES ('$thisUser', '$friendID', 'conf')";
			$db->squery($query);
		} else {
			$query = "UPDATE FriendMap SET Status = 'conf' WHERE (`To` = '$friendID') AND (`From` = '$thisUser')";
			$db->squery($query);
		}
		
		return("MSG: Confirmed ".$friendID);
	}
	function getUnconfirmedFriends($post, $user, $db) {
		/* action = getUnconfirmedFriends
		 * 
		 */
		$thisUser = $user['Login'];
		$query = "SELECT `From` FROM FriendMap WHERE `To`='$thisUser' AND Status='req'";
		$res = $db->squery($query);
		$list = '';
		if($res->num_rows == 0) {
			die("MSG: No Requests");
		}
		while($row = $res->fetch_assoc()) {
			$list .= $row['From'].',';
		}
		$list = substr($list, 0, -1); //remove last comma
		return $list;
	}
	function getMessages($post, $user, $db) {
		/* action = getMessages
		 */
		//if((preg_match('!#!', $message) !== FALSE) || (preg_match(':&:', $message) !== FALSE)) {
		// Format sent to client:
		// Friend!#!Message:&:Friend!#!Message ... Message
		$thisUser = $user['Login'];
		$query = "SELECT * FROM Messages WHERE `To`='$thisUser' ORDER BY Time DESC LIMIT 50";
		$res = $db->squery($query);
		$list = '';
		if($res->num_rows == 0) {
			die("MSG: No Messages");
		}
		while($row = $res->fetch_assoc()) {
			$list .= $row['From'].'!#!'.$row['Message'].':&:';
		}
		$list = substr($list, 0, -3);
		return $list;
	}
	function getFriends($post, $user, $db) {
		$thisUser = $user['Login'];
		$query = "SELECT * FROM FriendMap WHERE Status = 'conf' AND `From` = '$thisUser'";
		$res = $db->squery($query);
		$list = '';
		if($res->num_rows == 0) {
			die("MSG: You have no friends");
		}
		
		while($row = $res->fetch_assoc()) {
			$list .= $row['To'].',';
		}
		$list = substr($list, 0, -1);
		return $list;
	}
	function deleteMessage($post, $user, $db) {
		$thisUser = $user['Login'];
		$sender = $db->san($post['sender']);
		$message = $db->san($post['message']);
		$query = "DELETE FROM Messages WHERE `To`='$thisUser' AND `From`='$sender' AND `Message`='$message'";
		$db->squery($query);
	}
}