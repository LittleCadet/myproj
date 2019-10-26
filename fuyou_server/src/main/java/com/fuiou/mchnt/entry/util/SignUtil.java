/**
 * 
 */
package com.fuiou.mchnt.entry.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @project entry
 * @description 
 * @author abel.li
 * @creation 2017年3月14日
 * @email 
 * @version	 
 */
public class SignUtil {

    /**
     * 
     * @param object
     * @param exceptsField
     * @return
     */
    public static String generateSign(Object object, String... exceptsField){
        StringBuffer signContent = new StringBuffer();
        HashMap<String,String> map = new HashMap<String,String>();
        try {
            Field[] fs = object.getClass().getDeclaredFields();
            for (Field f:fs){
                if (f.getName().equals("serialVersionUID")){
                    continue;
                }
                f.setAccessible(true);
                String value = (String)f.get(object);
                if(value==null || value.equals("")){
                    continue;
                }
                String name = f.getName();
                map.put(name, value);
            }
            if(exceptsField != null && exceptsField.length > 0){
                for(String exceptFieldName : exceptsField){
                    map.remove(exceptFieldName);
                }
            }
//            map.remove("sign");
//            map.remove("mchntCd");
            List<Map.Entry<String,String>> mappingList = new ArrayList<Map.Entry<String,String>>(map.entrySet());
            Collections.sort(mappingList, new Comparator<Map.Entry<String,String>>(){ 
                   public int compare(Map.Entry<String,String> mapping1,Map.Entry<String,String> mapping2){ 
                       return mapping1.getKey().compareTo(mapping2.getKey()); 
                   } 
            });
            Map.Entry<String,String> mapping = (Entry<String, String>) mappingList.get(0);
            signContent.append(mapping.getKey().toLowerCase()+"="+mapping.getValue());
            for (int i=1;i<mappingList.size();i++){
                Map.Entry<String,String> maps = (Entry<String, String>) mappingList.get(i);
                signContent.append("&"+maps.getKey().toLowerCase()+"="+map.get(maps.getKey()));
            }
            
            return signContent.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
