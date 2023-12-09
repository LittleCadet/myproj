package com.myproj.app.algorithm.排序;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目：
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 思路：
 * 1. 不能排序： 因为：题目要求返回数组下标。 排序后，下标会变。
 * 2. 不能用set去重，需要用map, 因为： 需要知道 元素的值 和 下标。 如果只用set会导致下标的记录 是件难事。
 * 3. 用for循环确定第一个数， 之后第二个数 = target - 第一个数。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 两数相加 {

    public static void main(String[] args) {
//        System.out.println(twoSum(new int[]{2,7,11,15}, 9));
        System.out.println(twoSum(new int[]{3,2,4}, 6));
    }

    public static int[] twoSum(int[] nums, int target) {
        // 如果最小的数 > target , 直接返回空数组。
        if(nums.length < 2 ) {
            return new int[]{};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
