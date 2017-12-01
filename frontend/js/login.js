

var btn_login = $("#btn_login")

var username_small = $("#username_small")
var password_small = $("#password_small")

var username_input = $("#login_username")
var password_input = $("#login_password")
var username_logged = $("#username_logged")

var login_dialog = $("#login_modal")

var label_incorrect_data = $("#label_incorrect_data")
var lnk_register = $("#lnk_register")
var lnk_login = $("#lnk_login")

var accessToken = null

password_small.hide();
username_small.hide();
label_incorrect_data.hide();


btn_login.click(function() {
	result = check_inputs()
	if(result == true) {
		btn_login.attr("class", "btn btn-outline-primary disabled")
		btn_login.html("Loading..")
		login(username_input.val(), password_input.val())
	}
})

var login = function(username, password) {
	$.ajax({
            type: 'POST',
            url:  "http://localhost:8082/simple_login-1.0-SNAPSHOT/user/login",
            data: {
              "username": username,
              "password": password
            },
            dataType: 'json',
            async: true,
            success: function(result) {
              	if(result != null) {
                 	accessToken = result.accessToken
                 	login_dialog.modal("toggle")
                 	label_incorrect_data.hide();
                 	lnk_login.hide()
                 	lnk_register.hide()
                 	get_username(accessToken)
            	} else {
            		label_incorrect_data.show()
            	}
            	btn_login.attr("class", "btn btn-outline-primary")
            	btn_login.html("Log in")
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	btn_login.attr("class", "btn btn-outline-primary")
            	btn_login.html("Log in")
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
}

var get_username = function(token) {
	$.ajax({
        type: 'GET',
        url:  "http://localhost:8082/simple_login-1.0-SNAPSHOT/user/"+token,
        dataType: 'json',
        async: true,
        success: function(result) {
          	if(result != null) {
             	username_logged.html("Hello, "+result.username)
        	} else {
        		alert(null)
        	}
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

var check_inputs = function () {
	result = -1;
	if(username_input.val().length < 5) {
		username_small.show();
		result--;
	} else {
		username_small.hide();
		result++;
	}

	if(password_input.val().length < 6) {
		password_small.show();
		result--;
	} else {
		password_small.hide();
		result++;
	}

	return result;
}