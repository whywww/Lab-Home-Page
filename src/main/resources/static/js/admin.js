function submit() {
    var name = document.getElementById("add-name").value;
    var realName = document.getElementById("add-real-name").value;
    var pswd = document.getElementById("add-password").value;

    var isAdmin = 1;

    $.ajax({
        type: "post",
        url: "/admin/addusr",
        dataType: 'json',
        data: {
            "usrName": name,
            "name": realName,
            "pswd": pswd
        },
        success: function(data) {
            if (data.ok == "true"){
                alert("add success");
                window.location.href = data.url;
            } else {
                alert("add user error");
            }
        },
        error: function(data) {
            alert("connection error");
        }
    });
}

//paper management
function show_mask() {
    var height = window.innerHeight;
    var width = window.innerWidth;

    var div = document.getElementById("mask");
    div.setAttribute("height", height);
    div.setAttribute("width", width);
    div.style.display = "block";
}

function hide_mask() {
    var div = document.getElementById("mask");
    div.style.display = "none";
}

function in_center(divName) {
    var top = ($(window).height() - $(divName).height()) / 2;
    var left = ($(window).width() - $(divName).width()) / 2;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $(divName).css({
        position: 'absolute',
        top: top + scrollTop,
        left: left + scrollLeft
    }).show();
}

function hide_pop(divId) {
    //清空表单
    $("input[type=reset]").trigger("click");
    //隐藏表单
    var div = document.getElementById(divId);
    div.style.display = "none";
}

function add_paper(divName) {
    show_mask();
    in_center(divName);
}

function add_patent(divName) {
    show_mask();
    in_center(divName);
}

function paper_submit(divId) {
    var title = document.getElementById("paper-title").value;
    var authors = document.getElementById("authors").value;
    var year = document.getElementById("paper-year").value;
    var type = document.getElementById("paper-type").value;
    var published = document.getElementById("paper-published-on").value;
    var pub_url = document.getElementById("paper-published-url").value;

    // var auArr = authors.split(",");
    // alert(authors);
    // alert(auArr[1]);
    // cancel(divId);

        $.ajax({
            type: "post",
            url: "/admin/addPub",
            dataType: "json",
            data: {
                "pubTitle": title,
                "authors": authors,
                "pubDate": year,
                "type": type,
                "typeName": published,
                "pubHref": pub_url,
            },
            success: function(data) {
                // alert("add success");
                // window.location.href = data.url;
                if (data.ok == "true"){
                    alert("add success");
                    window.location.href = data.url;
                    // cancel(divId);
                } else {
                    alert("add publications error");
                    in_center(divId);
                }
            },
            error: function(data) {
                alert("connection error");
                in_center(divId);
            }
        });
}

function patent_submit(divId) {
    var title = document.getElementById("patent-title").value;
    var number = document.getElementById("patent-no").value;
    var patentee = document.getElementById("patentee").value;
    var inventor = document.getElementById("inventor").value;
    var year = document.getElementById("patent-year").value;
    var region = document.getElementById("region").value;

    var authors = inventor+','+patentee;
    //alert(patentee);
    cancel(divId);

        $.ajax({
            type: "post",
            url: "/admin/addPub",
            dataType: "json",
            data: {
                "pubTitle": title,
                "authors": authors,
                "pubDate": year,
                "type":"patent",
                "patentId": number,
                "patentRegion": region,
            },
            success: function(data) {
                if (data.ok == "true"){
                    alert("add success");
                    window.location.href = data.url;
                    // cancel(divId);
                } else {
                    alert("add publications error");
                    in_center(divId);
                }
                // cancel(divId);
            },
            error: function(data) {
                alert("add error");
                in_center(divId);
            }
        });
}

function cancel(divId) {
    //var id = "#" + divId;
    //document.getElementById(divId).form("clear");
    //$(divId).form("clear");
    hide_mask();
    hide_pop(divId);
}

// Project Management
function pro_submit() {
    var pro_name = document.getElementById("add-pro-name").value;
    var pro_url = document.getElementById("add-pro-link").value;
    var pro_desc = document.getElementById("add-pro-desc").value;
    var pro_pic = document.getElementById("add-pro-pic").value;

    // 图片路径不能为空
    if (pro_pic == null || pro_pic == "" || pro_pic == undefined){
        alert("Please upload an image file!");
        return false;
    }
    var fileType = pro_pic.substr(pro_pic.lastIndexOf('.')+1);
    if (fileType == 'jpg' || fileType == 'png' || fileType == 'bmp'){
        $("#proj-upload").ajaxSubmit({
            type:'post',
            // url:'/usr/usr-img-upload',
            success:function (result) {
                alert("Add success");
                window.location.href = result.url;
            },
            error:function (result) {
                alert("Add failure!");
            }
        })
    }else{
        alert("Please upload an IMAGE file!");
        return false;
    }

        // $.ajax({
        //     type: "post",
        //     url: "/admin/addProj",
        //     dataType: 'json',
        //     data: {
        //         "projName": pro_name,
        //         "projHref": pro_url,
        //         //"proj_image":"",
        //         "projDescribe": pro_desc
        //     },
        //     success: function(data) {
        //         alert("add success");
        //         window.location.href = data.url;
        //     },
        //     error: function(data) {
        //         alert("add error");
        //     }
        // });
}

