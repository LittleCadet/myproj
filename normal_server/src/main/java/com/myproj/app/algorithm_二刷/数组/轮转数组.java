package com.myproj.app.algorithm_二刷.数组;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 *
 * 思路：
 *      1. 使用额外数组： 做暂存即可。
 *          - 注意：
 *              - 要让数组顺时针转动！！！ 而不是逆时针！！！
 *
 * @author shenxie
 **/
public class 轮转数组 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rotateCopy(nums, 3 );
        for (int num :nums) {
            System.out.println(num);
        }
    }

    public static void rotateCopy(int[] nums, int k) {
        int length = nums.length;
        // 新建数组
        int[] results = new int[length];
        for(int i = 0 ; i< length; i++) {
            // 该解法错误：原因： 让数组逆时针转动。
//            results[i] = nums[(i + k) % length];
            // 让数组顺时针转动
            results[(i + k) % length] = nums[i];
        }
        // 数组复制
        System.arraycopy(results, 0, nums, 0, length);

    }
}
