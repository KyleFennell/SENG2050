function validateCancellation(securityCodein, userIDin, verificationFieldin){
	var userID = document.getElementById("UserID").value;
	var verificationField = document.getElementById("verificationField").value;
	var securityCode = document.getElementById("SecurityCode").value;

	var alertmsg = "";
	var valid = true;

	if (userID != userIDin){
		alertmsg += "UserID is incorrect\n";
		valid = false;
	}

	if (verificationField != verificationFieldin){
		alertmsg += "varification field does not match the booking\n";
		valid = false;
	}

	if (securityCode != securityCodein){
		alertmsg += "incorrect securityCode\n";
		valid = false;
	}

	if (!valid){
		alert(alertmsg);
	}

	return valid;
}