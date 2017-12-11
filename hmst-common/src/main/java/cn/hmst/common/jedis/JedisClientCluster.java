package cn.hmst.common.jedis;


import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient {
	
	private JedisCluster jedisCluster;

	@Override
	public String setJedisPool(String key, String value) {
		return null;
	}

	@Override
	public String getJedisPool(String key) {
		return null;
	}

	@Override
	public String setJedisCluster(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String getJedisCluster(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}

	public void setJediscluster(JedisCluster jediscluster) {
		this.jedisCluster = jediscluster;
	}

	public JedisCluster getJediscluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}
}
