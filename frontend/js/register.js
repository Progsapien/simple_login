
var username_register = $("#register_username")
var password_register = $("#register_password")
var fistname_register = $("#register_firstname")
var lastname_register = $("#register_lastname")
var email_register = $("#register_email")

var btn_register = $("#btn_register")

var username_small_regiser = $("#username_small_register")
var password_small_register = $("#password_small_register")
var fistname_small_register = $("#firstname_small_register")
var lastname_small_register = $("#lastname_small_register")
var email_small_register = $("#email_small_register")
var label_username_incorrect = $("#label_username_incorrect")
var label_username_success = $("#label_username_success")


username_small_regiser.hide()
password_small_register.hide()
fistname_small_register.hide()
lastname_small_register.hide()
email_small_register.hide()

label_username_success.hide()
label_username_incorrect.hide()

btn_register.click(function() {
    if(check_register_inputs() == true) {
        btn_register.html("Loading..")
        btn_register.attr("class", "btn btn-outline-success disabled")
        register(username_register.val(), password_register.val(), fistname_register.val(), lastname_register.val(), email_register.val())
    }
})


var register = function(username, password, firstname, lastname, email) {
	$.ajax({
            type: 'POST',
            url:  "http://localhost:8082/simple_login-1.0-SNAPSHOT/user/register",
            data: {
              "username": username,
              "password": password,
              "email": email,
              "firstname": firstname,
              "lastname": lastname
            },
            dataType: 'json',
            async: true,
            success: function(result) {
              	if(result != null) {
                    label_username_success.show()
                    label_username_incorrect.hide()
            	} else {
                    label_username_success.hide()
            		label_username_incorrect.show()
            	}
                btn_register.html("Register")
                btn_register.attr("class", "btn btn-outline-success")
            },
            error: function(jqXHR, textStatus, errorThrown) {
                btn_register.html("Register")
                btn_register.attr("class", "btn btn-outline-success")
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
}

var check_register_inputs = function() {
    var result = -4
    if(username_register.val().length < 5) {
        username_small_regiser.show()
        result--
    } else {
        username_small_regiser.hide()
        result++
    }

    if(password_register.val().length < 6) {
        password_small_register.show()
        result--
    } else {
        password_small_register.hide()
        result++
    }

    if(fistname_register.val().length == 0) {
        fistname_small_register.show()
        result--
    } else {
        fistname_small_register.hide()
        result++
    }

    if(lastname_register.val().length == 0) {
        lastname_small_register.show()
        result--
    } else {
        lastname_small_register.hide()
        result++
    }

    if(email_register.val().length == 0) {
        email_small_register.show()
        result--
    } else {
        email_small_register.hide()
        result++
    }

    return result
}