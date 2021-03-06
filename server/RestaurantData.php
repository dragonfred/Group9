<?php
/* Data for calculating distances: 
http://calgary.rasc.ca/latlong.htm
It's approximately 70 miles per degree latitude/longitude.
(0.016667 degrees/mile)
class is lat 28.601936 longi -81.198444
reci is at lat 28.600810 longi -81.200145
*/

class RestaurantData {
	function getUserReviews($post, $user, $db) {
		$uuid = $user['UUID'];
		$qs = '';
		$qr = $db->squery("SELECT ReviewUUID FROM Reviews where UserUUID=".$db->uuidBinToSql($uuid)." LIMIT 50");
		while($qr->fetch_assoc()) {
			$qs .= $db->uuidBinToStr($qr['ReviewUUID'])."\n";
		}
		return $qs;
	}
	
	function getRestaurantReviews($post, $user, $db) {
		$restaurant = $db->san($post['uuid']);
		$qs='';
		$qr = $db->squery("SELECT ReviewUUID FROM Reviews WHERE RestaurantUUID=".$db->uuidStrToSql($restaurant)." LIMIT 50");
		while($row = $qr->fetch_assoc()) {
			$qs .= $db->uuidBinToStr($row['ReviewUUID']).",";
		}
		return $qs;
	}
	function getOneRestaurant($post, $user, $db) {
		$uuid = $db->san($post['uuid']);
		$query = "SELECT * FROM Restaurants WHERE UUID = ".$db->uuidStrToSql($uuid);
		$row = $db->oneRow($query);
		if($row === FALSE) {
			die("ERR: Restaurant not found.");
		}
		$res = 'name|'.$row['Name'].'*'.
				'address|'.$row['Address'].'*'.
				'phone|'.$row['Phone'].'*'.
				'lat|'.$row['Latitude'].'*'.
				'lng|'.$row['Longitude'].'*'.
				'rating|'.$row['Rating'].'*'.
				'count|'.$row['RatingCount'];
		return $res;
	}
	function getRestaurants($post, $user, $db) {
		$query = "SELECT UUID FROM Restaurants WHERE 1=1 ";
		$degmile = 0.016667;
		$validQuery = FALSE;
		if(isset($post['lati']) && isset($post['longi']) && isset($post['miles'])) {
			$validQuery = TRUE;
			$lati = $db->san($post['lati']);
			$longi = $db->san($post['longi']);
			$miles = $db->san($post['miles']);
			$degdist = $degmile * $miles;
			$latmin = $lati - $degdist;
			$latmax = $lati + $degdist;
			$longmin = $longi - $degdist;
			$longmax = $longi + $degdist;
			$query .= "AND Latitude > $latmin AND Latitude < $latmax AND Longitude > $longmin AND Longitude < $longmax ";
		}
		if(isset($post['zip'])) {
			$validQuery = TRUE;
			$zip = $db->san($post['zip']);
			$query .= "AND Zip=".$post['zip']." ";
		}
		if(isset($post['keywords'])) {
			$validQuery = TRUE;
			$keywords = $db->san(strtoupper($post['keywords']));
			$keywordArray = explode(",",$keywords);
			foreach($keywordArray as $key => $val) {
				$query .= "AND (Keywords LIKE '%$val%' OR Name LIKE '%$val%') ";
			}
		}
		$query .= " LIMIT 50";
		
		if($validQuery === FALSE) {
			die("ERR: Invalid set of criteria to select restaurant.");
		}
		$qs = '';
		$qr = $db->squery($query);
		while($row = $qr->fetch_assoc()) {
			$qs .= $db->uuidBinToStr($row['UUID']).",";
		}
		return $qs;
	}
	
	function newRating($row, $rating, $db) {
		$uuid = $db->uuidBinToSql($row['UUID']);
		$oldRating = $row['Rating'];
		$oldCount = $row['RatingCount'];
		$newCount = $oldCount + 1;
		if($oldCount == 0) $oldCount = 1;
		$newRating = $oldRating * ($oldCount / $newCount) + $rating * (1 / $newCount);
		$query = "UPDATE Restaurants SET `Rating`='$newRating', `RatingCount`='$newCount' WHERE UUID = $uuid";
		echo '$@'.$query.'@$';
		$db->query($query);
	}
	
	function putRestaurantReview($post, $user, $db) {
		$useruuid = $user['UUID'];
		$visibility = $db->san($post['visibility']);
		$restaurantuuid = $db->san($post['restaurantuuid']);
		$reviewuuid = $db->san($post['reviewuuid']);
		$review = $db->san($post['object']);
		$rating = $db->san($post['rating']);
		
		$r1 = $db->onerow("SELECT * FROM Restaurants WHERE UUID=".$db->uuidStrToSql($restaurantuuid));
		if($r1 === FALSE) {
			die("ERR: Restaurant not found.");
		}
		$this->newRating($r1, $rating, $db);
		$r1 = $db->onerow("SELECT * FROM Reviews WHERE ReviewUUID=".$db->uuidStrToSql($reviewuuid));
		if($r1 !== FALSE) {
			die("ERR: Review UUID already exists.");
		}
		
		$q1 = "INSERT INTO Reviews (RestaurantUUID, UserUUID, ReviewUUID) VALUES (".
				$db->uuidStrToSql($restaurantuuid).', '.
				$db->uuidBinToSql($user['UUID']).', '.
				$db->uuidStrToSql($reviewuuid).')';
		$db->squery($q1);
		$q2 = "INSERT INTO Objects (UUID, Object, OwnerUUID, Visibility) VALUES (".
				$db->uuidStrToSql($reviewuuid).', '.
				"'".$db->objectToSql($review)."', ".
				$db->uuidBinToSql($user['UUID']).', '.
				"'".$visibility."')";
		$db->squery($q2);
		return "MSG: Review Added.";
	}

}

