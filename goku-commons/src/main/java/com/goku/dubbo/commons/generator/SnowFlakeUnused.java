package com.goku.dubbo.commons.generator;

/**
 * @author fuyongde
 * @date 2018/5/1 15:21
 * @desc 雪花算法，生成唯一id
 * @version V1.0
 */
public class SnowFlakeUnused {
  /** 起始的时间戳 */
  private static final long START_STAMP = 1480166465631L;

  /** 每一部分占用的位数 */
  /** 序列号占用的位数 */
  private static final long SEQUENCE_BIT = 12;
  /** 机器标识占用的位数 */
  private static final long MACHINE_BIT = 5;
  /** 数据中心占用的位数 */
  private static final long DATA_CENTER_BIT = 5;

  /** 每一部分的最大值 */
  private static final long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);

  private static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
  private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

  /** 每一部分向左的位移 */
  private static final long MACHINE_LEFT = SEQUENCE_BIT;

  private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
  private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

  /** 数据中心 */
  private long dataCenterId;
  /** 机器标识 */
  private long machineId;
  /** 序列号 */
  private long sequence = 0L;
  /** 上一次时间戳 */
  private long lastStamp = -1L;

  public SnowFlakeUnused(long dataCenterId, long machineId) {
    if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
      throw new IllegalArgumentException(
          "dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
    }
    if (machineId > MAX_MACHINE_NUM || machineId < 0) {
      throw new IllegalArgumentException(
          "machineId can't be greater than MAX_MACHINE_NUM or less than 0");
    }
    this.dataCenterId = dataCenterId;
    this.machineId = machineId;
  }

  /**
   * 产生下一个ID
   *
   * @return
   */
  public synchronized long nextId() {
    long currStamp = nextMill();
    if (currStamp < lastStamp) {
      throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
    }

    if (currStamp == lastStamp) {
      // 相同毫秒内，序列号自增
      sequence = (sequence + 1) & MAX_SEQUENCE;
      // 同一毫秒的序列数已经达到最大
      if (sequence == 0L) {
        currStamp = nextMill();
      }
    } else {
      // 不同毫秒内，序列号置为0
      sequence = 0L;
    }

    lastStamp = currStamp;

    return (currStamp - START_STAMP) << TIMESTAMP_LEFT // 时间戳部分
        | dataCenterId << TIMESTAMP_LEFT // 数据中心部分
        | machineId << MACHINE_LEFT // 机器标识部分
        | sequence; // 序列号部分
  }

  private long nextMill() {
    long mill = nextStamp();
    while (mill <= lastStamp) {
      mill = nextStamp();
    }
    return mill;
  }

  private long nextStamp() {
    return System.currentTimeMillis();
  }

}
