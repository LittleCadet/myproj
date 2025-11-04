package com.myproj.app.algorithm.动态规划;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 *
 * 示例 1：
 * A  1  1
 * 1  2  3
 * 1  3  B
 *
 * 输入：m = 3, n = 3
 * 输出：3 + 3 = 6
 *
 * @author shenxie
 * @date 2021/7/20
 */
public class 不同路径 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(4,4));
    }

    public static int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];

        // 数据初始化
        for(int i = 0 ;i<m; i++) {
            result[i][0] = 1;
        }

        for(int i = 0 ; i<n; i++) {
            result[0][i] = 1;
        }

        // 动态规划：
        for(int i = 1 ;i < m; i++) {
            for(int j = 1 ; j<n; j++) {
                result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }
        return result[m-1][n-1];
    }
}
