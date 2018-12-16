package com.sunflower.goku.dubbo.provider.entity;

import com.alibaba.fastjson.JSON;

/**
 * @author fuyongde
 */
public class Region extends BaseEntity {
    private Integer parentId;
    private String name;
    private Integer level;
    private Boolean leaf;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
