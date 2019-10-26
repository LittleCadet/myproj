package com.myproj.app.collection;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LittleCadet
 */
public class ListDemo {

    /**
     * java1.8 list可以用stream()中的函数：distinct()去重。不在需要set
     */
    public static void distinct(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        //Set<Integer> set = new HashSet<>(list);

        for(Integer i:collect){
            System.out.println(i);
        }
    }
}
