<?php
//session_start();
//echo $_SESSION['login_user'];
//if($_SESSION['login_user']!="")
//echo "no session";
//$con=mysqli_connect("localhost","hazoortr_aumento","7DfkK*plReu2","hazoortr_aps");

if($_SESSION['login_user']!="admin")
$con=mysqli_connect("localhost","hazoortr_del","deldel","hazoortr_aps");
else
$con=mysqli_connect("localhost","hazoortr_aumento","7DfkK*plReu2","hazoortr_aps");


?>