package com.myproj.app.sensitive;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * LittleCadet
 */
public class Sensitive {

    /**
     * 对身份证号，脱敏处理，保留后4位
     * @param id
     * @return
     */
    public static String cardNum(String id){

            if (StringUtils.isBlank(id)) {
                return "";
            }

            //StringUtils.right(id, 4):保留字符串右侧4位
            return StringUtils.leftPad(StringUtils.right(id, 4), StringUtils.length(id), "*");
    }

    /**
     * 对座机号，做脱敏处理，保留后4位
     */
    public static String phoneNum(String num){
        if(StringUtils.isBlank(num)){
            return "";
        }

        return StringUtils.leftPad(StringUtils.right(num,4),StringUtils.length(num),"*");
    }

    /**
     * 对手机号，做脱敏处理，保留前3位，和后4位
     * @param num
     * @return
     */
    public static String cellPhoneNum(String num){
        if(StringUtils.isBlank(num)){
            return "";
        }

        return StringUtils.left(num, 3)
                .concat(
                        StringUtils.removeStart(
                                StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"),
                                "***"));
    }

}
