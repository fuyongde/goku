package com.goku.dubbo.api.service.dto;

import java.io.Serializable;

/**
 * @author fuyongde
 * @desc 地区信息的数据层传输对象
 * @date 2017/10/30 18:27
 */
public class RegionDTO implements Serializable {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer level;
    private Boolean leaf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegionDTO{");
        sb.append("id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", level=").append(level);
        sb.append(", leaf=").append(leaf);
        sb.append('}');
        return sb.toString();
    }
}
