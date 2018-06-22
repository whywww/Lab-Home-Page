package com.lab.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ContactController {

    @GetMapping(value = "/contact")
    public String showContact(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");

        Object loginUser = request.getSession().getAttribute("usrName");
        String userName = null;
        if (loginUser!=null){
            userName = loginUser.toString();
            model.addAttribute("userName",userName);
        }

        return "contact";
    }
}
