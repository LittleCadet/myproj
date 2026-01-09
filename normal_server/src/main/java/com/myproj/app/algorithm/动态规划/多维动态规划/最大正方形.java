package com.myproj.app.algorithm.动态规划.多维动态规划;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 *
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 *
 *      思路：
 *          - 动态规划
 *
 * @author shenxie
 * @date 2025/11/14
 */
public class 最大正方形 {

    public static void main(String[] args) {
        System.out.println(maximalSquare(null));
    }

    public static int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        // 值初始化： 1
                        // 如果 i 和 j 中至少有一个为 0，则以位置 (i,j) 为右下角的最大正方形的边长只能是 1
                        dp[i][j] = 1;
                    } else {
                        // 在当前节点：获得最大的边
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    // 当前的最大边， 与 之前的最大边相比较： 获取最大边
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        // 面积= 边* 边
        return maxSide * maxSide;
    }
}
