package com.kellan.redis;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

public class RedisCache implements Cache {

	public static final Logger logger = LoggerFactory
			.getLogger(RedisCache.class);

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	// @Resource
	// private static JedisConnectionFactory jedisConnectionFactory;

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;  
    
	private JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(); 

	private String id; // cache instance id

	public RedisCache(final String id) {
		if (id == null)
			throw new IllegalArgumentException("Cache instances require an ID");
		logger.debug("MyBatisRedisCache id:" + id);
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public Object getObject(Object arg0) {
		logger.info("二级缓存：getObject获取缓存对象");
		return arg0 == null ? null
				: jdkSerializer.deserialize((byte[])redisTemplate.opsForValue().get(arg0.toString()));
	}

	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

	public int getSize() {
		logger.info("二级缓存：getSize获取缓存大小");
		Long size = redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException{
				return connection.dbSize();
			}
		});
		return size.intValue();
	}

	public void putObject(Object arg0, Object arg1) {
		logger.info("二级缓存：putObject添加缓存对象");
		if (arg1 != null) redisTemplate.opsForValue().set(arg0.toString(), jdkSerializer.serialize(arg1));
	}

	public Object removeObject(Object arg0) {
		logger.info("二级缓存：removeObject移除缓存对象");
		return arg0 == null? null : redisTemplate.expire(arg0.toString(),1,TimeUnit.SECONDS);
	}
	
	public void clear() {
		try {
			Set<Serializable> keys = redisTemplate.keys("");
			redisTemplate.delete(keys);
			logger.info("出现增删改操作时，清空对应的缓存 >>>>>>>>>>>> " + keys.size());
		} catch (Exception e) {
			logger.debug("二级缓存方法clear()执行失败");
			e.printStackTrace();
		}
	}

}
