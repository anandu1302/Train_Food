<?php

include("connection.php");

$uid = $_REQUEST['uid'];

$sql = "SELECT * FROM cart_tbl WHERE user_id='$uid'";

$result = mysqli_query($con,$sql);

if (mysqli_num_rows($result) > 0) {

	$sql = "SELECT COUNT(*) as cc FROM cart_tbl WHERE user_id='$uid'";
	$result = mysqli_query($con,$sql);
	$row=mysqli_fetch_array($result);

	
	echo $row['cc'];

} else {

	echo "failed";

 }

?>