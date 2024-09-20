<?php

include("connection.php");

$uid = $_POST['uid'];
$trainDetails = $_POST['trainDetails'];
$name = $_POST['name'];
$contact = $_POST['contact'];
$trainNo = $_POST['trainNo'];
$coachPosition = $_POST['coach'];
$seatNo = $_POST['seatNo'];


$sql ="INSERT INTO orders_tbl (uid,train_details,name,contact,train_no,coach_position,seat_no,payment_status) VALUES ('$uid','$trainDetails','$name','$contact','$trainNo','$coachPosition','$seatNo','ordered')";

if(mysqli_query($con,$sql)){
	
	$id = mysqli_insert_id($con);
	echo $id;
}
else{
	
	echo"failed";
}

?>