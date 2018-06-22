package com.lab.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "publicationauthor")
@IdClass(PubUsrMultiKeysClass.class)
public class PublicationAuthor implements Serializable{
    private Integer pubId;
    private String usrName;
    private Integer cat;
    @Transient
    private String name;

    @Id
    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    @Id
    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
