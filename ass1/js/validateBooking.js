function validateBooking(correctSecurityCode){
	var userID =document.getElementById("UserID").value;
	var phone = document.getElementById("Phone").value;
	var address = document.getElementById("Address").value;
	var email = document.getElementById("Email").value;
	var securityCode = document.getElementById("SecurityCode").value;

	var digitCheck = /\d/;
	var emailCheck = /[@]/;
	var alertmsg = "";
	var valid = true;

	if (!userID || userID == ""){
		alertmsg += "UserID is required\n";
		valid = false;
	}

	if (digitCheck.test(userID)){
		alertmsg += "UserID must not contain any numbers\n";
		valid = false;
	}

	if (!emailCheck.test(email)){
		alertmsg += "email must contain an @ symbol\n";
		valid = false;
	}

	if (securityCode !== correctSecurityCode){
		alertmsg += "incorrect securityCode\n";
		valid = false;
	}

	if (!valid){
		alert(alertmsg);
	}
	
	return valid;
}