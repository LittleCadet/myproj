package com.myproj.app.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;

/**
 * @Author LittleCadet
 * @Date 2020/3/27
 */
public class MultiMapDemo
{
    /**
     * Multimap : 允许key重复
     * @param args
     */
    public static void main(String[] args)
    {
        //创建multiMap
        Multimap<String,Object> multimap = ArrayListMultimap.create();

        multimap.put("Fruits", "Bannana");
        multimap.put("Fruits", "Apple");
        multimap.put("Fruits", "Pear");
        multimap.put("Fruits", "Pear");
        multimap.put("Vegetables", "Carrot");

        //multiMap取值
        Collection<Object> fruits =  multimap.get("Fruits");
        System.out.println("fruits : "  + fruits);
        System.out.println("fruits set :" + ImmutableSet.copyOf(fruits));
        Collection<Object> vegetables = multimap.get("Vegetables");
        System.out.println("vegetables:" + vegetables);
    }
}
