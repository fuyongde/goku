package com.goku.dubbo.consumer.rest.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author fuyongde
 * @desc 地区的展示层对象
 * @date 2017/10/30 18:42
 */
public class RegionVO implements Serializable {
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
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
