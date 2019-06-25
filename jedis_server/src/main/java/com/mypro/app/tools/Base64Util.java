package com.mypro.app.tools;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * java.util的Base64加密，解密【源自于apache的Base64，java8以后的jdk功能】
 * @author The flow developers
 */
public class Base64Util {

    private static Base64.Encoder encoder = Base64.getEncoder();

    private static Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 加密
     * @param param
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodec(String param) throws UnsupportedEncodingException {
        return encoder.encodeToString(param.getBytes("UTF-8"));
    }

    /**
     * 解密:解码长度需要是4的倍数，否则报错：Last unit does not have enough valid bits
     * @param param
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodec (String param) throws UnsupportedEncodingException {
        return new String(decoder.decode(param),"UTF-8");
    }

}
