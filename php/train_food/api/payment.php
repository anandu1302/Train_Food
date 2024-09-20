<?php

include("connection.php");

$uid = $_POST['userId'];
$orderId = $_POST['orderId'];
$amount = $_POST['amount'];
$cardname = $_POST['cardname'];
$cardnumber = $_POST['cardnumber'];
$cardtype = $_POST['cardtype'];
$cardmonth = $_POST['cardmonth'];
$cardyear = $_POST['cardyear'];
$cvv = $_POST['cvv'];

$date = date('d-m-Y');

$sql ="INSERT INTO payment_tbl (user_id,order_id,amount,date,card_name,card_number) VALUES ('$uid','$orderId','$amount','$date','$cardname','$cardnumber')";

if(mysqli_query($con,$sql)){
	
	$sql2 ="UPDATE orders_tbl SET payment_status='paid' WHERE id='$orderId'";
	mysqli_query($con,$sql2);

	echo "success";
}
else{
	
	echo"failed";
} 



?>