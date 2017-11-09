package com.goku.dubbo.commons.utils;

import com.goku.dubbo.commons.consts.EmailPattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fuyongde
 * @desc 邮箱验证的工具类
 * @date 2017/11/9 19:32
 */
public class EmailChecker {

    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        return email.matches(EmailPattern.EMAIL_PATTERN);
    }
}
