package com.sunflower.goku.druid.demo.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author fuyongde
 * @date 2020/1/7 22:38
 */
public class MyLock {

    private volatile int state = 0;

    private Thread lockHolder;

    private final ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        if (acquire()) {
            return;
        }
        Thread currentThread = Thread.currentThread();
        // 加锁失败，放入队列中等待
        waiters.add(currentThread);
        while (true) {
            // 如果是队首的线程，则尝试获取锁
            if (waiters.peek() == currentThread && acquire()) {
                // 获取到锁之后，移除对头
                waiters.poll();
                return;
            }
            //
            LockSupport.park();
        }
    }

    public void unlock() {
        if (Thread.currentThread() != lockHolder) {
            throw new RuntimeException("lockHolder is not current");
        }
        if (compareAndSwapState(1, 0)) {
            setLockHolder(null);
            Thread thread = waiters.peek();
            if (thread != null) {
                // 队头释放锁，唤醒下一个线程
                LockSupport.unpark(thread);
            }
        }
    }

    private boolean acquire() {
        int s = getState();
        Thread currentThread = Thread.currentThread();
        if (s == 0) {
            // 当前没有加锁，若当前队列为空或队列的头结点是当前线程，则尝试获取锁
            if ((waiters.size() == 0 || currentThread == waiters.peek())
                    && compareAndSwapState(0, 1)) {
                // 加锁成功，设置当前线程持有锁
                setLockHolder(currentThread);
                return true;
            }
        }
        return false;
    }

    public int getState() {
        return state;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }

    public final boolean compareAndSwapState(int except, int update) {
        return UNSAFE.compareAndSwapInt(this, STATE_OFFSET, except, update);
    }

    private static final Unsafe UNSAFE;

    private static final long STATE_OFFSET;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (Unsafe) f.get(null);
            STATE_OFFSET = UNSAFE.objectFieldOffset(MyLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
