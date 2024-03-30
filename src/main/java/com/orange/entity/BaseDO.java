package com.orange.entity;

import java.io.Serializable;

public class BaseDO<P> implements Serializable {

    private static final long serialVersionUID = -8612241350220864396L;

    private P id;

    private Long gmtCreate;

    private Long gmtModified;

    public P getId() {
        return id;
    }

    public void setId(P id) {
        this.id = id;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