// Personal Home
function modify_img(divName) {
    show_mask();
    in_center(divName);
}

function photo_submit(divId) {
    var photoPath = document.getElementById("new-photo").value;
    //alert(photoPath);
    // 不能为空
    if (photoPath == null || photoPath == "" || photoPath == undefined){
        alert("Please upload an image file!");
        return false;
    }
    var fileType = photoPath.substr(photoPath.lastIndexOf('.')+1);
    if (fileType == 'jpg' || fileType == 'png' || fileType == 'bmp'){
        // 格式正确
        $("#upload").ajaxSubmit({
            type:'post',
            url:'/usr/usr-img-upload',
            success:function (result) {
                alert("Modify success");
                window.location.href = result.url;
            },
            error:function (result) {
                alert("Modify error!");
                in_center(divId);
            }
        })
    } else {
        alert("Please upload an IMAGE file!");
        return false;
    }
}

function modify_pwd(divName) {
    show_mask();
    in_center(divName);
}

function pwd_submit(divId) {
    var new_pwd = document.getElementById("new-pwd").value;
    var confirm = document.getElementById("confirm-pwd").value;

    if (new_pwd != confirm) {
        alert("Passwords don't match!");
        return;
    }
        $.ajax({
            type: "post",
            url: "/usr/editPswd",
            dataType: "json",
            data: {
                "pswd": new_pwd
            },
            success: function(data) {
                alert("Modify success");
                window.location.href = data.url;
                // cancel(divId);
            },
            error: function(data) {
                alert("Modify failure");
                in_center(divId);
            }
        });
}

// modify title
function modify_title(divName) {
    show_mask();
    in_center(divName);
}

function title_submit(divId) {
    var new_title = document.getElementById("new-title").value;

    //不能为空
    if (!new_title) {
        alert("Please input your new title.");
        return;
    }

        $.ajax({
            type: "post",
            url: "/usr/editEdu",
            dataType: "json",
            data: {
                "education": new_title
            },
            success: function(data) {
                alert("Modify success");
                window.location.href = data.url;
                // cancel(divId);
            },
            error: function(data) {
                alert("Modify failure");
                in_center(divId);
            }
        });
}

// modify email
function modify_email(divName) {
    show_mask();
    in_center(divName);
}

function email_submit(divId) {
    var new_email = document.getElementById("new-email").value;
    var emailPattern = /\b(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/;

    //不能为空
    if (!new_email) {
        alert("Please input your new email.");
        return;
    }
    //原则上还要检验一下邮箱格式，用正则表达式
    if (!emailPattern.test(new_email)) {
        alert("Please input a valid eamil address.");
        return;
    }

        $.ajax({
            type: "post",
            url: "/usr/editEmail",
            dataType: "json",
            data: {
                "email": new_email
            },
            success: function(data) {
                alert("Modify success");
                window.location.href = data.url;
                // cancel(divId);
            },
            error: function(data) {
                alert("Modify failure");
                in_center(divId);
            }
        });
}

//modify research area
function modify_area(divName) {
    show_mask();
    in_center(divName);
}

function area_submit(divId) {
    var new_area = document.getElementById("new-area").value;

    //不能为空
    if (!new_area) {
        alert("Please input your new research area.");
        return;
    }

        $.ajax({
            type: "post",
            url: "/usr/editArea",
            dataType: "json",
            data: {
                "area": new_area
            },
            success: function(data) {
                alert("Modify success");
                window.location.href = data.url;
                // cancel(divId);
            },
            error: function(data) {
                alert("Modify failure");
                in_center(divId);
            }
        });
}

//modify introduction
function modify_intro(divName) {
    show_mask();
    in_center(divName);
}

function intro_submit(divId) {
    var new_intro = document.getElementById("new-intro").value;
    var usr_name;

    //不能为空
    if (!new_intro) {
        alert("Please input your new introduction.");
        return;
    }

    $.ajax({
        type: "post",
        url: "/usr/editDesc",
        dataType: "json",
        data: {
            "describe": new_intro
        },
        success: function(data) {
            alert("Modify success");
            window.location.href = data.url;
            // cancel(divId);
        },
        error: function(data) {
            alert("Modify failure");
            in_center(divId);
        }
    });
}

function logout() {
    var currentUrl = window.location.pathname;
    // alert(currentUrl);
    $.ajax({
        type: "post",
        url: "/logout",
        dataType: "json",
        data: {
            "currentUrl": currentUrl
        },
        success: function(data) {
            alert("Log out success");
            window.location.href = data.url;
        },
        error: function(data) {
            alert("Log out failure");
        }
    });
}

function splogout() {
    $.ajax({
        type: "post",
        url: "/logout",
        dataType: "json",
        data: {
            "currentUrl": "/admin"
        },
        success: function(data) {
            alert("Log out success");
            window.location.href = data.url;
        },
        error: function(data) {
            alert("Log out failure");
        }
    });
}