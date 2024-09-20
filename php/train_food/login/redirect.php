<?php
session_start();
include('../db/connectionI.php');
//$db_name="music"; // Database name 
$tbl_name="employee"; // Table name 

// Connect to server and select databse.


// username and password sent from form 
$myusername=$_POST['UserName']; 
$mypassword=$_POST['Password']; 


if(isset($_POST['login']))
{
//

if($myusername=="admin" and $mypassword=="admin")
{
		$_SESSION['type']="admin";
header("location:../dashboard/dashboard.php");
}
elseif($_POST['type']=='vendor')
{

	$sql2 = "SELECT * FROM vendor_tbl WHERE email='$myusername' and password='$mypassword'";
	$result2 = mysqli_query($con,$sql2);

	$cc = mysqli_num_rows($result2);
	$row2 = mysqli_fetch_array($result2);

	if($cc==1)
		{	
			$_SESSION['type']="vendor";
			$_SESSION['uid']=$row2['id'];
			header("location:../dashboard/dashboard.php");
		}else{
			header("Location:login.php?error");
		}



}
else
{
header("location:login.php?a=1");
}




}

?>
 
 



