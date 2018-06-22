package com.lab.demo.repository;

import com.lab.demo.entity.PubUsrMultiKeysClass;
import com.lab.demo.entity.PublicationAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationAuthorRepository extends JpaRepository<PublicationAuthor, PubUsrMultiKeysClass> {
    List<PublicationAuthor> findByPubIdOrderByCat(Integer pubId);
    List<PublicationAuthor> findByUsrName(String usrName);
}
