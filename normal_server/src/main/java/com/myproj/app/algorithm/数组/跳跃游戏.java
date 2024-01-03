package com.myproj.app.algorithm.数组;

/**
 * 题目；
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 思路：
 * 1. 贪心算法：以当前位置i 最大连续跳跃 nums[i]的位置： 即为当前位置的最大连续跳跃到的位置为max：i+nums[i]。
 * 2. 即为： 只要max > num.length -1 ， 则代表： 可以完成跳跃到最后一个下标的任务。 否则： 不行。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 跳跃游戏 {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,0,8,2,0,0,1}));
    }

        public static boolean canJump(int[] nums) {
        // 贪心
        // 最大跳跃长度为max
        int max = 0;
        for(int i = 0 ; i < nums.length; i++) {
            // 这个判定必须要有： 要避免： 在每个位置都执行跳跃的动作
            // 一定要表达出： 最远只能调到max, 超过max就不能跳跃了。
            if(i <= max) {
                // Math.max绝对不能少， 因为要表达出：在max的范围内， 每一步都可以跳跃，并更新跳跃的最远距离。
                max = Math.max(max, i + nums[i]);
                if( max >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
