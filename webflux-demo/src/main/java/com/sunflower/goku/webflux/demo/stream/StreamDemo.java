package com.sunflower.goku.webflux.demo.stream;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author fuyongde
 * @date 2019/6/11
 * @desc 演示Stream
 */
public class StreamDemo {

    /**
     * 演示Stream最基本的用法
     */
    public void method1() {
        int[] numbers = {1, 2, 3, 4};
        int sum = IntStream.of(numbers).sum();
        System.out.println("结果为：" + sum);
    }

    /**
     * 演示Stream的常规操作
     */
    public void method2() {
        Random random = new Random();
        Stream<Integer> stream = Stream.generate(random::nextInt)
                //产生4个
                .limit(4)
                //第一个无状态操作
                .peek(s -> System.out.println("第一：" + s))
                //第二个无状态操作
                .filter(s -> {
                    System.out.println("打印：" + s);
                    return s > 100;
                })
                //有状态操作
                .sorted((i1, i2) -> {
                    System.out.println("排序：" + i1 + ", " + i2);
                    return i1.compareTo(i2);
                })
                //又一个有状态操作
                .peek(s -> System.out.println("PEEK：" + s))
                .parallel();
        System.out.println(stream.count());
    }
}
