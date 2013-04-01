<!DOCTYPE html>

<head>
  <title>Restaurant App Password Reset</title>
<script type='text/javascript'>
function submitForm(e) {
    var np = e.newpassword.value;
    var cp = e.confirm.value;
    var dm = document.getElementById('message');
    if(np != cp) {
	document.getElementById('message').innerHTML='Passwords do not match.';
	console.log("failed");
	return false;
    }
    if(np.length < 6) {
	dm.innerHTML = 'Password must be at least six characters.';
	return false;
    }
    return true;
}
</script>
</head>

<body>
   Password Reset<br /><br />

<form method=POST action='PWReset_Page2.php'>
<label for='newpassword'>New Password:</label>
<input type='password' name='newpassword' /><br /></br />
<label for='confirm'>Confirm:</label>
<input type='password' name='confirm' /><br /><br />
<input type='hidden' name='username' value='<?php echo $pwreset ?>' />
<input type='hidden' name='hp' value='<?php echo $hp ?>' />
<div id='message' style='color: #FF0000'></div><br /><br />

<input type='submit' onclick='return submitForm(this.form)' />
</form>
</body>
   
  
