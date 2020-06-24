package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class IdRecord implements Serializable {
    private String bizTag;

    private Long maxId;

    private Integer step;

    private String description;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getBizTag() {
        return bizTag;
    }

    public void setBizTag(String bizTag) {
        this.bizTag = bizTag == null ? null : bizTag.trim();
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}