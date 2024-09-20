<?php
include("../connection.php");
include("table.php");
$id=$_REQUEST[id];
$result = mysqli_query($con,"SHOW FIELDS FROM $table");

$i = 0;

while ($row = mysqli_fetch_array($result))
 {
 // echo $row['Field'] . ' ' . $row['Type']."<br>";
  $name=$row['Field'];
  //echo $name."<br>";
  $post_values[]=$_POST[$name];
  $field_name[]=$name;
  $data_type[]=$row['Type'];
 // echo $post_values[$i];
  $i++;
 }
$j=$i;
//echo "<br>";
for($k=1;$k<$i;$k++)
{
	if($fields=="")
	$fields=$field_name[$k];
	else
	$fields=$fields.",".$field_name[$k];
	
	
	$type=$data_type[$k];
	$type = explode("(", $type);
    $type_only=$type[0];
	
	

  if($type_only=='tinytext')
  {

$date=date("Y-m-d-h-i-s");
$target_path = $target_path.$date.basename($_FILES[$field_name[$k]]['name']); 
//echo "<br>tsets".basename($_FILES[$field_name[$k]]['name']);
$path_null=move_uploaded_file($_FILES[$field_name[$k]]['tmp_name'], $target_path);

$target_path2 = $date.basename($_FILES[$field_name[$k]]['name']); 

if($path_null=="")
{
$result2 = mysqli_query($con,"SELECT * FROM $table where id=$id") or die("error".mysql_error());
$row2= mysqli_fetch_array($result2);
$target_path2=$row2[$field_name[$k]];
}


	if($datas=="")
	{
	$datas="$field_name[$k]='".$target_path2."'";
	//echo $field_name[$k];
	}
	else
	{
	$datas=$datas.",$field_name[$k]='".$target_path2."'";
//	echo $field_name[$k];
	}
	
  }
  
  
  else
	 {
	if($datas=="")
	$datas="$field_name[$k]='".$post_values[$k]."'";
	else
	$datas=$datas.",$field_name[$k]='".$post_values[$k]."'";
	
  }
	
	
	
	
	
}
//echo $datas."<br>";

mysqli_query($con,"UPDATE $table SET $datas  WHERE id='$id'") or die("error".mysql_error());










mysql_free_result($result);

















mysql_close();


header("location:update.php?id=$id");
?>
