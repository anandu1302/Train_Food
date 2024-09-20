<?php
 
include("connection.php");

$rid = $_REQUEST['rid'];

$sql = "SELECT * FROM food_tbl WHERE restaurant_id='$rid'";
$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result) > 0){

	while($row = mysqli_fetch_assoc($result))
		$data["data"][] = $row;
	echo json_encode($data);
}
else{
	echo "failed";
}

?>