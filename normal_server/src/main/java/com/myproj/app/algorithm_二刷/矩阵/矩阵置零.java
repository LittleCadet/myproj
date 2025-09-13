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
        setZeroes(matrix);
    }

    public static void setZeroes(int[][] matrix) {
        int l1 = matrix.length;
        int l2 = matrix[0].length;
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for(int i = 0 ; i < l1 ; i++) {
            for(int j = 0 ; j<l2; j++) {
                if(matrix[i][j] == 0) {
                    set1.add(i);
                    set2.add(j);
                }
            }
        }

        for(int i = 0 ; i<l1; i++) {

            for(int j = 0 ; j<l2; j++) {
                if(set1.contains(i) || set2.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
