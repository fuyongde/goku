package com.goku.dubbo.commons.pinyin;

/**
 * @author fuyongde
 * @desc 拼音相关的常量
 * @date 2017/11/1 19:46
 */
public interface PinyinRegexConsts {

    /** 空川 **/
    String NON = "";

    /** 标点符号 **/
    String REGEX_PUNCT = "[\\pP]";
    /** 控制字符 **/
    String REGEX_CNTRL = "[\\pC]";
    /** 空白字符 **/
    String REGEX_SPACE = "[\\pS]";
    /** 罗马数字 **/
    String REGEX_ROMAN_NUMERALS = "[\\u2160~\\u217F]";

}
