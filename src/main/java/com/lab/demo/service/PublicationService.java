package com.lab.demo.service;

import com.lab.demo.entity.Publication;
import com.lab.demo.entity.PublicationAuthor;
import com.lab.demo.repository.PublicationAuthorRepository;
import com.lab.demo.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationAuthorRepository publicationAuthorRepository;

    @Transactional
    @Modifying
    public boolean addPub(Publication publication,String authorStr){
        boolean flag = false;
        PublicationAuthor publicationAuthor = new PublicationAuthor();
        String author[] = authorStr.split(",");
        try {
            publicationRepository.save(publication);
            publicationAuthor.setPubId(publication.getPubId());
            for (int i = 0; i < author.length; i++) {
                publicationAuthor.setUsrName(author[i].trim());
                publicationAuthor.setCat(i+1);
                publicationAuthorRepository.save(publicationAuthor);
            }
            flag = true;
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return flag;
    }
}
