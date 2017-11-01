package com.goku.dubbo.commons.utils;

import com.google.common.collect.Lists;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.List;

import static com.goku.dubbo.commons.pinyin.PinyinRegexConsts.*;

/**
 * @author fuyongde
 * @desc 汉字转拼音的工具类
 * @date 2017/11/1 17:30
 */
public class PinyinUtils {

    /**
     * 获取汉字的首字母
     *
     * @param chinese
     *
     * @return
     */
    public static String getAlpha(String chinese) {

        List<String> regexs = Lists.newArrayList(
                REGEX_CNTRL,
                REGEX_PUNCT,
                REGEX_SPACE,
                REGEX_ROMAN_NUMERALS
        );

        for (String regex : regexs) {
            chinese = chinese.replaceAll(regex, NON);
        }

        char[] chars = chinese.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(PinyinHelper.toHanyuPinyinStringArray(aChar)[0].charAt(0));
        }
        return sb.toString().toUpperCase();
    }
}
