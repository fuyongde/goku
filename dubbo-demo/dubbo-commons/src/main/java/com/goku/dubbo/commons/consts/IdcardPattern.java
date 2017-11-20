package com.goku.dubbo.commons.consts;

/**
 * @author fuyongde
 * @desc 身份证号码正则
 * @date 2017/11/10
 */
public interface IdcardPattern {

  /**
   * 一代身份证号码长度
   */
  int FIRST_IDCARD_LENGTH = 15;

  /**
   * 二代身份证号码长度
   */
  int SECOND_IDCARD_LENGTH = 18;

  /**
   * <pre>
   * 省、直辖市代码表：
   *     11=北京  12=天津  13=河北   14 =山西 15 =内蒙古
   *     21=辽宁  22=吉林  23=黑龙江 31 =上海 32 =江苏
   *     33=浙江  34=安徽  35=福建   36 =江西 37 =山东
   *     41=河南  42=湖北  43=湖南   44 =广东 45 =广西
   *     46=海南  50=重庆  51=四川   52 =贵州 53 =云南
   *     54=西藏  61=陕西  62=甘肃   63 =青海 64 =宁夏
   *     65=新疆  71=台湾  81=香港   82 =澳门 91 =国外
   * </pre>
   */
  String[] CITY_CODE = {
      "11", "12", "13", "14", "15",
      "21", "22", "23", "31", "32",
      "33", "34", "35", "36", "37",
      "41", "42", "43", "44", "45",
      "46", "50", "51", "52", "53",
      "54", "61", "62", "63", "64",
      "65", "71", "81", "82", "91"};

  /**
   * 每位加权因子
   */
  int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

}
