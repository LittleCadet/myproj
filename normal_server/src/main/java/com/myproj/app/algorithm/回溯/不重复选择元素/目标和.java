package com.myproj.app.algorithm.回溯.不重复选择元素;

/**
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *     例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 *  思路：
 *      - 递归
 *
 * @author shenxie
 * @date 2026/1/12
 */
public class 目标和 {

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1,1,1,1,1}, 3));
    }

    static int ans = 0;
    public static int findTargetSumWays(int[] nums, int target) {
        dfs(0,target,0, nums);
        return ans;
    }


    private static void dfs(int i,int target, int sum, int[] nums){
        if(i == nums.length){
            if(sum == target) {
                ans++;
            }
            return;
        }
        // 代表： 执行 加法
        dfs(i + 1,target, sum + nums[i], nums);
        // 代表： 执行 减法
        dfs(i + 1,target, sum - nums[i], nums);
    }
}
