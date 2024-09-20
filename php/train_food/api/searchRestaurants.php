<?php

include("connection.php");

$location = $_POST['location'];

$sql = "SELECT * FROM restaurant_tbl WHERE location LIKE '%$location%'";

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