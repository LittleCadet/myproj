/**
 * 
 */
package com.fuiou.mchnt.entry.insEntry.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import com.fuiou.mchnt.entry.util.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @project wmp
 * @description 
 * @author abel.li
 * @creation 2017年8月7日
 * @email lizhensh@fuiou.com,abel0130@126.com
 * @version	 
 */
public class VerifySignUtil {

    private static final Logger logger = LoggerFactory.getLogger(VerifySignUtil.class);
    
    public static boolean verifySign(String sign, Object o, String key, String charset){
        return verifySignWithCase(sign, o, key, charset, true);
    }
    
    public static String getSign(Object o,String key, String charset){
        return getSignWithCase(o, key, charset, true);
    }
    
    /**
     * 
     * @param sign
     * @param o
     * @param key
     * @param charset
     * @param isUpperCase
     * @return
     */
    public static boolean verifySignWithCase(String sign, Object o, String key, String charset, boolean isUpperCase){
        if(sign.equals(getSignWithCase(o, key, charset, isUpperCase))){
            return true;
        }
        return false;
    }

    /**
     * 
     * @param o
     * @param key
     * @param charset
     * @param isUpperCase
     * @return
     */
    public static String getSignWithCase(Object o, String key, String charset, boolean isUpperCase) {
        String sb = getSignContent(o);
        String result = sb.toString();
        result += "key=" + key;
        System.out.println(result);
        logger.info("signContent:", result);
//        result = MD5.MD5Encode(result, charset);
        result = DigestUtils.md5Hex(result.getBytes(Charset.forName(charset)));
        logger.info("sign:", result);
        return isUpperCase ? result.toUpperCase() : result.toLowerCase();
    }

    /**
     * @param o
     * @return
     */
    public static String getSignContent(Object o) {
        ArrayList<String> list = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        try {
            BeanInfo intro = Introspector.getBeanInfo(o.getClass(), Object.class);
            for (PropertyDescriptor f : intro.getPropertyDescriptors()) {
                System.out.println(f.getReadMethod().getName());
                if(!"sign".equals(f.getName())&& !"sign_type".equals(f.getName()) 
                        && f.getReadMethod().invoke(o) != null 
                        && StringUtils.isNotEmpty(f.getReadMethod().invoke(o).toString())){
                    list.add(f.getName() + "=" + f.getReadMethod().invoke(o).toString() + "&");
                }
            }
            int size = list.size();
            String [] arrayToSort = list.toArray(new String[size]);
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
            
            for(int i = 0; i < size; i ++) {
                sb.append(arrayToSort[i]);
            }
            System.out.println("signContent:" + sb);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Introspector.flushFromCaches(o.getClass());
        }
        return sb.toString();
    }
    
}
