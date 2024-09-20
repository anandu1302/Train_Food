<?php
include("../connection.php");
include("table.php");
$result = mysqli_query($con,"SHOW FIELDS FROM $table");
$i = 0;
while ($row = mysqli_fetch_array($result))
 {
 // echo $row['Field'] . ' ' . $row['Type']."<br>";
  $name=$row['Field'];
  //echo $name."<br>";
  $post_values[]=addslashes($_POST[$name]);
  $field_name[]=$name;
  $data_type[]=$row['Type'];
 // echo $post_values[$i];
  $i++;
 }
$j=$i;
//echo "<br>";
for($k=0;$k<$i;$k++)
{
	if($fields=="")
	$fields=$field_name[$k];
	else
	$fields=$fields.",".$field_name[$k];
	
	
	$type=$data_type[$k];
	//$data_type[$k];
	$type = explode("(", $type);
  $type_only=$type[0];
	
	

  if($type_only=='tinytext')
  {
	

$date=date("Y-m-d-h-i-s");
$target_path = $target_path.$date.basename($_FILES[$field_name[$k]]['name']); 
$target_path2 = $date.basename($_FILES[$field_name[$k]]['name']); 
//echo $target_path;
move_uploaded_file($_FILES[$field_name[$k]]['tmp_name'], $target_path);





	if($datas=="")
	{
	$datas="'".$target_path2."'";
	//echo $field_name[$k];
	}
	else
	{
	$datas=$datas.",'".$target_path2."'";
//	echo $field_name[$k];
	}
	
  }
  
  elseif($type_only=='date')
  {
	$var=$post_values[$k];
		  $date2=date("Y-m-d",strtotime($var));
	
	
	if($datas=="")
	$datas="'".$date2."'";
	else
	$datas=$datas.",'".$date2."'";
  }
  
  else
	 {
	if($datas=="")
	$datas="'".$post_values[$k]."'";
	else
	$datas=$datas.",'".$post_values[$k]."'";
	
  }
}
//echo $datas;

mysqli_query($con,"INSERT INTO $table($fields) VALUES ($datas)") or die("error".mysql_error());


$c=$_POST['start_date'];
$end_date=strtotime("+".$_POST['duration']." months", strtotime($c));

function date_range($t,$start_date, $end_date,$t2, $step = '+1 day', $output_format = 'Y-m-d') {

    $dates = array();
    $current = strtotime($start_date);
    $last = strtotime($end_date);
	$slo=$t2;
	
	$tid=$t;
$con=mysqli_connect("localhost","root","","varnakerala");
    while( $current < $last ) {

        $dates[] = date($output_format, $current);
        $current = strtotime($step, $current);
		$ds= date($output_format, $current) ;
		$de= date($output_format, $current);
		
		echo $ds."--";
		echo $de."--";
		echo $tid."<br>";
		
		mysqli_query($con,"INSERT INTO workassign(tutor_id,day_start,day_end,slot) VALUES ('$tid','$ds','$de','$slo')");
		
    }

    return $dates;
}

//echo date_range($_POST['toutor'],$_POST['day_start'],$_POST['day_end'],$_POST['ts'],$_POST['te']);


echo date_range($_POST['tutor'],$_POST['start_date'],$end_date,$_POST['slot']);




header("location:form.php?a=1");
?>
