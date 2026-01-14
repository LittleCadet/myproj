package com.myproj.app.algorithm.数组;

/**
 * 给你一个整数数组 nums。在一次操作中，你可以选择一个子数组，并将其替换为一个等于该子数组 最大值 的单个元素。
 * 返回经过零次或多次操作后，数组仍为 非递减 的情况下，数组 可能的最大长度。
 * 子数组 是数组中一个连续、非空 的元素序列。
 *
 *  示例 1：
 * 输入： nums = [4,2,5,3,5]
 * 输出： 3
 * 解释：
 * 实现最大长度的一种方法是：
 *     将子数组 nums[1..2] = [2, 5] 替换为 5 → [4, 5, 3, 5]。
 *     将子数组 nums[2..3] = [3, 5] 替换为 5 → [4, 5, 5]。
 * 最终数组 [4, 5, 5] 是非递减的，长度为 3。
 *
 *      思路：
 *          非递减子数组：即为 nums[i] >= nums[i-1]即可。
 * @author shenxie
 * @date 2026/1/14
 */
public class 非递减子数组的最大长度 {
    public static void main(String[] args) {

    }
    public int maximumPossibleSize(int[] nums) {
        int count = 0, preMax = 0;
        for(int i = 0 ; i< nums.length ; i++) {
            // 如果nums[i] < preMax时： 不保留nums[i]
            // 只有在nums[i] >= preMax时， 才能做到 非递减子数组
            if(nums[i] >= preMax) {
                preMax = nums[i];
                count ++;
            }
        }

        return count;
    }
}
