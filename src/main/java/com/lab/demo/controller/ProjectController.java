package com.lab.demo.controller;

import com.lab.demo.entity.Project;
import com.lab.demo.service.AdminService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    AdminService adminService = new AdminService();

    @Transactional
    @GetMapping(value = "/projects")
    public String getProjects(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");

        Object loginUser = request.getSession().getAttribute("usrName");
        String userName = null;
        if (loginUser!=null){
            userName = loginUser.toString();
            model.addAttribute("userName",userName);
        }

        List<Project> projects = adminService.findAllProjAndMembers();
        model.addAttribute("projects", projects);

        return "projects";
    }
}
