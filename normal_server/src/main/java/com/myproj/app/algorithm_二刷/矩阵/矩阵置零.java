package com.myproj.app.algorithm_二刷.矩阵;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 示例 2：
 *
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * 思路：
 *      1. 看到矩阵：一定会有双重for循环。 要舍得用循环。
 *      2. 2次双重for循环：
 *          - 第一次：记录出现0的每一行和每一列
 *          - 第二次：将出现0的每一行和每一列 置为0.
 *
 * @author shenxie
 **/
public class 矩阵置零 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        setZeroesCopy(matrix);
    }

    public static void setZeroesCopy(int[][] matrix) {
        int x = matrix.length;
        int y = matrix[0].length;
        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();

        // 找到所有0的x和y轴
        for(int i = 0 ; i < x; i++) {
            for(int j = 0 ; j < y; j++) {
                if(matrix[i][j] == 0 ) {
                    xSet.add(i);
                    ySet.add(j);
                }
            }
        }

        // 替换 0 的x和y轴
        for(int i = 0 ; i<x; i++) {
            for(int j = 0 ; j < y; j++) {
                if(xSet.contains(i) || ySet.contains(j)) {
                    matrix[i][j] = 0;
                }

            }
        }
    }
}
