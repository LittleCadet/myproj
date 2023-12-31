package com.myproj.app.algorithm.图;

/**
 * 题目：
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * 示例 1：
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 *
 * 思路：
 *      1. 递归：
 *          核心思想：
 *              1.1 超过边界： 不管
 *              1.2 水 、 被检索过的岛屿： 不管
 *              1.3 未被检索过的岛屿 + 1 + 其他四周陆地的面积
 * @author shenxie
 * @date 2023/12/31
 */
public class 岛屿的最大面积 {

    public static int maxAreaOfIsland(int[][] grid) {
        int maxSum = 0;
        for(int i = 0; i<grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    maxSum = Math.max(dfs(grid, i, j), maxSum);
                }
            }
        }
        return maxSum;
    }

    private static int dfs(int[][] grid, int i, int j){
        // 超过边界： 不管
        if(isOverSize(grid, i, j)) {
            return 0;
        }
        // 水 、 被检索过的岛屿： 不管
        if(grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = 2;

        //   1【未被检索过的岛屿】 + 其他四周陆地的面积
        return 1 +
                dfs(grid, i+1, j) +
                dfs(grid, i-1, j) +
                dfs(grid, i, j+1) +
                dfs(grid, i, j-1);
    }

    private static boolean isOverSize(int[][] grid, int i, int j){
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }

}
