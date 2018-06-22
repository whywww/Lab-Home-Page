function submit(){
    
    var name = document.getElementById("mem-name").value;
    var pswd = document.getElementById("password").value;
    $.ajax({
        type:"post",
        url:"/login",
        dataType:'json',
        data:{
            "usrName":name,
            "pswd":pswd
        },
        success:function(data){
            // if (data.flag){
            //     alert("login success");
            //     window.location.href = "/admin";
            // }else {
            //     alert("login error");
            // }
            if(data.flag){
                window.location.href = data.url;
            }else {
                alert("login error");
            }
        },
        error:function(data){
            alert("connection error");
        }
    });
}