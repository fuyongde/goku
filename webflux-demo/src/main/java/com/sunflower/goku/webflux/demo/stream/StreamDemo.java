package com.sunflower.goku.webflux.demo.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
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
                });
    }

    /**
     * 演示Stream的高级操作collect
     */
    public void method4() throws JsonProcessingException {
        List<String> list = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("-----");
        String str = Stream.of("x", "y", "z")
                .collect(Collectors.joining(","));
        System.out.println(str);

        System.out.println("-----");
        IntSummaryStatistics intSummaryStatistics = Stream.of(1, 2, 3)
                .collect(Collectors.summarizingInt(value -> value));
        System.out.println(intSummaryStatistics);

        System.out.println("-----");
        Double average = Stream.of(1, 2, 3)
                .collect(Collectors.averagingInt(value -> value));
        System.out.println(average);

        System.out.println("-----");
        Map<Integer, List<Integer>> map1 = Stream.of(1, 2, 3)
                .collect(Collectors.groupingBy(value -> value));
        System.out.println(new ObjectMapper().writeValueAsString(map1));

        System.out.println("-----");
        Map<Integer, String> map2 = Stream.of("a", "b", "c")
                .collect(Collectors.toMap(String::hashCode, Function.identity()));
        System.out.println(new ObjectMapper().writeValueAsString(map2));
    }

    /**
     * 演示Stream的高级操作flatMap
     */
    public void method5() {
        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + " -> " + i)));
        foos.forEach(foo -> IntStream.range(1, 4).forEach(i -> foo.bars.add(new Bar(foo.name + " -> " + "Bar" + " -> " + i))));
        List<Bar> bars = foos.stream()
                .flatMap(foo -> foo.bars.stream())
                .collect(Collectors.toList());
        bars.forEach(bar -> System.out.println(bar.name));
    }

    /**
     * 演示Stream的高级操作reduce
     */
    public void method6() {
        List<Foo> foos = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + " -> " + i)));
        foos.forEach(foo -> IntStream.range(1, 4).forEach(i -> foo.bars.add(new Bar(foo.name + " -> " + "Bar" + " -> " + i))));
        foos.stream()
                .reduce((foo1, foo2) -> foo1.name.hashCode() > foo2.name.hashCode() ? foo1 : foo2)
                .ifPresent(foo -> System.out.println(foo.name));

        System.out.println("-----");
        Foo foo = foos.stream()
                .reduce(new Foo(""), (foo1, foo2) -> {
                    foo1.name += foo2.name;
                    return foo1;
                });
        System.out.println(foo.name);
    }

    /**
     * 演示Stream的并行流
     */
    public void method7() {
        // 并行流使用公共的ForkJoinPool，查看公共池的默认线程数，也可以通过JVM参数 -Djava.util.concurrent.ForkJoinPool.common.parallelism=5 来调整
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        System.out.println(forkJoinPool.getParallelism());

        // 演示并行流使用了不同的线程来处理
        System.out.println("-----");
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .collect(Collectors.toList());
        list.forEach(System.out::print);

        // 该段程序会发现sort看起来只在主线程上串行执行。实际上，并行流上的sort在背后使用了Java8中新的方法Arrays.parallelSort()
        System.out.println("\n-----");
        Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .collect(Collectors.toList()).forEach(System.out::print);
        System.out.println("\n");
    }

    class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        public Foo(String name) {
            this.name = name;
        }
    }

    class Bar {
        String name;

        public Bar(String name) {
            this.name = name;
        }
    }
}
