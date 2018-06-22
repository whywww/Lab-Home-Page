package com.lab.demo.repository;

import com.lab.demo.entity.Usr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsrRepository extends JpaRepository<Usr, String>{
    Usr findByUsrName(String usrName);
    List<Usr> findByAuthorityAndEducationOrderByUsrName(Integer authority, String education);

    @Modifying
    @Query(value = "update Usr set pswd = ?1 where usrName = ?2")
    void updatePswdByUsrName(String pswd, String usrName);

    @Modifying
    @Query(value = "update Usr set education = ?1 where usrName = ?2")
    void updateEducationByUsrName(String edu, String usrName);

    @Modifying
    @Query(value = "update Usr set email = ?1 where usrName = ?2")
    void updateEmailByUsrName(String email, String usrName);

    @Modifying
    @Query(value = "update Usr set researchArea = ?1 where usrName = ?2")
    void updateAreaByUsrName(String area, String usrName);

    @Modifying
    @Query(value = "update Usr set usrDescribe = ?1 where usrName = ?2")
    void updateDescByUsrName(String describe, String usrName);

    @Modifying
    @Query(value = "update Usr set photo = ?1 where usrName = ?2")
    void updatePhotoByUsrName(String path, String usrName);
}
