package com.gh.common.data.redis.example.service;

import com.gh.common.data.redis.example.data.po.CacheTestPO;

/**
 *
* description：缓存测试
 */
public interface ICacheService {
	String testGet(CacheTestPO cacheTestPO);
	CacheTestPO testPost(CacheTestPO cacheTestPO);
}
