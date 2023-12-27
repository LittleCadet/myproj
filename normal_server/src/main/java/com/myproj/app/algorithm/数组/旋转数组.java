package com.myproj.app.algorithm.数组;

/**
 * 题目：
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 * 思路：
 *      1. 用额外数组来求解：
 *          1.1 核心思想：取余：
 *              将第i个元素 赋值给 (i+k) % n 。
 *          1.2 最后将新数组的值给到原数组即可。
 *              System.arraycopy(newArr, 0 , num, 0, n);
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 旋转数组 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        int k = 3;
        rotate(nums, k);
        System.out.println(nums);
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

}
