
<?php
include("../header.php");
include("table.php");
?>


		<link rel="stylesheet" type="text/css" href="datatables.min.css">
 
		<script type="text/javascript" src="datatables.min.js"></script>
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#example').DataTable();
			} );
		</script>

<style>
.dataTable
{
font-size:10px;	
}

</style>
<div class="col-sm-12">

<table id="example" class="table table-striped table-bordered dataTable no-footer" cellspacing="0"  role="grid" aria-describedby="example_info" >

       
        
            
          <?php
	
		  include("../connection.php");
	
	
	
	
	
	
	
	

$del_id=$_REQUEST['del_id'];

//if($del_id!="")

	?>
    <script>


function rem()
{
if(confirm('Are you sure you want to delete this record?')){
return true;
}
else
{
return false;
}
}


function rem2()
{
if(confirm('Are you sure you want to deactive this record?')){
return true;
}
else
{
return false;
}
}
</script>
    
	
	<?php
mysql_query("delete from $table where id='$del_id'");
	$col=11;
	
	
	
	
	
	
	
		  $result2 = mysql_query("SHOW FIELDS FROM $table");

 echo "<thead><tr>";

while ($row2 = mysql_fetch_array($result2))
 {

  $name=$row2['Field'];
  if($i<$col)
  echo "<th>".
  str_replace('_', ' ', $name)
  ."</th>";
 $i++;
 }
 echo "<th>Bal</th>
 <th>Bill</th>
<th>Update</th>
 
 <th>Del</th> 
 </tr></thead>";
   
   $i=0;
   echo "<tfoot><tr>";
	  $result3 = mysql_query("SHOW FIELDS FROM $table");
while ($row2 = mysql_fetch_array($result3))
 {

  $name=$row2['Field'];
  if($i<$col)
  echo "<th>".
  str_replace('_', ' ', $name)
  ."</th>";
 $i++;
 }
 echo "<th>Bal</th>
 <th>Bill</th>
<th>Update</th>
 
 <th>Del</th> 
 </tr></tfoot><tbody>";
   
            
            
         
 	$result = mysql_query("SELECT * FROM $table ");
	

		while($row = mysql_fetch_array($result))
		{
		$id=$row['0'];
		echo "<tr>";
		for($k=0;$k<$col;$k++)
		{
	
			
			
			echo "<td >$row[$k]</td>";
		
		
		
		
		
		
		}
		
		
		$result2 = mysql_query("select sum(amount) as amt from transaction where account_no='$row[id]' ");
$row2 = mysql_fetch_array($result2);
				$debit=$row2['amt'];
		$bal =$row[10]-$debit;
		echo "<td >$bal</td>";
		
		
			echo "
			<td><a href='../fees/form.php?fid=$id'>Fees</a></td>
			<td><a href='update.php?id=$id'>Update</a></td>
			
			<td><a href='?del_id=$id&location=$location' onclick='return rem()'>Del</a></td>
		
			</tr>";
		
		
		
		}
        
        ?>
        </tbody>
    </table>
			
		



<script type="text/javascript">
	// For demo to fit into DataTables site builder...
	$('#example')
		.removeClass( 'display' )
		.addClass('table table-striped table-bordered');
</script>
	