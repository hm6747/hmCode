package cn.hmst.common.util;

import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 各种id生成策略
 *
 * @version 1.0
 */
public class IDUtils {

    private static SecureRandom random = new SecureRandom();

    /**
     * 小写
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 大写
     */
    public static String uuidBig() {
        return uuid().toUpperCase();
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 生成唯一的订单ID
     *
     * @return
     */
    public static String orderId() {
        String id = "";
        do {
            id = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss") + (int) ((Math.random() * 9 + 1) * 100000);
        } while (StringUtils.isBlank(id));
        return id;
    }

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 商品id生成
     */
    public static long genItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

}
