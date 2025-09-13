package com.myproj.app.algorithm_二刷.矩阵;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 *     每行的元素从左到右升序排列。
 *     每列的元素从上到下升序排列。
 *
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 思路：
 *      - 根据题目要求： 先找到target， 之后看target所在行列， 是否满足升序要求， 即可
 * @author shenxie
 **/
public class 搜索二维矩阵II {


    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,5}, {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30} };
        searchMatrix(matrix, 5);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int okm = -1;
        int okn = -1;
        // 找到target，并映射对应的行 和 列;
        for(int i = 0; i < m ; i++) {
            for(int j = 0 ; j<n; j++) {
                if(target == matrix[i][j]) {
                    okm = i;
                    okn = j;
                    break;
                }
            }
        }

        // 没有找到就返回
        if(okm == -1) {
            return false;
        }

        // target对应行， 是否满足升序要求
        int tmp = matrix[okm][0];
        for(int i = 1; i < okn; i++) {
            if(tmp > matrix[okm][i]) {
                return false;
            }
            tmp = matrix[okm][i];
        }

        // target 对应列， 是否满足升序要求。
        tmp = matrix[0][okn];
        for(int i = 1; i < okm; i++) {
            if(tmp > matrix[i][okn]) {
                return false;
            }
            tmp = matrix[i][okn];
        }

        return true;
    }
}
