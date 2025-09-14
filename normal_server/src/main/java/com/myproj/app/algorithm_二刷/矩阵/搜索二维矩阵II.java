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
        searchMatrixCopy(matrix, 5);
    }

    public static boolean searchMatrixCopy(int[][] matrix, int target) {
        int x = matrix.length;
        int y = matrix[0].length;
        int targetX = Integer.MIN_VALUE ;
        int targetY = Integer.MIN_VALUE;
        // 找到target的坐标
        for(int i = 0 ; i < x; i++) {
            for(int j = 0; j < y; j++) {
                if(target == matrix[i][j]) {
                    targetX = i;
                    targetY = j;
                    break;
                }
            }
        }

        // 没有找到就返回
        if(targetX == Integer.MIN_VALUE || targetY == Integer.MIN_VALUE) {
            return false;
        }

        // 对比target的X轴是否满足
        for(int i = 1; i<x; i++) {
            if(matrix[i-1][targetY] > matrix[i][targetY]){
                return false;
            }
        }

        // 对比target的Y轴是否满足
        for(int j = 1; j<y; j++) {
            if(matrix[targetX][j-1] > matrix[targetX][j]) {
                return false;
            }
        }

        return true;
    }
}
