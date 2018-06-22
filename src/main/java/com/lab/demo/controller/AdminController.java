package com.lab.demo.controller;

import com.lab.demo.entity.Project;
import com.lab.demo.entity.Publication;
import com.lab.demo.entity.PublicationAuthor;
import com.lab.demo.entity.Usr;
import com.lab.demo.repository.ProjectRepository;
import com.lab.demo.repository.PublicationAuthorRepository;
import com.lab.demo.repository.PublicationRepository;
import com.lab.demo.repository.UsrRepository;
import com.lab.demo.service.AdminService;
import com.lab.demo.service.PublicationService;
import com.lab.demo.util.ImageUpload;
import org.hibernate.annotations.Parameter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.PrintWriter;
import java.util.List;

/**
 * 管理员控制
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private UsrRepository usrRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PublicationAuthorRepository publicationAuthorRepository;
    @Autowired
    private PublicationService publicationService;

    @GetMapping(value = "/admin")
    public String showAdminPage(HttpServletRequest request,
                                HttpServletResponse response,
                                Model model){
        response.setHeader("Access-Control-Allow-Origin","*");
        Object loginUser = request.getSession().getAttribute("usrName");
        if (loginUser!=null){
            String userName = loginUser.toString();
            model.addAttribute("userName",userName);
        }

        // 获取所有普通用户列表
        List<Usr> usrList = usrRepository.findAll();
        model.addAttribute("userList",usrList);
        return "admin";
    }

    /**
     * 添加一个用户
     */
//    @Transactional
//    @ResponseBody
    @PostMapping(value = "/admin/addusr")
    public void addUsr(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) throws Exception{
        JSONObject jsonData = new JSONObject();

        response.setHeader("Access-Control-Allow-Origin","*");

        String usrName = request.getParameter("usrName");
        String name = request.getParameter("name");
        String pswd = request.getParameter("pswd");
        if (usrName.isEmpty()){
            jsonData.put("ok", "false");
        }else {
            Usr usr = new Usr();
            usr.setUsrName(usrName);
            usr.setName(name);
            usr.setPswd(pswd);
            usr.setAuthority(1);

            boolean flag = adminService.addUsr(usr);
    //        System.out.println(flag);
            // 添加成功
            if (flag){
                jsonData.put("ok","true");
                jsonData.put("url","/admin");
            }else {
                jsonData.put("ok","false");
            }
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonData.toString());
        out.flush();
        out.close();
    }

    /**
     * 删除某个用户
     */
    @Transactional
    @ResponseBody
    @PostMapping(value = "/admin/delusr")
    public String delUsr(HttpServletRequest request){
//
        String usrName = request.getParameter("usrName");
        //删除某个用户
        Integer flag = adminService.delUsr(usrName);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", flag);
        return jsonObject.toString();
    }

    /**
     *获取paper和patent分别的列表
     */
    @Transactional
    @GetMapping(value = "/admin/Pubs")
    public String getPaper(Model model){
        List<Publication> paper;
        List<Publication> patent;

        //找到期刊和会议的全部刊物信息
        paper = adminService.findPubAndAuthorsByType("journal");
        paper.addAll(adminService.findPubAndAuthorsByType("conference"));
        //找到专利全部信息
        patent = adminService.findPubAndAuthorsByType("patent");

        model.addAttribute("patents",patent);
        model.addAttribute("papers",paper);

        return "PaperManagement";
    }

    /**
     * 添加publication
     */
//    @Transactional
//    @ResponseBody
    @PostMapping(value = "/admin/addPub")
    public void addPaper(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");

        Publication publication = new Publication();
//        PublicationAuthor publicationAuthor = new PublicationAuthor();

        publication.setPubTitle(request.getParameter("pubTitle"));
        publication.setPubDate(request.getParameter("pubDate"));
        publication.setType(request.getParameter("type"));
        publication.setTypeName(request.getParameter("typeName"));
        publication.setPatentRegion(request.getParameter("patentRegion"));
        publication.setPatentId(request.getParameter("patentId"));
        publication.setPubHref(request.getParameter("pubHref"));
        String authors = request.getParameter("authors");

        boolean flag = publicationService.addPub(publication,authors);
        System.out.println(flag);
        JSONObject jsonData = new JSONObject();
        if (flag == true){
            jsonData.put("ok","true");
            jsonData.put("url","/admin/Pubs");
        }else {
            jsonData.put("ok","false");
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonData.toString());
        out.flush();
        out.close();
//        String author[] = authors.split(",");
//        publicationRepository.save(publication);
//
//        publicationAuthor.setPubId(publication.getPubId());
//        for (int i = 0; i < author.length; i++) {
//            publicationAuthor.setUsrName(author[i].trim());
//            publicationAuthor.setCat(i+1);
//            publicationAuthorRepository.save(publicationAuthor);
//        }
//
//        JSONObject jsonData = new JSONObject();
//        jsonData.put("url","/admin/Pubs");
//
//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(jsonData.toString());
//        out.flush();
//        out.close();
    }


    /**
     * 根据pubId删除publication
     */
    @Transactional
    @PostMapping(value = "/admin/delPub")
    public void deletePub(HttpServletRequest request){
        Integer pubId = Integer.parseInt(request.getParameter("PubId"));
        publicationRepository.deleteByPubId(pubId);
    }

    /**
     * 获取项目列表
     */
    @Transactional
    @GetMapping(value = "/admin/Projs")
    public String getProjects(Model model){

        List<Project> projects = null;
        projects = adminService.findAllProjAndMembers();
        model.addAttribute("projects",projects);

        return "ProjectManagement";
    }

    /**
     * 添加新项目
     */
    @Transactional
    @PostMapping(value = "/admin/addProj")
    public void addProject(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model,
                           @RequestParam("proj-upload") MultipartFile multipartFile) throws Exception{

        //添加项目信息（除了图片）
        Project project = new Project();
        project.setProjName(request.getParameter("projName"));
        project.setProjHref(request.getParameter("projHref"));
        project.setProjDescribe(request.getParameter("projDescribe"));
        projectRepository.save(project);

        // 用项目编号做项目图片名
        Integer proj_id = project.getProjId();
        String directoryPath = "D:\\Lab\\MainPageDemo\\src\\main\\resources\\static\\images\\upload\\proj\\";
        String relativePath = "../static/images/upload/proj/";

        ImageUpload upload = new ImageUpload();
        upload.usrImageUpload(multipartFile, proj_id.toString(), directoryPath);
        projectRepository.updateProjImageByProjId(relativePath + proj_id.toString() + ".jpg", proj_id);

        model.addAttribute("url","/admin/Projs");
        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.flush();
//        out.close();
    }

    /**
     * 删除项目
     */
    @Transactional
    @PostMapping(value = "/admin/delProj")
    public void deleteProject(HttpServletRequest request, HttpServletResponse response){
        Integer projId = Integer.parseInt(request.getParameter("projId"));
        projectRepository.deleteByProjId(projId);
    }

}
