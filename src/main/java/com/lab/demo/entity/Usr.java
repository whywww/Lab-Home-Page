package com.lab.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class Usr implements Serializable{

//    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "usr_name")
    private String usrName;

    private String name;
    private String pswd;
    private Integer authority;
    private String photo;
    private String education;
    private String email;
    private String researchArea;
    private String usrDescribe;

    @Transient
    private List<Project> projects;
    @Transient
    private List<Publication> publications;

    //这里有循环引用的问题！！！
//    @ManyToMany(mappedBy = "usrs")
//    @JsonIgnore
//    private Set<Project> projects = new HashSet<Project>();

    public Usr(){
    }

    public Usr(String usrName, String name, String pswd, Integer authority, String photo, String education, String email, String researchArea, String usrDescribe) {
        this.usrName = usrName;
        this.name = name;
        this.pswd = pswd;
        this.authority = authority;
        this.photo = photo;
        this.education = education;
        this.email = email;
        this.researchArea = researchArea;
        this.usrDescribe = usrDescribe;
//        this.projects = projects;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public String getUsrDescribe() {
        return usrDescribe;
    }

    public void setUsrDescribe(String usrDescribe) {
        this.usrDescribe = usrDescribe;
    }

//    public Set<Project> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(Set<Project> projects) {
//        this.projects = projects;
//    }

//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

    /**
     * 修改个人信息
     */
    public void setInfo(String usrName, Usr usr){
        setUsrName(usrName);
        if(usr.getEducation()!= null) this.education = usr.getEducation();
        if(usr.getEmail() != null) this.email = usr.getEmail();
        if(usr.getResearchArea() != null) this.researchArea = usr.getResearchArea();
        if(usr.getPhoto() != null) this.photo = usr.getPhoto();
        if(usr.getUsrDescribe() != null) this.usrDescribe = usr.getUsrDescribe();

    }

    @Override
    public String toString() {
        return "Usr{" +
                "usrName='" + usrName + '\'' +
                ", name='" + name + '\'' +
                ", pswd='" + pswd + '\'' +
                ", authority=" + authority +
                ", photo='" + photo + '\'' +
                ", education='" + education + '\'' +
                ", email='" + email + '\'' +
                ", researchArea='" + researchArea + '\'' +
                ", usrDescribe='" + usrDescribe + '\'' +
                '}';
    }
}
