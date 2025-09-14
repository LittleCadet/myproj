package com.myproj.app.algorithm_二刷.数组;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 *
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 * 思路：
 *      1. 前缀积 与 后缀积。 理解下图:
 *          - 原数组：       [1       2       3       4]
 *              左部分的乘积：   1       1      1*2    1*2*3
 *              右部分的乘积： 2*3*4    3*4      4      1
 *              结果：        1*2*3*4  1*3*4   1*2*4  1*2*3*1
 *
 *          - 所以： result[i] = L[i] * R[i];
 *              - 前缀积 : L[i] = 前一个数 * 前一个前缀积
 *              - 后缀积： R[i] = 后一个数 * 后一个后缀积
 *              - L[0] : 为1： 因为第一个数的前一个数： 没有： 所以L[0] = 1
 *              - R[length - 1] ： 为1： 因为最后一个数的后一个数没有： 所以R[length - 1] = 1;
 *
 *
 * @author shenxie
 **/
public class 除自身以外数组的乘积 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4};
        productExceptSelfCopy(nums);
    }

    public static int[] productExceptSelfCopy(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] result = new int[nums.length];
        left[0] = 1;
        right[nums.length - 1] = 1;
        // 前缀积: 注意：始点
        for(int i = 1; i< nums.length; i++) {
            left[i] = left[i-1] * nums[i-1];
        }

        // 前缀积：注意：始点
        for(int i = nums.length - 2 ; i >= 0; i--) {
            right[i] = right[i+ 1] * nums[i+1];
        }

        // 除自身以外的数组的积 = 前缀积 * 后缀积
        for(int i = 0; i< nums.length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }
}
