package com.gh.common.data.redis.example.service.imp;

import com.gh.common.data.redis.example.data.po.CacheTestPO;
import com.gh.common.data.redis.example.service.ICacheService;
import com.gh.common.core.util.gson.GsonUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
* description：缓存测试
 */
@Service
public class CacheServiceImpl implements ICacheService {


	@Override
	@Cacheable(value = "testGet", key = "#cacheTestPO.id")
	public String testGet(CacheTestPO cacheTestPO) {
		String result = GsonUtil.toJson(cacheTestPO);
		return result;
	}

	@Override
	@Cacheable(value = "testPost", key = "#cacheTestPO.id")
	public CacheTestPO testPost(CacheTestPO cacheTestPO) {
		return cacheTestPO;
	}
}
