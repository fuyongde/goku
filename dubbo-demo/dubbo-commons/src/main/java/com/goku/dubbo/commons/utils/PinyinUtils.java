package com.goku.dubbo.commons.utils;

import com.google.common.collect.Lists;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;

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

        List<String> regexList = Lists.newArrayList(
                REGEX_CNTRL,
                REGEX_PUNCT,
                REGEX_SPACE,
                REGEX_ROMAN_NUMERALS
        );

        return getAlpha(chinese, regexList);
    }

    /**
     * 获取汉字的首字母
     *
     * @param chinese   汉字
     * @param regexList 需要剔除掉的字符的正则
     *
     * @return
     */
    public static String getAlpha(String chinese, List<String> regexList) {

        Validate.notBlank(chinese);

        if (!CollectionUtils.isEmpty(regexList)) {
            for (String regex : regexList) {
                chinese = chinese.replaceAll(regex, NON);
            }
        }

        char[] chars = chinese.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char tmp : chars) {
            if (Character.toString(tmp).matches(REGEX_CHINESE)) {
                sb.append(PinyinHelper.toHanyuPinyinStringArray(tmp)[0].charAt(0));
            }
        }
        return sb.toString().toUpperCase();
    }

    public static String getAllLetter(String input) {
        List<String> regexList = Lists.newArrayList(
                REGEX_CNTRL,
                REGEX_PUNCT,
                REGEX_SPACE,
                REGEX_ROMAN_NUMERALS
        );

        return getAllLetter(input, regexList);
    }

    public static String getAllLetter(String input, List<String> regexList) {
        Validate.notBlank(input);
        if (!CollectionUtils.isEmpty(regexList)) {
            for (String regex : regexList) {
                input = input.replaceAll(regex, NON);
            }
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] chars = input.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char tmp : chars) {
            if (Character.toString(tmp).matches(REGEX_CHINESE)) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(tmp, format)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    continue;
                }
            } else {
                sb.append(tmp);
            }
        }
        return sb.toString();
    }
}
