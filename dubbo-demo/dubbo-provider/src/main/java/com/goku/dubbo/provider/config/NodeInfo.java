package com.goku.dubbo.provider.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/1 15:57
 * @desc 节点信息
 */
@Component
@ConfigurationProperties(prefix = "node")
public class NodeInfo {
  private Integer dataCenterId;
  private Integer machineId;

  public Integer getDataCenterId() {
    return dataCenterId;
  }

  public void setDataCenterId(Integer dataCenterId) {
    this.dataCenterId = dataCenterId;
  }

  public Integer getMachineId() {
    return machineId;
  }

  public void setMachineId(Integer machineId) {
    this.machineId = machineId;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
