package com.myproj.app.algorithm.heap;

import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;

/**
 * @author shenxie
 * @date 2023/11/30
 */
public class Test {

    public static void main(String[] args) {
        HashMap<Object, Object> maps = Maps.newHashMap();
        maps.put(2,1);
        maps.put(1,1);
        maps.put(3,1);

        String a = "1";
        String b = a;
        a = "2";
        int a1 = 1;
        int a2 = a1;
        a1 = 2;
        System.out.println(b);
        System.out.println(a2);
        System.out.println(new TreeMap<>(maps));
    }
}
