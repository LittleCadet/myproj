package com.myproj.app.algorithm.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.util.Assert;

/**
 * @author shenxie
 * @date 2023/12/25
 */
public class RandomizedSet {

    public static void main(String[] args) {
        Assert.isTrue(insert(1) == true, "1");
        Assert.isTrue(remove(2) == false, "2");
        Assert.isTrue(insert(2) == true, "2");
        Assert.isTrue( nums.contains(getRandom()), "不包含");
        Assert.isTrue(remove(1) == true, "1");
        Assert.isTrue(insert(2) == false, "2");
        Assert.isTrue( nums.contains(getRandom()), "不包含");
    }

    static List<Integer> nums = new ArrayList<Integer>();

    // k-v: 值：ArrayList的索引
    static Map<Integer,Integer> indices = new HashMap<Integer,Integer>();
    static Random random =new Random();
    public RandomizedSet() {
    }

    public static boolean insert(int val) {
        if(indices.containsKey(val)){
            return false;
        }
        int index=nums.size();
        nums.add(val);//ArrayList存入数据
        indices.put(val,index);//哈希表中存入该数据和它在ArrayList中的下标
        return true;
    }

    public static boolean remove(int val) {
        if(!indices.containsKey(val)){
            return false;
        }

        //总体思路：用ArrayList最后一个元素覆盖要删除的元素，然后把ArrayList最后一个元素删掉，更新哈希表中的数据
        int index=indices.get(val);//在哈希表中查找该数据在ArrayList中的下标
        int last=nums.get(nums.size()-1);//获取ArrayList中最后一个元素
        nums.set(index,last);//把最后一个元素移到需要删除的元素处，替换掉
        indices.put(last,index);//把替换后的元素和它的新下标一起存入哈希表
        nums.remove(nums.size()-1);//删掉ArrayList最后一个元素
        indices.remove(val);//删掉哈希表中要删除的元素
        return true;
    }

    public static int getRandom() {
        int randomIndex=random.nextInt(nums.size());//获取一个范围为[0,nums.size())的随机整数
        return nums.get(randomIndex);
    }
}
