package com.goku.dubbo.commons.demo;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fuyongde
 * @version V1.0
 * @date 2018/5/12 17:59
 * @desc 排序算法
 */
public class Sortor {

  private static int[] bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) { // 外层循环控制排序趟数
      for (int j = 0; j < arr.length - 1 - i; j++) { // 内层循环控制每一趟排序多少次
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }

    return arr;
  }

  public static void main(String[] args) {
    int[] a = new int[] {3, 54523, 213, 325, 234, 56564};
    bubbleSort(a);
    List<Integer> array = Lists.newArrayList();
    for (int i : a) {
      array.add(i);
    }
    String s = StringUtils.join(array, ",");
    System.out.println(s);
  }
}
