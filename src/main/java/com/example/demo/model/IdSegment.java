package com.example.demo.model;

import java.util.Date;

/**
 * @program: demo
 * @description:
 * @author: Irving
 * @create: 2019-11-01
 **/
public class IdSegment {

    private Long minId;
    private Long maxId;

    private Integer step;

    private Long middleId;

    private Date lastUpdateTime;
    private Date currentUpdateTime;

    public IdSegment() {

    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCurrentUpdateTime() {
        return currentUpdateTime;
    }

    public void setCurrentUpdateTime(Date currentUpdateTime) {
        this.currentUpdateTime = currentUpdateTime;
    }

    public Long getMiddleId() {

        if (this.middleId == null) {
            this.middleId = this.maxId - (long) Math.round(step / 2);
        }
        return middleId;
    }

    public Long getMinId() {
        if (this.minId == null) {
            if (this.maxId != null && this.step != null) {
                this.minId = this.maxId - this.step;
            } else {
                throw new RuntimeException("maxid or step is null");
            }
        }

        return minId;
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

    @Override
    public String toString() {
        return "IdSegment [minId=" + minId + ", maxId=" + maxId + ", step=" + step + ", middleId=" + middleId
                + ", lastUpdateTime=" + lastUpdateTime + ", currentUpdateTime=" + currentUpdateTime + "]";
    }
}
