package cn.hmst.common.util;

/**
 * Created by Administrator on 2018/3/27 0027.
 */
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {

    /**
     * Base64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * Base64解密
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

}