package com.sunflower.goku.dubbo.consumer.rest.vo;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @author fuyongde
 * @desc 地区的展示层对象
 * @date 2017/10/30 18:42
 */
public class RegionVO implements Serializable {
    private Long id;
    private Integer parentId;
    private String name;
    private Integer level;
    private Boolean leaf;
    private List<RegionVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<RegionVO> getChildren() {
        return children;
    }

    public void setChildren(List<RegionVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
