package com.myproj.app.algorithm.动态规划.多维动态规划;

import com.myproj.app.algorithm.二叉树.二叉搜索树.不同的二叉搜索树;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 *  示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 *      思路：
 *          - 动态规划： {@link 不同路径II}的思路一致。
 *              方法1：
 *              方法2：【推荐】
 *
 *          - {@link 不同路径} && {@link 不同路径II} && {@link 不同的二叉搜索树} 类似：
 *              -- {@link 不同路径}： 网格中没有障碍物: 表达式：result[i][j] = result[i-1][j] + result[i][j-1];
 *              -- {@link 不同路径II}: 网格中有障碍物: 表达式：同上
 *              -- {@link 不同的二叉搜索树}： 表达式：dp[i] += dp[j-1] * dp[i-j]
 *
 * @author shenxie
 * @date 2025/11/15
 */
public class 不同路径 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }

    /**
     * 方法1： 动态规划： 但初始化：耽误了一些循环
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];
        for(int i = 0 ;i<m; i++) {
            result[i][0] = 1;
        }

        for(int i = 0 ; i<n; i++) {
            result[0][i] = 1;
        }

        for(int i = 1 ;i < m; i++) {
            for(int j = 1 ; j<n; j++) {
                result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }
        return result[m-1][n-1];
    }

    /**
     * 方法2： 动态规划： 解决方法1 初始化耽误循环的问题
     */
    public static int uniquePathsV2(int m, int n) {
        int[][] result = new int[m][n];

        for(int i = 0 ;i < m; i++) {
            for(int j = 0 ; j<n; j++) {
                if(i == 0 || j ==0 ){
                    result[i][j] = 1;
                }
                if( i > 0 && j >0){
                    result[i][j] = result[i-1][j] + result[i][j-1];
                }
            }
        }
        return result[m-1][n-1];
    }
}
