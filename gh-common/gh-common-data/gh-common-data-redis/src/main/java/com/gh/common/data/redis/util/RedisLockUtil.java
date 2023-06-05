package com.gh.common.data.redis.util;


import com.gh.common.data.redis.redisson.DistributedLocker;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁帮助类
 *
 * @author gourd.hu
 *
 */
public class RedisLockUtil {

    @Resource
    private DistributedLocker distributedLockerT ;

    private static DistributedLocker distributedLocker;

    @PostConstruct
    public void init() {
        distributedLocker = distributedLockerT;
    }
//    /**
//     * 加锁
//     * @param lockKey
//     * @return
//     */
//    public static RLock lock(String lockKey) {
//        return distributedLocker.lock(lockKey);
//    }
//
//    /**
//     * 释放锁
//     * @param lockKey
//     */
//    public static void unlock(String lockKey) {
//        distributedLocker.unlock(lockKey);
//    }
//
//    /**
//     * 释放锁
//     * @param lock
//     */
//    public static void unlock(RLock lock) {
//        distributedLocker.unlock(lock);
//    }
//
//    /**
//     * 带超时的锁
//     * @param lockKey
//     * @param timeout 超时时间   单位：秒
//     */
//    public static RLock lock(String lockKey, int timeout) {
//        return distributedLocker.lock(lockKey, timeout);
//    }
//
//    /**
//     * 带超时的锁
//     * @param lockKey
//     * @param unit 时间单位
//     * @param timeout 超时时间
//     */
//    public static RLock lock(String lockKey, int timeout,TimeUnit unit ) {
//        return distributedLocker.lock(lockKey, unit, timeout);
//    }
//
//    /**
//     * 尝试获取锁
//     * @param lockKey
//     * @param waitTime 最多等待时间
//     * @param leaseTime 上锁后自动释放锁时间
//     * @return
//     */
//    public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
//        return distributedLocker.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
//    }
//
//    /**
//     * 尝试获取锁
//     * @param lockKey
//     * @param unit 时间单位
//     * @param waitTime 最多等待时间
//     * @param leaseTime 上锁后自动释放锁时间
//     * @return
//     */
//    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
//        return distributedLocker.tryLock(lockKey, unit, waitTime, leaseTime);
//    }
//
//    /**
//     * 指定时间内尝试上锁(单个锁)
//     * 1:相比lock有等待获取时间
//     * 没有等待时间: 会一直循环尝试获取锁,直到获取到锁为止
//     *
//     * 2:相比其它两个tryLock方法没有指定锁自动释放时间
//     * 有释放时间： 在到期后就会释放锁
//     * 没有释放时间： 在默认的30秒释放时间内会有看门狗自动刷新过期时间
//     * @author zhangguoqing
//     * @date 2022/11/28 14:43
//     * @param lockKey:
//     * @param waitTime: 尝试时间（单位：秒）,-1 会一直尝试直到成功为止
//     * @return RLock
//     */
//    public static RLock tryLock(String lockKey, int waitTime) {
//        return distributedLocker.tryLock(lockKey, waitTime, TimeUnit.SECONDS);
//    }
//
//    /**
//     * 指定时间内尝试上锁(多个锁)
//     * 和事务一样,只要有一个没有成功上锁就失败
//     * @author zhangguoqing
//     * @date 2022/11/29 13:48
//     * @param waitTime: 尝试时间（单位：秒）,-1 会一直尝试直到成功为止
//     * @param lockKeys:
//     * @return org.redisson.api.RLock
//     */
//    public static RLock tryLock(int waitTime,String... lockKeys) {
//        return distributedLocker.tryLock(waitTime, TimeUnit.SECONDS,lockKeys);
//    }
//
//    /**
//     * 指定时间内尝试上锁(多个锁)
//     * 和事务一样,只要有一个没有成功上锁就失败
//     * @author zhangguoqing
//     * @date 2022/11/29 13:48
//     * @param waitTime: 尝试时间（单位：秒）,-1 会一直尝试直到成功为止
//     * @param lockKeys:
//     * @return org.redisson.api.RLock
//     */
//    public static RLock tryLock(int waitTime, List<String> lockKeys) {
//        return distributedLocker.tryLock(waitTime, TimeUnit.SECONDS,lockKeys);
//    }
//
//    /**
//     * 获取计数器
//     *
//     * @param name
//     * @return
//     */
//    public static RCountDownLatch getCountDownLatch(String name){
//        return distributedLocker.getCountDownLatch(name);
//    }
//
//    /**
//     * 获取信号量
//     *
//     * @param name
//     * @return
//     */
//    public static RSemaphore getSemaphore(String name){
//        return distributedLocker.getSemaphore(name);
//    }
}