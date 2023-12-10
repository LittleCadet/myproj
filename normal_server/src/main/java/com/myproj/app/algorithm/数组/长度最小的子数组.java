package com.myproj.app.algorithm.数组;

/**
 * 题目：
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 思路：
 * 1. 滑动窗口：
 * 2. 最小的连续子数组 的理解： 移动右指针 且 当sum >= target时， 需要继续移动左指针，并累减左指针的值， 当不满足条件的时候， 重新移动右指针。
 *                          为了达到此要求： 右指针只能在外层， 左指针只能在内层。
 *                          因为如果右指针在内层， 会导致sum >= target时，停不下来，直到right < nums.length, 才能移动左指针， 从而浪费cpu的执行时间
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 长度最小的子数组 {

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public static int minSubArrayLen(int target, int[] nums) {

        // 滑动窗口。
        int right = 0;
        int left = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while(right < nums.length) {
            sum += nums[right];
            while(sum >= target) {
                // 最小的连续子数组 的理解： 移动右指针 且 当sum >= target时， 需要继续移动左指针，并累减左指针的值， 当不满足条件的时候， 重新移动右指针。
                // 为了达到此要求： 右指针只能在外层， 左指针只能在内层。
                // 因为如果右指针在内层， 会导致sum >= target时，停不下来，直到right < nums.length, 才能移动左指针， 从而浪费cpu的执行时间
                ans = Math.min(ans, right - left + 1);
                sum -=nums[left];
                left++;
            }
            right ++;
        }

        return ans == Integer.MAX_VALUE ? 0:ans;
    }
}
