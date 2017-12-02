package com.goku.dubbo.commons.utils;

import org.apache.commons.lang3.StringUtils;

import static com.goku.dubbo.commons.consts.EmailPattern.EMAIL_PATTERN;

/**
 * @author fuyongde
 * @desc 邮箱验证的工具类
 * @date 2017/11/9 19:32
 */
public class EmailChecker {

  public static boolean isEmail(String email) {
    return StringUtils.isNotBlank(email) && email.matches(EMAIL_PATTERN);
  }
}
