package hmst;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
  /*     ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        System.out.println(2);
        JedisClient jedisClient=applicationContext.getBean(JedisClient.class);
        System.out.println(1);
        jedisClient.set("mytest","155");
        String string = jedisClient.get("mytest");
        System.out.println(string);*/
     /*   PageHelper.startPage(1,10);
        TbItemExample example = new TbItemExample();
        List<TbItem> list=tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(list.size());*/
/*        assertTrue( true );*/

    }
}
