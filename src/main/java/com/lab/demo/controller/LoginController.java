package com.lab.demo.controller;

import com.lab.demo.entity.Usr;
import com.lab.demo.service.LoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 请求登录页面
     * GET
     */
    @GetMapping(value = "/login")
    public String showLogin(HttpServletRequest request, HttpServletResponse response){
        Object usrName = request.getSession().getAttribute("usrName");
        Object authority = request.getSession().getAttribute("authority");
        if (usrName != null){
            if (authority.equals(0)){
                // 管理员
                return "redirect:/admin";
            }else{
                // 普通用户
                return "redirect:/usr";
            }
        }
        // 未登录
        return "login";
    }

    /**
     * 登录验证
     * POST
     */
    @ResponseBody
    @PostMapping("/login")
    public String doLogin(HttpServletRequest request, HttpServletResponse response){
        Object usr = request.getSession().getAttribute("usrName");
        JSONObject jsonData = new JSONObject();

        if (usr == null){
            response.setHeader("Access-Control", "*"); //??????
            String usrName = request.getParameter("usrName");
            String pswd = request.getParameter("pswd");

            if (usrName.equals("")) { //用户名为空
                jsonData.put("flag", 0);
                jsonData.put("msg", "Empty username!");
//            jsonData.put("data", "");
            }
            else {
                Usr usrData = loginService.validLogin(usrName, pswd); //判断并返回查询数据

                if (usrData != null) { //登录成功
                    //向前端返回登录信息
                    jsonData.put("flag", 1);
                    jsonData.put("msg", "Login success!");
                    jsonData.put("data", usrData);
                    request.getSession().setAttribute("usrName", usrName); //维护登录状态
                    if (usrData.getAuthority() == 1){
                        jsonData.put("url", "/usr");
                        request.getSession().setAttribute("authority", 1);
                    }else {
                        jsonData.put("url", "/admin");
                        request.getSession().setAttribute("authority", 0);
                    }
                }
                else { //登录失败
                    //向前端返回错误信息
                    jsonData.put("flag", 0);
                    jsonData.put("msg", "Wrong username or password!");
//                jsonData.put("data", "");
                }
            }
            response.setContentType("application/json;charset=UTF-8");
        }else {
            jsonData.put("flag",0);
            jsonData.put("msg", "您已经登录！");
        }
        return jsonData.toString();
    }

    /**
     * 注销账户
     */
//    @Transactional
//    @ResponseBody
    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request,
                         HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
//        Object loginUser = request.getSession().getAttribute("usrName");
        String url = request.getParameter("currentUrl");

        JSONObject jsonData = new JSONObject();

        request.getSession().removeAttribute("usrName");
        jsonData.put("url",url);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonData.toString());
        out.flush();
        out.close();

//        try {
//            if (usrName!=null){
//                request.removeAttribute(usrName.toString());
//                return "login";
//            }else{
//                jsonObject.put("msg", "当前非登录状态！");
//            }
//        }catch (Exception e){
//            jsonObject.put("msg", "注销失败！");
//        }
    }
}
