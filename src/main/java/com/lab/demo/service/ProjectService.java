package com.lab.demo.service;

import com.lab.demo.entity.Project;
//import com.lab.demo.repository.ProjectmemberRepository;
import com.lab.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
//    private ProjectmemberRepository projectmemberRepository;

    @Transactional
    public List<Project> getProjects(){
        List<Project> projects = projectRepository.findAll();
//        for (int i = 0; i<projects.size(); i++){
//            Integer projId = projects.get(i).getProjId();
//            projectmemberRepository
//        }
        return null;
    }
}
