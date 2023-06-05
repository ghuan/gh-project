package com.gh.common.data.redis.redisson;

/**
 * @author gourd.hu
 */
public interface DistributedLocker {

//    RLock lock(String lockKey);
//
//    RLock lock(String lockKey, int timeout);
//
//    RLock lock(String lockKey, TimeUnit unit, int timeout);
//
//    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);
//
//    /**
//     * 指定时间内尝试上锁(单个锁)
//     * @author zhangguoqing
//     * @date 2022/11/29 10:29
//     * @param lockKey: 锁key
//     * @param waitTime: 尝试时间
//     * @param timeUnit: 时间单位
//     * @return org.redisson.api.RLock
//     */
//    RLock tryLock(String lockKey,int waitTime,TimeUnit timeUnit);
//
//    /**
//     * 指定时间内尝试上锁(多个锁)
//     * @author zhangguoqing
//     * @date 2022/11/29 13:48
//     * @param waitTime:
//     * @param timeUnit:
//     * @param lockKeys:
//     * @return org.redisson.api.RLock
//     */
//    RLock tryLock(int waitTime,TimeUnit timeUnit,String... lockKeys);
//
//    /**
//     * 指定时间内尝试上锁(多个锁)
//     * @author zhangguoqing
//     * @date 2022/11/29 13:48
//     * @param waitTime:
//     * @param timeUnit:
//     * @param lockKeys:
//     * @return org.redisson.api.RLock
//     */
//    RLock tryLock(int waitTime, TimeUnit timeUnit, List<String> lockKeys);
//
//    void unlock(String lockKey);
//
//    void unlock(RLock lock);
//
//    RCountDownLatch getCountDownLatch(String name);
//
//    RSemaphore getSemaphore(String name);
}