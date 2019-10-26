/**
 * 
 */
package com.fuiou.mchnt.entry.util;

/**
 * 
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email 
 * @version
 */
public class StringUtils {
    
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }
    
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }
    
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
    
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }
    
    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }
}
