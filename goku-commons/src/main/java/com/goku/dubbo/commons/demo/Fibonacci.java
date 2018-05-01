package com.goku.dubbo.commons.demo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author fuyongde
 * @version V1.0 @Description: 斐波那契数列
 * @date 2018/4/29 14:10
 */
public class Fibonacci {

  public static void main(String[] args) throws Exception {

    long start = System.currentTimeMillis();
    //System.out.println(recursiveFibonacci(50));
    BigInteger a = iterativeFibonacci(500000);
    long end = System.currentTimeMillis();
    System.out.println("迭代计算耗时："+ (end - start) + "毫秒");

    long start2 = System.currentTimeMillis();
    BigInteger b = parallelFibonacci(500000, 128);
    long end2 = System.currentTimeMillis();
    System.out.println("并行计算用时：" + (end2 - start2) + "毫秒");

    long start3 = System.currentTimeMillis();
    BigInteger c = streamFibonacci(500000, 128);
    long end3 = System.currentTimeMillis();
    System.out.println("流式计算用时：" + (end3 - start3) + "毫秒");

    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
  }

  private static long recursiveFibonacci(int n) {
    if (n < 2) {
      return 1;
    }

    return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
  }

  public static BigInteger iterativeFibonacci(int n) {

    if (n < 2) {
      return BigInteger.ONE;
    }

    BigInteger n1 = BigInteger.ONE;
    BigInteger n2 = BigInteger.ONE;
    BigInteger fi = BigInteger.valueOf(2);

    for (int i = 2; i <= n; i++) {
      fi = n1.add(n2);
      n1 = n2;
      n2 = fi;
    }

    return fi;
  }

  private static BigInteger streamFibonacci(int itemNum, int threadNum) {
    final Matrix matrix = new Matrix(1, 1, 1, 0);
    final Matrix primary = new Matrix(1, 0, 1, 0);
    final int workload = itemNum / threadNum;
    final int lastWorkload = itemNum - workload * (threadNum - 1);

    // 流式 API
    return IntStream.range(0, threadNum)    // 产生 [0, threadNum) 区间，用于将任务切分
        .parallel()                     // 使流并行化
        .map(i -> i < threadNum - 1 ? workload : lastWorkload)
        .mapToObj(w -> matrix.pow(w))   // map    ->  mN = matrix ^ workload
        .reduce((m1, m2) -> m1.mul(m2)) // reduce ->  m = m1 * m2 * ... * mN
        .map(m -> m.mul(primary))       // map    ->  m = m * primary
        .get().c;                       // get    ->  m.c
  }

  private static BigInteger parallelFibonacci(int itemNum, int threadNum) throws Exception {
    final Matrix matrix = new Matrix(1, 1, 1, 0);
    final Matrix primary = new Matrix(1, 0, 1, 0); // (f0, 0; f1, 0)
    final int workload = itemNum / threadNum;      // 每个线程要计算的 相乘的项数
    // (num / threadNum) 可能存在除不尽的情况，所以最后一个任务计算所有剩下的项数
    final int lastWorkload = itemNum - workload * (threadNum - 1);

    List<Callable<Matrix>> tasks = new ArrayList<>(threadNum);
    for (int i = 0; i < threadNum; i++) {
      if (i < threadNum - 1) {
        // 为了简洁，使用 Lambda 表达式替代要实现 Callable<Matrix> 的匿名内部类
        tasks.add(() -> matrix.pow(workload));
      } else {
        tasks.add(() -> matrix.pow(lastWorkload));
      }
    }

    ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);
    List<Future<Matrix>> futures = threadPool.invokeAll(tasks); // 执行所有任务，invokeAll 会阻塞直到所有任务执行完毕

    Matrix result = primary.copy();
    for (Future<Matrix> future : futures) { // (matrix ^ n) * (f0, 0; f1, 0)
      result = result.mul(future.get());
    }

    threadPool.shutdown();

    return result.c;
  }

}
