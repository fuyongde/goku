package com.goku.dubbo.commons.utils;

import org.apache.commons.lang3.StringUtils;

import static com.goku.dubbo.commons.consts.PhonePattern.MOBILE_PATTERN;
import static com.goku.dubbo.commons.consts.PhonePattern.TELEPHONE_PATTERN;

/**
 * @author fuyongde
 * @desc 电话号码检测
 * @date 2017/11/10 17:47
 */
public class PhoneChecker {

    /**
     * 验证手机号码
     *
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        return mobile.matches(MOBILE_PATTERN);
    }

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean isTelephone(String telephone) {
        if (StringUtils.isBlank(telephone)) {
            return false;
        }
        return telephone.matches(TELEPHONE_PATTERN);
    }
}
