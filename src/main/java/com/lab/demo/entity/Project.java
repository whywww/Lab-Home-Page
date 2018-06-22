package com.lab.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_id")
    private Integer projId;
    private String projName;
    private String projHref;
    private String projImage;
    @Lob//text
    @Column(columnDefinition="text")
    private String projDescribe;
    @Transient
    private List<ProjectMember> usrs;

    public Project() {
    }

    public Project(String projName, String projHref, String projImage, String projDescribe, List<ProjectMember> usrs) {
        this.projName = projName;
        this.projHref = projHref;
        this.projImage = projImage;
        this.projDescribe = projDescribe;
        this.usrs = usrs;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjHref() {
        return projHref;
    }

    public void setProjHref(String projHref) {
        this.projHref = projHref;
    }

    public String getProjImage() {
        return projImage;
    }

    public void setProjImage(String projImage) {
        this.projImage = projImage;
    }

    public String getProjDescribe() {
        return projDescribe;
    }

    public void setProjDescribe(String projDescribe) {
        this.projDescribe = projDescribe;
    }

    public List<ProjectMember> getUsrs() {
        return usrs;
    }

    public void setUsrs(List<ProjectMember> usrs) {
        this.usrs = usrs;
    }
}
