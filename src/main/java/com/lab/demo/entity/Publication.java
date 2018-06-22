package com.lab.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Publication {
    @Id
    @GeneratedValue
    private Integer pubId;

    private String pubTitle;
    private String pubDate;
    private String type;
    private String typeName;
    private String patentRegion;
    private String patentId;
    private String pubHref;
    @Transient
    private List<PublicationAuthor> authors;
    @Transient
    private List<String> authorNameList;
    @Transient
    private String authorNameString;

    public Publication() {
    }

    public Publication(String pubTitle, String pubDate, String type, String typeName, String patentRegion, String patentId, String pubHref, List<PublicationAuthor> authors, List<String> authorNameList, String authorNameString) {
        this.pubTitle = pubTitle;
        this.pubDate = pubDate;
        this.type = type;
        this.typeName = typeName;
        this.patentRegion = patentRegion;
        this.patentId = patentId;
        this.pubHref = pubHref;
        this.authors = authors;
        this.authorNameList = authorNameList;
        this.authorNameString = authorNameString;
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public String getPubTitle() {
        return pubTitle;
    }

    public void setPubTitle(String pubTitle) {
        this.pubTitle = pubTitle;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPatentRegion() {
        return patentRegion;
    }

    public void setPatentRegion(String patentRegion) {
        this.patentRegion = patentRegion;
    }

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public String getPubHref() {
        return pubHref;
    }

    public void setPubHref(String pubHref) {
        this.pubHref = pubHref;
    }

    public List<PublicationAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<PublicationAuthor> authors) {
        this.authors = authors;
    }

    public List<String> getAuthorNameList() {
        return authorNameList;
    }

    public void setAuthorNameList(List<String> authorNameList) {
        this.authorNameList = authorNameList;
    }

    public String getAuthorNameString() {
        return authorNameString;
    }

    public void setAuthorNameString(String authorNameString) {
        this.authorNameString = authorNameString;
    }
}
