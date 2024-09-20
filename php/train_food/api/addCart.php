<?php

include("connection.php");

$uid = $_POST['uid'];
$foodId = $_POST['foodId'];
$quantity = "1"; 

$sel=mysqli_query($con,"select * from cart_tbl where user_id='$uid' and food_id='$foodId'");
$res=mysqli_fetch_array($sel);
$row=mysqli_num_rows($sel);

if($row>0)
{
	
	$sql2 = "UPDATE cart_tbl SET quantity=quantity+1 WHERE user_id='$uid' and food_id='$foodId'";

	mysqli_query($con,$sql2);

	echo "success";

}
else{

	$sql = "INSERT INTO cart_tbl(user_id,food_id,quantity) VALUES('$uid','$foodId','$quantity')";
	if(mysqli_query($con,$sql)){
		
		echo "success";
	}
	else{
		echo"failed";
	}
}


?>