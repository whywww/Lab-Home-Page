package com.lab.demo.repository;

import com.lab.demo.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    List<Project> findAllByOrderByProjIdAsc();
    void deleteByProjId(Integer projId);
    @Modifying
    @Query("update Project set projImage = ?1 where projId = ?2")
    void updateProjImageByProjId(String projImage, Integer projId);
}
