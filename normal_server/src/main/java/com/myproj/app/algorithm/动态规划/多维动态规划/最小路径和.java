package com.myproj.app.algorithm.动态规划.多维动态规划;

/**
 * 题目：
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 思路：
 *      1. 动态规划：
 *          核心思想： 任意单元格的路径和最小 = 左边 和 上边单元格的路径和的最小值 + 当前节点的值
 *              ans[i][j] = Math.min(ans[i][j - 1], ans[i - 1][j]) + grid[i][j];
 *          坑点：需要为动态规划的数据准备：
 *              因为：动态规划执行时， 要求从i = 1 和 j = 1开始循环， 所以要为第一行 和 第一列的数据做准备！！！！！
 *
 * @author shenxie
 * @date 2023/12/31
 */
public class 最小路径和 {
    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }
    public static int minPathSum(int[][] grid) {
        int[][] ans = new int[grid.length][grid[0].length];
        ans[0][0] = grid[0][0];
        // 第一行： 各个元素累加值
        // 动态规划： 数据准备阶段
        for(int i =1 ; i< grid[0].length; i++) {
            ans[0][i] = ans[0][i - 1] + grid[0][i];
        }
        // 第一列： 各个元素累加值
        // 动态规划， 数据准备阶段
        for(int i = 1; i< grid.length; i++) {
            ans[i][0] = ans[i - 1][0] + grid[i][0];
        }

        // 动态规划
        for(int i = 1; i<grid.length; i++) {
            for(int j = 1; j < grid[0].length; j++) {
                ans[i][j] = Math.min(ans[i][j - 1], ans[i - 1][j]) + grid[i][j];
            }
        }
        return ans[grid.length - 1][grid[0].length - 1];
    }
}
