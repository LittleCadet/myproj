package com.myproj.app.algorithm.动态规划;

/**
 * 题目：
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 思路：
 *      方法1：动态规划：
 *          确定最优子结构：想法： 抢劫第N家时， 能获取的最高金额是多少？
 *              所以：dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
 *          确定不用执行最优子结构都知道的结果：
 *              dp[0] = nums[0];
 *              dp[1] = Math.max(nums[0], nums[1]);
 *
 *      方法2：滚动数组： 即为变量
 *
 *
 * @author shenxie
 * @date 2023/12/17
 */
public class 打家劫舍 {

    public static void main(String[] args) {
        System.out.println(robCopy(new int[]{2,7,9,3,1}));
    }

    /**
     * 方法1： 动态规划
     */
    public static int robCopy(int[] nums) {
        int sum = 0;
        if(nums.length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length ];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i =2; i< nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[nums.length-1];
    }


    /**
     * 方法2： 滚动数组： 因为动态规划的最优子结构为：dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
     * 所以：只与两个变量 dp[i-2], dp[i-1]有关。  即为可以考虑滚动数组。 用 变量 first + second来表示。
     */
    public int rob(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int first = nums[0] ;
        int second = Math.max(first, nums[1]);
        for(int i = 2 ; i<nums.length ; i++) {
            int tmp = second;
            second = Math.max(nums[i] + first, second);
            first = tmp;
        }

        return second;

    }
}
