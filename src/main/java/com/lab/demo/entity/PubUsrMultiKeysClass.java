package com.lab.demo.entity;

import java.io.Serializable;

public class PubUsrMultiKeysClass implements Serializable {
    private Integer pubId;
    private String usrName;

    public PubUsrMultiKeysClass() {
    }

    public PubUsrMultiKeysClass(Integer pubId, String usrName) {
        this.pubId = pubId;
        this.usrName = usrName;
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
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
        result = PRIME * result + ((pubId == null) ? 0 : pubId.hashCode());
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

        final PubUsrMultiKeysClass other = (PubUsrMultiKeysClass) obj;
        if(usrName == null){
            if(other.usrName != null){
                return false;
            }
        }else if(!usrName.equals(other.usrName)){
            return false;
        }
        if(pubId == null){
            if(other.pubId != null){
                return false;
            }
        }else if(!pubId.equals(other.pubId)){
            return false;
        }

        return true;
    }
}
