package com.myproj.app.algorithm.矩阵;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 思路：
 *      1. 2个HashSet：
 *          1.1 1个用于存储行：需要置为0的行数。
 *          1.2 1个用于存储列：需要置为0的列数。
 *
 *
 * @author shenxie
 * @date 2024/1/16
 */
public class 矩阵置零 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        setZeroes(matrix);
    }

    public static void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        int rowNums = matrix.length;
        int colNums = matrix[0].length;

        // 用hashSet存储行和列 需要置为0的数据。
        for(int i = 0; i<rowNums; i++) {
            for(int j = 0; j<colNums; j++) {
                if(0 == matrix[i][j]){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for(int i = 0; i<rowNums; i++) {
            for(int j = 0; j<colNums; j++) {
                if(rows.contains(i)){
                    matrix[i][j] = 0;
                }
                if(cols.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
