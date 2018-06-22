package com.lab.demo.service;

import com.lab.demo.entity.*;
import com.lab.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UsrRepository usrRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PublicationAuthorRepository publicationAuthorRepository;
    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Transactional
    @Modifying
    public boolean addUsr(Usr usr){
        boolean flag = false;
        try{
            usrRepository.save(usr);
            flag = true;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return flag;
    }

    @Transactional
    @Modifying
    public int delUsr(String usrName){
        Integer flag = 0;
        try{
            usrRepository.deleteById(usrName);
            flag = 1;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return flag;
    }

    @Transactional
    @Modifying
    public int addPaper(Publication publication){
        Integer flag = 0;
        try{
//            publicationRepository.save(publication); //需要重写
            flag = 1;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return flag;
    }

    /**
     * 找到所有publication数据，包括作者
     */
    @Transactional
    public List<Publication> findPubAndAuthorsByType(String type){
        List<Publication> publications = publicationRepository.findByType(type);

        for(int i = 0; i < publications.size(); i++){
            List<String> authorNameList = new ArrayList<String>(); //列表格式的作者
            String authorNameString = ""; //逗号分割的字符串形式的作者

            Integer pubId = publications.get(i).getPubId();
            publications.get(i).setAuthors(publicationAuthorRepository.findByPubIdOrderByCat(pubId));
            List<PublicationAuthor> authorList = publications.get(i).getAuthors(); // 这里列表中每个元素为一个PublicationAuthor对象

            for (int j = 0; j < authorList.size(); j++){
                String usrName = authorList.get(j).getUsrName();
                String name = usrRepository.findByUsrName(usrName).getName(); // 找到作者真实姓名
                authorList.get(j).setName(name);

                if (authorNameString.isEmpty()){ //字符串开始不用逗号
                    authorNameString = name;
                }else{
                    authorNameString += ", " + name;
                }

                authorNameList.add(name);
                publications.get(i).setAuthorNameList(authorNameList);
                publications.get(i).setAuthorNameString(authorNameString);
            }
        }
        return publications;
    }

    /**
     * 找到所有project数据，包括成员
     */
    @Transactional
    public List<Project> findAllProjAndMembers(){
        List<Project> projects = projectRepository.findAll();

        for(int i = 0; i < projects.size(); i++){
            Integer projId = projects.get(i).getProjId();
            projects.get(i).setUsrs(projectMemberRepository.findByProjId(projId));
            List<ProjectMember> memberList = projects.get(i).getUsrs();
            for (int j = 0; j < memberList.size(); j++){
                String usrName = memberList.get(j).getUsrName();
                Usr usr = usrRepository.findByUsrName(usrName);
                memberList.get(j).setName(usr.getName());
            }
        }
        return projects;
    }

}
