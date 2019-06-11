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

    /**
     * 演示Stream延时性，即没有终止操作，则不执行之前的衔接操作
     */
    public void method3() {
        // 没有终止操作，则不会执行之前的逻辑，下面这段代码中filter里面的逻辑是不会执行的
        Stream.of("a", "b", "c")
                .filter(s -> {
                    System.out.println("filter -> " + s);
                    return true;
                });

        System.out.println("-----");

        // 有终止操作forEach，则会执行filter里面的逻辑，并且可以观察顺序是执行完单个元素的filter操作，则会继续执行该元素的forEach操作
        // 而不是执行完所有元素的filter，再去执行forEach操作
        Stream.of("x", "y", "z")
                .filter(s -> {
                    System.out.println("filter -> " + s);
                    return true;
                })
                .forEach(System.out::println);

        System.out.println("-----");

        // 有终止操作forEach，则会执行filter里面的逻辑，在遇到anyMatch操作时，若匹配到一个元素，则后续的元素的操作不会执行
        Stream.of("x", "y", "z")
                .map(s -> {
                    System.out.println("map -> " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch -> " + s);
                    return s.startsWith("X");
                })
        ;
    }
}
