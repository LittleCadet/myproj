package com.myproj.app.algorithm_二刷.动态规划;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组
 * 是数组中的一个连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 *
 * 思路：
 *      1. 暴力解法： 首尾双指针 【即为双重for循序】
 *          - 可以，但是对于超长数组， 会计算超时
 *      2. 动态规划： 核心思想：
 *          - 当前值 和 之前的累计和的大小比较， 取大。
 * @author shenxie
 **/
public class 最大子数组和 {

    public static void main(String[] args) {
//        System.out.println(maxSubArray(new int[]{1}));
        System.out.println(maxSubArray2Copy(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    /**
     * 暴力解法： 快慢指针：即为双重for循环
     * 大部分情况可以， 但是不行：原因： 对于超长数组： 会计算超时、
     *
     */
    public static int maxSubArrayCopy(int[] nums) {
        int result = 0 ;
        for(int left = 0 ; left < nums.length; left ++) {
            int sum = 0;
            for(int right = left ; right < nums.length; right ++) {
                sum +=nums[right];
                result = Math.max(result, sum);
            }
        }
        return result;
    }

    /**
     * 动态规划： 核心思想：
     *  - 当前值 和 累计和的大小比较， 取大。
     * 最后：将最大值 与 累加和比较， 取大 即可
     *
     *
     * 此做法：不违背题意中的 “连续子数组”的概念： 因为：
     * - 当 当前值 和 累计和的大小比较： 累计和较大时， 继续往下执行即可： 即为连续子数组 【连续子数组：起始位置没有刷新】
     * - 当 当前值 和 累计和的大小比较： 累计和较小时， 则从当前位置重新累加， 继续往下执行即可： 即为连续子数组 【连续子数组：起始位置被刷新】
     * @param nums
     * @return
     */
    public static int maxSubArray2Copy(int[] nums) {
        // 需要用来比较大小， 所以整个最小值 也是可以的
        int result = Integer.MIN_VALUE ;
        int tmp = 0;
        for(int i = 0 ; i<nums.length; i++) {
            tmp = Math.max(tmp + nums[i], nums[i]);
            result = Math.max(result, tmp);
        }
        return result;
    }
}
