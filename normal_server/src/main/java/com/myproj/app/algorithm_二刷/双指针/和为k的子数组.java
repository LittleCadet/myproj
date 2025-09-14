package com.myproj.app.algorithm_二刷.双指针;

import java.util.Arrays;

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
        System.out.println(subarraySumCopy(new int[]{1,1,1}, 2));
    }


    public static int subarraySumCopy(int[] nums, int k) {
        int count = 0 ;
        int sum = 0 ;
        for(int left = 0 ; left < nums.length; left++) {
            // 题意要求：连续非空序列的和， 所以在此处置为0
            sum = 0;
            for(int right = left; right < nums.length; right ++) {
                // 累计求和
                sum += nums[right];
                if(sum == k) {
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * 错误解法：不能因为累加和 > k 时， 就开始移动左指针。
     *
     * 因为 没有经过排序 [题目要求： 连续非空子数组， 所以不能排序]， 所以后续的元素累加时， 依旧可能等于 K;
     */
    public static int subarraySumCopyV2(int[] nums, int k) {
        int count = 0 ;
        int sum = 0 ;
        for(int left = 0 ; left < nums.length; left++) {
            sum = 0;
            for(int right = left; right < nums.length; right ++) {
                // 累计求和
                sum += nums[right];
                if(sum == k) {
                    count ++;

                // 错误： 因为 没有经过排序， 所以后续的元素累加时， 依旧可能等于 K;
                }else if(sum > k ) {
                    break;
                }
            }
        }
        return count;
    }
}
