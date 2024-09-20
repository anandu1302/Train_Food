<?php
error_reporting(0);
$status=$_REQUEST['status'];
if ($status == "logout")
{
    session_start();
    session_unset();
    session_destroy();
	header("location:../login/login.php");
}
?>
<?php
include("../common/menu.php");
	
?>


    <style>
#right
{
	
float:right;	
color:#333;
font-size:12px;
}
</style>

<style>
#right
{
	
float:right;	
color:#333;
font-size:12px;
}
.userd
{
color:#333;	
}
.red
{
background:#FFECF4;
padding:10px;	
}


#right
{
	
float:right;	
color:#333;
font-size:12px;
}
.userd
{
color:#333;	
}
.red
{
background:#F36;
padding:10px;	
}
.table
{
margin-bottom:10px;
background:#E6F4FF;	
}
.sep
{
height:30px;
background:#666;	
}
</style>
       


        <div class="content" >
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card" style="opacity: 0.9;">
                            <div class="header">
                                                             
                            </div>
                            <div class="content all-icons">
                                <div class="row" style=" /*background-image: url(Bugs.jpg);*/ ">

                                <?php
                        if($_SESSION['type']=='admin')
                        {
                        ?>
                                           
                              
                                <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../user_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="USER DETAILS">
                                    </div></a>
                                  </div>

                                   <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../vendor_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="VENDOR">
                                    </div></a>
                                  </div>

                                   <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../restaurants_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="RESTAURANTS">
                                    </div></a>
                                  </div>


                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../orders_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="ORDERS">
                                    </div></a>
                                  </div>

                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../payment_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="PAYMENTS">
                                    </div></a>
                                  </div>

                                 <!--  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="FEEDBACK">
                                    </div></a>
                                  </div> -->

								 
                                <?php
                                }elseif($_SESSION['type']=='vendor')
                                {
                                ?>

                                 <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../food_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="ADD FOOD ITEMS">
                                    </div></a>
                                  </div>

                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../orders_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="VIEW ORDERS">
                                    </div></a>
                                  </div>


                                   <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../payment_tbl/select.php">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="PAYMENTS">
                                    </div></a>
                                  </div>

                                 <!--  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="">    <div class="font-icon-detail"><i class="pe-7s-user"></i>
                                      <input type="text" value="FEEDBACK">
                                    </div></a>
                                  </div>
 -->

                                <?php
                                }
                                ?>
                                  

                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                
            </div>
        </div>
    </div>
</div>


</body>

    <!--   Core JS Files   -->
    <script src="../assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="../assets/js/bootstrap-checkbox-radio-switch.js"></script>

	<!--  Charts Plugin -->
	<script src="../assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="../assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="../assets/js/light-bootstrap-dashboard.js"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="../assets/js/demo.js"></script>

	

</html>
