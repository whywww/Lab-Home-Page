package com.lab.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projectmember")
@IdClass(ProjMemberMultiKeysClass.class)
public class ProjectMember implements Serializable {
    private Integer projId;
    private String usrName;
    @Transient
    private String name;

    @Id
    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    @Id
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
}
