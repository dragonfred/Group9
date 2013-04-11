<?php
// https://developers.google.com/maps/documentation/geocoding/
require('../Database.php');
require('../cred.php');
$db = new Database($DB_HOST, $DB_USER, $DB_PASS, $DB_DB);
ini_set('auto_detect_line_endings',TRUE);
error_reporting(E_ALL);
//pecho("Start.");

isset($_GET['loc']) or die("Please select location.");
$loc = $_GET['loc'];

// prevent path change
//(preg_match('/\//', $loc) === FALSE) or die("Invalid location name");

$fh = fopen($loc.".csv", "r");
($fh !== FALSE) or die("No location data present.");

fgetcsv($fh); // drop first line
pecho("USE Restaurant");
pecho("TRUNCATE TABLE Restaurants;");

while (($row = fgetcsv($fh, 4096)) !== FALSE) {
  // Name, Address, City, State, Zip, Phone

  $name = $row[0];
  $address = $row[1];
  $city = $row[2];
  $state = $row[3];
  $zip = $row[4];
  $phone = $row[5];

  // this is a hack because it never works 100%, just recover here.
  //if($trip === FALSE && $name != 'Gentabellas Restaurant') continue;
  //else $trip = TRUE;

  //http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true_or_false
  $surl = 'http://maps.googleapis.com/maps/api/geocode/json?address=';
  $surl .= preg_replace('/\s+/', '+', $address);
  $surl .= ',+';
  $surl .= preg_replace('/\s+/', '+', $city);
  $surl .= ',';
  $surl .=  preg_replace('/\s+/', '+', $state);
  $surl .= '&sensor=false';

  $gjson = serverQueryUrl($surl, 1);
  
  if($gjson === FALSE) {
    echo "/*\n";
    echo "Couldn't process $surl\n";
    echo "*/\n";
  }

  $addressc = $gjson['results'][0]['formatted_address'];
  $location = $gjson['results'][0]['geometry']['location'];
  $lat = $location['lat'];
  $lng = $location['lng'];
  $uuid = $db->uuidBinToSql($db->genUuidBin());

  $name = $db->san($name);
  $addressc = $db->san($addressc);
  $zip = $db->san($zip);
  $query = 'INSERT INTO Restaurants (`Latitude`, `Longitude`, `Name`, `Address`, `Zip`, `UUID`, `Phone`) VALUES (';
  $query .= "$lat, $lng, '$name', '$addressc', $zip, $uuid, $phone);";
  pecho($query);
  $query2 = 'INSERT INTO Objects(`UUID`, `Object`, `ownerUUID`, `Visibility`, `note`) VALUES (';
  $query2 .= "$uuid, 'RST: ', 0, 'public', 'RST: $name');";
  pecho($query2);
  // pecho('gjson');
  //echo("<pre>");
   //print_r($gjson['results'][0]['geometry']['location']);
   //print_r($gjson);
   //echo("</pre>");
  // pecho ($surl);
  flush();
  //break;
}

function pecho($s) {
  echo $s."\n";
}
 
function ServerQueryUrl($url, $count) {
  $goog_f = fopen($url, "r");
  if($goog_f === FALSE) {
    return FALSE;
  }
  $goog_line = fread($goog_f, 8192);
  //pecho($goog_line);
  $gjson = json_decode($goog_line, true);

  if(!isset($gjson['results'][0]['geometry']['location'])) {
    if(isset($gjson['status']) && $gjson['status'] == 'OVER_QUERY_LIMIT') {
      echo "\n /*";
      // print_r($gjson);
      echo 'delaying '.($count * 3).' seconds';
      echo "*/\n";
      sleep($count * 3);
      return ServerQueryUrl($url, $count * 3);
    } else {
      echo "\n/* ERROR on $url */\n";
      return FALSE;
    }
  }

  return $gjson;
}
  
