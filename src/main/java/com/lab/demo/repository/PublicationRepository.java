package com.lab.demo.repository;

import com.lab.demo.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {
    List<Publication> findByType(String type);
    void deleteByPubId(Integer pubId);
}
