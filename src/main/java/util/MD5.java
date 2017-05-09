package util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by mihail on 09.05.17.
 */
public class MD5 {

    public static String getMD5(String text)throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.digest(text.getBytes());
        return new BigInteger(1, md.digest()).toString();
    }

}
