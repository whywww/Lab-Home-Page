package com.lab.demo.controller;

import com.lab.demo.entity.Publication;
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
public class PublicationController {

    @Autowired
    AdminService adminService = new AdminService();

    /**
     *获取paper和patent分别的列表
     */
    @Transactional
//    @ResponseBody
    @GetMapping(value = {"/publications"})
    public String getPaper(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");

        Object loginUser = request.getSession().getAttribute("usrName");
        String userName = null;
        if (loginUser!=null){
            userName = loginUser.toString();
            model.addAttribute("userName",userName);
        }

        //找到期刊和会议的全部刊物信息
        List<Publication> paper = adminService.findPubAndAuthorsByType("journal");
        paper.addAll(adminService.findPubAndAuthorsByType("conference"));
        //找到专利全部信息
        List<Publication> patent = adminService.findPubAndAuthorsByType("patent");

        model.addAttribute("papers", paper);
        model.addAttribute("patents", patent);

        return "publications";
    }



}
