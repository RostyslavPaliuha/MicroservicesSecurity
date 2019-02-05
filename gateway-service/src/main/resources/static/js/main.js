$(document).ready(function () {

    if (localStorage.getItem("token") === null) {

        $("#container").load("./component/login-component.html");


    } else {
        $("#container").load("./component/home-component.html");
    }


});