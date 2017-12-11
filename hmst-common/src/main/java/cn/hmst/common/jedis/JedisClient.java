package cn.hmst.common.jedis;

public interface JedisClient {

	String setJedisPool(String key, String value);
	String getJedisPool(String key);
	String setJedisCluster(String key, String value);
	String getJedisCluster(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);
}
