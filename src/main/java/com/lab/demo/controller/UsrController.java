package com.lab.demo.controller;

import com.lab.demo.entity.Usr;
import com.lab.demo.repository.UsrRepository;
import com.lab.demo.service.LoginService;
import com.lab.demo.util.ImageUpload;
import org.hibernate.annotations.Parameter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 普通用户操作
 */
@Controller
public class UsrController {

    @Autowired
    private UsrRepository usrRepository;

    /**
     * 查询该用户所有信息
     */
    @GetMapping(value = "/usr")
    public String getUsrInfo(HttpServletRequest request,
                             HttpServletResponse response,
                             Model model) throws IOException{
        response.setHeader("Access-Control-Allow-Origin","*");

        Object loginUser = request.getSession().getAttribute("usrName");
        String userName = null;
        if (loginUser!=null){
            userName = loginUser.toString();
            model.addAttribute("userName",userName);
        }

        Usr usr = usrRepository.findByUsrName(userName);
        model.addAttribute("user", usr);
        return "PersonalHome";
    }

    /**
     * 上传照片
     */

//    @Transactional
//    @PostMapping(value = "/usr/usr-img-upload")
//    public void usrImageUpload(HttpServletRequest request,
//                               HttpServletResponse response,
//                               @RequestParam("pic") MultipartFile multipartFile) throws IOException{
//
//        String usrName = request.getSession().getAttribute("usrName").toString();
//        String directoryPath = "D:\\Lab\\MainPageDemo\\src\\main\\resources\\static\\images\\upload\\"; // 物理地址
//        String relativePath = "../static/images/upload/";
////        String directoryPath = request.getSession().getServletContext().getRealPath("../static/images/upload/"); //本地项目路径
//
//        JSONObject jsonObject = new JSONObject();
//
//        //存图
//        try{
//            BufferedOutputStream out = new BufferedOutputStream(
//                    new FileOutputStream(new File(directoryPath + usrName + ".jpg")));//保存图片到目录下
//            out.write(multipartFile.getBytes());
//            out.flush();
//            out.close();
//        }catch (IOException e){
//            e.getLocalizedMessage();
//            e.printStackTrace();
//            jsonObject.put("ok",false);
//        }
//        // updatePhotoByUsrName
//        usrRepository.updatePhotoByUsrName(relativePath + usrName + ".jpg", usrName);
//
//        jsonObject.put("url","/usr");
//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(jsonObject.toString());
//        out.flush();
//        out.close();
//    }

    @Transactional
    @PostMapping(value = "/usr/usr-img-upload")
    public void usrImageUpload(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("pic") MultipartFile multipartFile)throws IOException{

        String usrName = request.getSession().getAttribute("usrName").toString();
        String directoryPath = "D:\\Lab\\MainPageDemo\\src\\main\\resources\\static\\images\\upload\\"; // 物理地址
        String relativePath = "../static/images/upload/"; // 服务器上相对地址

        ImageUpload imageUpload = new ImageUpload();
        imageUpload.usrImageUpload(multipartFile, usrName, directoryPath);

        usrRepository.updatePhotoByUsrName(relativePath + usrName + ".jpg", usrName);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url","/usr");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }


    /**
     * 修改用户个人信息/密码
     */
    @Transactional
    @Modifying
    @PostMapping(value = "/usr/editPswd")
    public void editUsrPswd(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();

        Object usrName = request.getSession().getAttribute("usrName");
        String pswd = request.getParameter("pswd");
        usrRepository.updatePswdByUsrName(pswd, usrName.toString());

        jsonObject.put("url","/usr");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 修改学历
     */
    @Transactional
    @Modifying
    @PostMapping(value = "/usr/editEdu")
    public void editUsrEducation(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();

        Object usrName = request.getSession().getAttribute("usrName");
        String education = request.getParameter("education");
        usrRepository.updateEducationByUsrName(education, usrName.toString());

        jsonObject.put("url","/usr");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 修改邮箱
     */
    @Transactional
    @Modifying
    @PostMapping(value = "/usr/editEmail")
    public void editUsrEmail(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();

        Object usrName = request.getSession().getAttribute("usrName");
        String email = request.getParameter("email");
        usrRepository.updateEmailByUsrName(email, usrName.toString());

        jsonObject.put("url","/usr");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 修改研究领域
     */
    @Transactional
    @Modifying
    @PostMapping(value = "/usr/editArea")
    public void editUsrArea(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();

        Object usrName = request.getSession().getAttribute("usrName");
        String area = request.getParameter("area");
        usrRepository.updateAreaByUsrName(area, usrName.toString());

        jsonObject.put("url","/usr");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }

    /**
     * 修改简介
     */
    @Transactional
    @Modifying
    @PostMapping(value = "/usr/editDesc")
    public void editUsrDescribe(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setHeader("Access-Control-Allow-Origin","*");
        JSONObject jsonObject = new JSONObject();

        Object usrName = request.getSession().getAttribute("usrName");
        String describe = request.getParameter("describe");
        usrRepository.updateDescByUsrName(describe, usrName.toString());

        jsonObject.put("url","/usr");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }

}
