<?php

include("../db/connection.php");
$result=mysqli_query($con,"SELECT * FROM company_information");
?>
<html>
<head>
<script type="text/javascript" src="jquery_002.js"></script>
<script type="text/javascript" src="jquery.validate.min.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
jQuery.validator.addMethod("notEqual", function(value, element, param) {return this.optional(element) || value != param;}, "Please enter your name");
			$(document).ready(function() {
			$("#register_form").validate({
				submitHandler:function(form) 
				{
					SubmittingForm();
				},
				rules: 
				{
					total_balance: 
					{
						required: true,
						number: true			
					},// simple rule, converted to {required:true}
					user: 
					{
						required: true
					},
					company_balance: 
					{
						required: true,
					    number: true	
					},
					amount: 
					{
						required: true,
					    number: true	
					},
					remark: 
					{
						required: true	
					},
					comment: 
					{
						required: true
					}
					},
					messages: 
					{
						comment: "Please enter a comment."
					}
			});	
		});	
</script>

<link type="text/css" href="jquery.datepick.css" rel="stylesheet">
<script type="text/javascript" src="jquery.datepick.js"></script>
<script type="text/javascript">
$(function() {
	$('#t').datepick();
});

function showDate(date) {
	alert('The date chosen is ' + date);
}
</script>
<script type="text/javascript">
$(function() {
	$('#tt').datepick();
});

function showDate(date) {
	alert('The date chosen is ' + date);
}
</script>

 



<title>Advance Cash</title>
</head>
<body>
<?php
	$user_name=$_POST['user_name'];
	$from=$_POST['from'];
	$to=$_POST['to'];
?>
<form  action="" method="post" name="register_form" id="register_form">
<table id="form_table2" align="center" style="background:#FFF;width:850px;font-family:Arial, Helvetica, sans-serif;">
<tr><th colspan="3" style="background:#CCC;text-align:left;">Users Advance Payment</th></tr>
<tr><td>User : 
<select name="user_name" id="user_name" onChange="reload()">
   
	<option>Select One</option>
	<?php
	$result1=mysqli_query($con,"SELECT * FROM user_register ");
	while($row1=mysqli_fetch_array($result1))
	{
	?>
	<option value="<?php echo $row1['user_name']; ?>"><?php echo $row1['user_name'];?></option>
	<?php
	
	}
	?>
    <option value="all">All Users</option>
	</select>
</td>


	<td>From :&nbsp;<input type="text" name="from" id="t" ></td>



	<td>To :&nbsp;<input type="text" name="to" id="tt" ></td>
</tr>




<tr>
	<td colspan="3" align="center" id="normal"><input type="submit" value="Search" name="add" id="button" />
    <input type="submit" value="Print" onClick="PrintDiv();" id="button" /></td>
</tr>
</table>
</form>
