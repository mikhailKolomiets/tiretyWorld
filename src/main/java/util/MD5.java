package util;

import java.security.MessageDigest;

/**
 * Created by mihail on 09.05.17.
 */
public class MD5 {

    public static String getMD5(String text)throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte byteData[] = md.digest(text.getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (byte b : byteData){
            sb.append(b);
        }
        //for (int i = 0; i < byteData.length; i++)
            //sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }

}
