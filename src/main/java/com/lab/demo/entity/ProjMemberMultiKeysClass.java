package com.lab.demo.entity;

import java.io.Serializable;

public class ProjMemberMultiKeysClass implements Serializable {
    private Integer projId;
    private String usrName;

    public ProjMemberMultiKeysClass() {
    }

    public ProjMemberMultiKeysClass(Integer projId, String usrName) {
        this.projId = projId;
        this.usrName = usrName;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    @Override
    public int hashCode(){
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((usrName == null) ? 0 : usrName.hashCode());
        result = PRIME * result + ((projId == null) ? 0 : projId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final ProjMemberMultiKeysClass other = (ProjMemberMultiKeysClass) obj;
        if(usrName == null){
            if(other.usrName != null){
                return false;
            }
        }else if(!usrName.equals(other.usrName)){
            return false;
        }
        if(projId == null){
            if(other.projId != null){
                return false;
            }
        }else if(!projId.equals(other.projId)){
            return false;
        }

        return true;
    }
}
