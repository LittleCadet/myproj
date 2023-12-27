package com.myproj.app.algorithm.数组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *  题目：
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1：
 * 输入：nums = [3,2,3]
 * 输出：3
 *
 *  思路：
 *      方法一： hashMap求解。
 *      方法二： 排序求解。 【最优解】
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 多数元素 {

    public static void main(String[] args) {
        System.out.println(majorityElementV1(new int[]{3,2,3}));
    }

    /**
     * 方法一：
     *      用hashMap求解
     */
    public static int majorityElementV1(int[] nums) {
        Map<Integer, Integer> maps = new HashMap<>();
        for(int i = 0; i<nums.length; i++) {
            int times = maps.getOrDefault(nums[i], 0) + 1;
            // 根据题目要求： 必定存在这种情况。 所以解只会在这里。
            if(times > nums.length / 2) {
                return nums[i];
            }
            maps.put(nums[i], times);
        }
        // 因为必定存在解， 所以这里随便写了一个数。 因为不重要。
        return Integer.MAX_VALUE;
    }

    /**
     * 方法二： 排序。
     *      因为必定存在解： 即为超过nums.length/2 次数的元素必定存在。
     *      所以：nums[nums.length / 2]必定是解。
     */
    public static int majorityElementV2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

}
