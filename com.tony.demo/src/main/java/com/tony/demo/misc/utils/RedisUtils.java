package com.tony.demo.misc.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisUtils {

	// @Resource(name = "redisStringTemplate")
	private RedisTemplate redisTemplate;

	/**
	 * 获取缓存值
	 * 
	 * @param key
	 * @return
	 */
	private String getCache(String key) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		String value = ops.get(key);
		log.info("缓存获取成功 key-> {}, value-> {}", key, value);
		return value;
	}

	/**
	 * 存入缓存
	 */
	private void putHourCache(String key, String value,Integer expire) {
		this.putCache(key, value, expire, TimeUnit.HOURS);
	}
	
	private void putDayCache(String key, String value,Integer expire) {
		this.putCache(key, value, expire, TimeUnit.DAYS);
	}
	
	
	/**
	 * 存入缓存
	 */
	private void putCache(String key, String value,Integer expire,TimeUnit timeUnit) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		if (ops.get(key) != null) {
			redisTemplate.delete(key);
		}
		ops.set(key, value);
		redisTemplate.expire(key, expire, timeUnit);
		log.info("存入缓存成功 key-> {}, value-> {}", key, value);
	}
	
	
	private String genCodeCacheKey(String eventKey) {
		return "eventKey:" + eventKey + "-eventKeyInfo";
	}

	private void removeRedis(String key) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		if (ops.get(key) != null) {
			redisTemplate.delete(key);
			log.info("清除缓存成功 key-> {}", key);
		}
	}

}
