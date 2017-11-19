package com.goku.dubbo.commons.utils;

import static com.goku.dubbo.commons.consts.NumberPattern.ALL_DIGITAL_PATTERN;

/**
 * @author fuyongde
 * @desc 数字校验
 * @date 2017/11/10 15:38
 */
public class NumberChecker {

  /**
   * 数字验证
   *
   * @param str
   * @return
   */
  public static boolean isDigital(String str) {
    return str.matches(ALL_DIGITAL_PATTERN);
  }
}
