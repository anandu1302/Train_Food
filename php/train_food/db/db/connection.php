<?php
session_start();

//$_SESSION['login_user']="anish";
//echo $_SESSION['login_user'];
//if($_SESSION['login_user']!="")
//echo "no session".$_SESSION['login_user'];



//$conn=mysql_connect("localhost","hazoortr_aumento","7DfkK*plReu2");

if($_SESSION['login_user']!="admin")
$conn=mysql_connect("localhost","hazoortr_del","deldel");
else
$conn=mysql_connect("localhost","hazoortr_aumento","7DfkK*plReu2");


mysql_select_db("atm_security",$conn);
?>