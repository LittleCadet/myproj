package com.myproj.app.algorithm_二刷.双指针;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 * 子数组是数组中元素的连续非空序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 思路：
 *      1. 快慢双指针：  【因为要求连续非空序列：所以快慢双指针很合适】
 *          - 快指针： 从慢指针处 开始。 在数组末尾结束。
 *          - 移动慢指针的时间： 快指针走完一圈， 慢指针 +1
 *          - 何时计算子数组的个数： 快指针的累加和 = k， 则count ++;
 * @author shenxie
 **/
public class 和为k的子数组 {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, -1, 0}, 0));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0 ;
        int length = nums.length;
        for(int i = 0 ; i< length; i++) {
            int sum = 0 ;
            for(int j = i ; j < length; j++ ) {
                sum += nums[j];
                // 当和相等的时候， count +1
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}
