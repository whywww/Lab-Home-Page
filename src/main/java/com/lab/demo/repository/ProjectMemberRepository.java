package com.lab.demo.repository;

import com.lab.demo.entity.ProjMemberMultiKeysClass;
import com.lab.demo.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjMemberMultiKeysClass> {
    List<ProjectMember> findByProjId(Integer projId);
}
