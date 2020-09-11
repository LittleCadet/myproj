package com.myproj.app.copy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shenxie
 * @date 2020/9/11
 */
public class testV2 {

    @SuppressWarnings("unchecked")
    public static <T> Map deepClone(Map obj){
        T clonedObj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return (Map) clonedObj;
    }

    /**
     * 关于深拷贝与浅拷贝
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 111);
        map.put("userName", "testName");
        map.put("isParent", false);
        List testList = new ArrayList();
        testList.add("test1");
        testList.add("test2");
        map.put("testList", testList);
        //浅拷贝
        Map shallowMap1 = map;
        //浅拷贝 引用类型
        Map shallowMap2 = new HashMap<>();
        shallowMap2.putAll(map);
        //浅拷贝 引用类型
        Map shallowMap3 = map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        //浅拷贝
        Map shallowMap4 = new HashMap(map);
        Map shallowMap5 = new HashMap();
        //浅拷贝
        map.forEach(shallowMap5:: put);

        //深拷贝
        Map deepCopyMap = deepClone(map);

        //虽然String 是引用类型 ， 但是String是final修饰的，不可变更的，所以只要不是直接使用指针赋值，那么都是深拷贝
        map.put("userName","testName-edit");

        //引用和对象不同， 对象不是final修饰的，可变更，所以从对象的角度来分析， 那么他们都是浅拷贝。
        testList.add("test3");

        System.out.println("shallowMap1 ="+ shallowMap1.toString() +  " , hashcode:" + (map.hashCode() == shallowMap1.hashCode()? "true" : "false"));
        System.out.println("shallowMap2 ="+ shallowMap2.toString() +  " , hashcode:" + (map.hashCode() == shallowMap2.hashCode()? "true" : "false"));
        System.out.println("shallowMap3 ="+ shallowMap3.toString() +  " , hashcode:" + (map.hashCode() == shallowMap3.hashCode()? "true" : "false"));
        System.out.println("shallowMap4 ="+ shallowMap4.toString() +  " , hashcode:" + (map.hashCode() == shallowMap4.hashCode()? "true" : "false"));
        System.out.println("shallowMap5 ="+ shallowMap5.toString() +  " , hashcode:" + (map.hashCode() == shallowMap5.hashCode()? "true" : "false"));

        System.out.println("deepCopyMap ="+ deepCopyMap.toString() +  " , hashcode:" + (map.hashCode() == deepCopyMap.hashCode()? "true" : "false"));
    }

}
