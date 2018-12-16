package com.sunflower.goku.dubbo.provider.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/1 15:36
 * @desc 实体类的基类
 */
public abstract class BaseEntity implements Serializable {
    private Long id;

    private Boolean deleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
