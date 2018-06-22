function test_pub() {
    //alert("test onload");

    $.ajax({
        type: "get",
        url: "/getPubs",
        dataType: 'text',
        data: {

        },
        success: function(data) {
            alert("login success");
            //alert(data.name);
            // console.log("get publications success");
        },
        error: function(data) {
            alert("get publications error");
        }
    });
}