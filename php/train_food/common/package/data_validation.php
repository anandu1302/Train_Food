<script type="text/javascript" src="jquery_002.js"></script>
<script type="text/javascript" src="jquery.validate.min.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
jQuery.validator.addMethod("notEqual", function(value, element, param) {return this.optional(element) || value != param;}, "Please enter your name");

$.validator.addMethod("pwcheck",
function(value, element) {
   return /^[A-Za-z0-9\d=!\-@._*]+$/.test(value);
});

$(document).ready(function() {jQuery.validator.addMethod("lettersonly", function(value, element) {
  return this.optional(element) || /^[a-z ]+$/i.test(value);
}, "Letters only please");

			$("#register_form").validate({
				submitHandler:function(form) 
				{
					SubmittingForm();
				},
				rules: 
				{
					course: 
					{
						required: true,
			
					},// simple rule, converted to {required:true}
					duration: 
					{
						required: true,
						number: true			
					},
					start_time: 
					{
						required: true
					},
					end_time: 
					{
						required: true
					},
					
					strength: 
					{
						required: true,
						number: true,
						rangelength: [1, 2]			
					},
					package_name:
					{
						required: true
					},
					tutor:
					{
						required: true
					},
					amount:
					{
						required: true,
						number: true,
					},
					
					},
					messages: 
					{
						comment: "Please enter a comment.",
						password2: "Password Doesn't Matched"
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

 


