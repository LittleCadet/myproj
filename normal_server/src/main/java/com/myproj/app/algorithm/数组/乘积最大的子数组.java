package com.myproj.app.algorithm.数组;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续
 * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 *
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *      思路：
 *          - 负的找负的，正的找正的
 *
 *
 * @author shenxie
 * @date 2025/11/13
 */
public class 乘积最大的子数组 {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
    }

    public static int maxProduct(int[] nums) {
        int result = nums[0];
        // positive: 表示正数， negative: 表示负数
        int positive = Math.max(0, nums[0]), negative = Math.min(0, nums[0]);

        for(int i = 1 ; i<nums.length; i++) {
            if(nums[i]>0){
                // positive = 正数 * 正数
                positive = Math.max(nums[i], nums[i] * positive);
                // negative = 正数 * 负数
                negative = Math.min(nums[i], nums[i] * negative);
            }else{
                int tmp = positive;
                // positive = 负数 * 负数
                positive = Math.max(nums[i], nums[i] * negative);
                // negative = 负数 * 正数
                negative = Math.min(nums[i], nums[i] * tmp);
            }

            result = Math.max(result, positive);
        }

        return result;
    }
}
