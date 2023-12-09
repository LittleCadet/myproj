package com.myproj.app.algorithm.数组;

/**
 * 题目；
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 *  思路：
 *  1. 贪心算法：以当前位置i 最大连续跳跃 nums[i]的位置： 即为当前位置的最大连续跳跃到的位置为max：i+nums[i]。
 *  2. 即为： 只要max > num.length -1 ， 则代表： 可以完成跳跃到最后一个下标的任务。 否则： 不行。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 跳跃游戏 {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,3,1,1,4}));
    }

    public static boolean canJump(int[] nums) {
        // 贪心
        int max = 0;
        for(int i = 0 ; i < nums.length; i++) {
            // 最大跳跃长度为max
            if(i <= max) {
                max = Math.max(max, i + nums[i]);
                if( max >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
