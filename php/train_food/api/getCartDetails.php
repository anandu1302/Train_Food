<?php

include("connection.php");

$uid = $_REQUEST['uid'];

$sql = "SELECT * FROM cart_tbl WHERE user_id='$uid'";
$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result) > 0){

	while($row = mysqli_fetch_assoc($result)){

		$sql2 = "SELECT * FROM food_tbl WHERE id ='$row[food_id]'";
		$result2 = mysqli_query($con,$sql2);
		$roww = mysqli_fetch_assoc($result2);

		$totalPrice = $roww['price']*$row['quantity'];


		$data["data"][] = array('id'=>$row['id'],'itemName'=>$roww['itemName'],'price'=>$roww['price'],'totalPrice' => $totalPrice,'image'=>$roww['image'],'quantity' => $row['quantity']);
		
	}
	echo json_encode($data);
}
else{
	echo "failed";
}
?>