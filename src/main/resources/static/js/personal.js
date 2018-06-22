function submit() {
    var name = document.getElementById("add-name").value;
    var realName = document.getElementById("add-real-name").value;
    var pswd = document.getElementById("add-password").value;
    alert("real name is" + realName);

    //新增realName 180506
    $.ajax({
        type: "post",
        url: "/login",
        dataType: 'json',
        data: {
            "usrName": name,
            "name": realName,
            "pswd": pswd
        },
        success: function(data) {
            alert("login success");
            alert(data.name);
        },
        error: function(data) {
            alert("login error");
        }
    });
}