package hmst;

import cn.hmst.common.jedis.JedisClient;
import cn.hmst.common.jedis.JedisClientPool;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        JedisPool jedisPool = new JedisPool("120.79.53.121",7000);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("Hm123456");
        jedis.set("a","1000");
        System.out.println(jedis.get("a"));
    }
}
