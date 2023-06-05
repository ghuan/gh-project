package com.gh.common.data.redis.redisson.impl;

import com.gh.common.data.redis.redisson.DistributedLocker;

/**
 * @author gourd.hu
 */
public class RedisDistributedLocker implements DistributedLocker {

//    @Resource
//    private RedissonClient redissonClient;
//
//    @Override
//    public RLock lock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock();
//        return lock;
//    }
//
//    @Override
//    public RLock lock(String lockKey, int leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(leaseTime, TimeUnit.SECONDS);
//        return lock;
//    }
//
//    @Override
//    public RLock lock(String lockKey, TimeUnit unit ,int timeout) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(timeout, unit);
//        return lock;
//    }
//
//    @Override
//    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            return lock.tryLock(waitTime, leaseTime, unit);
//        } catch (InterruptedException e) {
//            return false;
//        }
//    }
//
//    /**
//     * 指定时间内尝试上锁(单个锁)
//     * @author zhangguoqing
//     * @date 2022/11/29 10:29
//     * @param lockKey: 锁key
//     * @param waitTime: 尝试时间
//     * @param timeUnit: 时间单位
//     * @return boolean
//     */
//    @Override
//    public RLock tryLock(String lockKey,int waitTime,TimeUnit timeUnit) {
//        RLock lock = redissonClient.getLock(lockKey);
//        boolean flag = false;
//
//        try {
//            flag = lock.tryLock(waitTime, timeUnit);
//        } catch (InterruptedException ignored) {}
//        Assert.isTrue(flag,"业务繁忙,请刷新后重试!", "");
//        return lock;
//    }
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
//    @Override
//    public RLock tryLock(int waitTime,TimeUnit timeUnit,String... lockKeys) {
//        RLock[] locks = new RLock[lockKeys.length];
//        for (int i = 0; i < lockKeys.length; i++) {
//            locks[i] = redissonClient.getLock(lockKeys[i]);
//        }
//        RLock multiLock = redissonClient.getMultiLock(locks);
//        boolean flag = false;
//        try {
//            flag = multiLock.tryLock(waitTime,timeUnit);
//        } catch (InterruptedException ignored) {}
//        Assert.isTrue(flag,"业务繁忙,请刷新后重试!", "");
//        return multiLock;
//    }
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
//    @Override
//    public RLock tryLock(int waitTime,TimeUnit timeUnit,List<String> lockKeys) {
//        RLock[] locks = new RLock[lockKeys.size()];
//        for (int i = 0; i < lockKeys.size(); i++) {
//            locks[i] = redissonClient.getLock(lockKeys.get(i));
//        }
//        RLock multiLock = redissonClient.getMultiLock(locks);
//        boolean flag = false;
//        try {
//            flag = multiLock.tryLock(waitTime,timeUnit);
//        } catch (InterruptedException ignored) {}
//        Assert.isTrue(flag,"业务繁忙,请刷新后重试!", "");
//        return multiLock;
//    }
//
//    @Override
//    public void unlock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.unlock();
//    }
//
//    @Override
//    public void unlock(RLock lock) {
//        lock.unlock();
//    }
//
//    @Override
//    public RCountDownLatch getCountDownLatch(String name) {
//        return redissonClient.getCountDownLatch(name);
//    }
//
//    @Override
//    public RSemaphore getSemaphore(String name) {
//        return redissonClient.getSemaphore(name);
//    }
}