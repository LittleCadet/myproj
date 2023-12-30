package com.myproj.app.algorithm.位运算;

import java.util.Arrays;

/**
 * 题目：
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * 思路：
 *      1. 与《只出现一次的数字》基本相同：
 *          3种情况：
 *  *                  1. 唯一数字 出现在首位。
 *  *                  2. 唯一数字 出现在中间。
 *  *                  3. 唯一数字 出现在最后一位。
 *          唯一不同的是： 元素重复次数超过两次后， times 依旧记为2. 为了保证代码与《只出现一次的数字》相同。
 *
 * @author shenxie
 * @date 2023/12/30
 */
public class 只出现一次的数字II {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2,2,3,2}));
    }

    public static int singleNumber(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int times = 1;
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i - 1] == nums[i]) {
                if(times != 2) {
                    times ++;
                }
            }else{
                times --;
            }
            if(times == 0) {
                return nums[i-1];
            }
            if(i == nums.length -1 && times == 1 ) {
                return nums[i];
            }
        }
        return Integer.MAX_VALUE;
    }
}
