	<link rel="stylesheet" href="../css/stylee.css">
<form action="staff_insert.php" method="post" id="staff_form" name="staff_form">
<table  border="0" class="form_table" cellpadding="0" cellspacing="0" align="center">
  <tr><th colspan="4">
Staff Registration <?php include '../db/connection.php'; ?>
  </th></tr>

<div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="staff_regno" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="first_name" class="form-control" placeholder="Username" value="michael23">
                                            </div>
                                        </div>
                                        
                                    </div>

 <tr>
    <td>Reg No:</td>
    <td><input name="staff_regno" type="text" /></td>
    <td>First Name:</td>
    <td><input name="first_name" type="text"  /></td>
 </tr>
    
    
                               <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="last_name" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="gender" class="form-control" placeholder="Username" value="michael23">
                                            </div>
                                        </div>
                                        
                                    </div>
    
    
 <tr>
 	<td>Last Name:</td>
    <td><input name="last_name" type="text"  /></td>
    <td>Gender:</td>
    <td><input name="last_name" type="radio" value="male"/> Male  &nbsp;&nbsp;<input name="gender" type="radio" value="female"  /> Female </td>
 </tr>
    
   
              <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="dob" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="nationality" class="form-control" placeholder="Username" value="michael23">
                                            </div>
                                        </div>
                                        
                                    </div>

               
               
               
 <tr>
 	<td>Date Of Birth:</td>
    <td><input name="dob" type="text" /></td>
    <td>Nationality:</td>
    <td><input name="nationality" type="text"  /></td>
 </tr>
 
 
 
              <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="email_id" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="contact_no" class="form-control" placeholder="Username" value="michael23">
                                            </div>
                                        </div>
                                        
                                    </div>
 
 
    
 <tr>
 	<td>Email ID:</td>
    <td><input name="email_id" type="text" /></td>
    <td>Land No.:</td>
    <td><input name="contact_no" type="text"  /></td>
 </tr>
 
 
   <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="contact_no" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="contact_landno" class="form-control" placeholder="Username" value="michael23">
                                            </div>
                                        </div>
                                        
                                    </div>
 
 
  <tr>
     <td>Mobile No 2.:</td>
    <td><input name="contact_no" type="text"  /></td>
    <td>Mobile No 1.:</td>
    <td><input name="contact_landno" type="text"  /></td>
    </tr>
    
    
    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="address" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                       
                                        
                                    </div>
    
    
    <tr>
    <td>Address:</td>
    <td><textarea name="address"></textarea></td>
 </tr>
 
 
 
  <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input type="text" name="joining_date" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                       
                                        
     <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label></label>
                                                <input name="department" class="form-control" disabled placeholder="Company" value="Creative Code Inc.">
                                            </div>
                                        </div>
                                       
                                        
                                    </div>
    
                                    </div>
    
 
 
 
 <tr>
    <td>Joining Date:</td>
    <td><input name="joining_date" type="text"  /></td>
    <td>Department:</td>
    <td>
    <select name="department">
    <option value="">Select Department</option>
    <?php
    $result= mysql_query("SELECT * FROM department order by department asc") or die("error".mysql_error($con));

while($row=mysql_fetch_array($result))
{
	echo "<option value='$row[department]'>".$row['department']."</option>";
	
}
    ?>
    </select></td>
 </tr>
    
 <tr>
    <td>Designation:</td>
    <td><select name="designation">
    <option value="">Select Designation</option>
    <option value="HOD">HOD</option>
    <option>Staff</option>
    </select></td>
    <td>Higher Qualification:</td>
    <td>
    <select name="h_qualification">
    <option value="">Select Qualification</option>
    <option value="Btech">Btech</option>
    <option>BCA</option>
    <option>BSC</option>
    <option>Mtech</option>
    <option>MCA</option>
    <option>MSC</option>
    </select></td>
 </tr>
 
 <tr>
    <td>Remark:</td>
    <td><textarea name="remark"></textarea></td>
    <td></td>
    <td></td>
 </tr>
 
 <tr>
    <td colspan="4" align="center" ><input name="reg_no" type="submit" value="Next >>" />&nbsp;&nbsp;
        <input name="submit" type="submit" value="Finish" /></td>
 </tr>
 
 
 
</table>
</form>