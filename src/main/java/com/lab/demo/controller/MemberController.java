package com.lab.demo.controller;

import com.lab.demo.entity.Usr;
import com.lab.demo.repository.UsrRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private UsrRepository usrRepository;

    /**
     * 获取用户列表
     */
    @GetMapping(value = "/members")
    public String showMembers(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model){
        response.setHeader("Access-Control-Allow-Origin","*");

        Object loginUser = request.getSession().getAttribute("usrName");
//        String userName = null;
        if (loginUser!=null){
            String userName = loginUser.toString();
            model.addAttribute("userName",userName);
        }

        List<Usr> doctors = usrRepository.findByAuthorityAndEducationOrderByUsrName(1, "Phd");
        List<Usr> masters = usrRepository.findByAuthorityAndEducationOrderByUsrName(1, "Master");
        List<Usr> undergraduates = usrRepository.findByAuthorityAndEducationOrderByUsrName(1, "Undergraduate");
        model.addAttribute("doctors", doctors);
        model.addAttribute("masters", masters);
        model.addAttribute("undergraduates", undergraduates);

        return "members";
    }

    /**
     * 某个成员详情
     */
    @GetMapping("/single")
    public String showSingleMember(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model){
        response.setHeader("Access-Control-Allow-Origin","*");

        Object loginUser = request.getSession().getAttribute("usrName");
        String userName = null;
        String singleUserName = null;

        if (loginUser!=null){
            userName = loginUser.toString();
            model.addAttribute("userName",userName);
        }

        singleUserName = request.getParameter("id");
        Usr usr = usrRepository.findByUsrName(singleUserName);
        model.addAttribute("usr",usr);

        return "single";
    }

}
