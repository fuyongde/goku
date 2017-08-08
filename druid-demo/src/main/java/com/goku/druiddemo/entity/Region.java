package com.goku.druiddemo.entity;

import java.io.Serializable;

public class Region implements Serializable {
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

}
