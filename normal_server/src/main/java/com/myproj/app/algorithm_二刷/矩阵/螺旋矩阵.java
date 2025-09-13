package com.myproj.app.algorithm_二刷.矩阵;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 解法：
 *  - 4个变量： 目的： 使用4个一层for循环， 输出元素： 而 4个变量是为了控制循环条件。
 *
 *
 * @author shenxie
 **/
public class 螺旋矩阵 {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        spiralOrder(matrix);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        int l = 0, r = matrix[0].length - 1, u = 0, d = matrix.length - 1;
        List<Integer> results = new ArrayList<>();
        while(true) {
            // 从左到右
            for(int i = l ; i<=r; i ++){
                results.add(matrix[u][i]);
            }
            if( ++ u > d) {
                break;
            }

            // 从上到下
            for(int i = u; i<=d; i++) {
                results.add(matrix[i][r]);
            }
            if( --r < l  ) {
                break;
            }
            // 从右到左
            for(int i = r; i>=l ; i--) {
                results.add(matrix[d][i]);
            }
            if(--d < u ) {
                break;
            }
            // 从下到上
            for(int i = d; i>= u; i--) {
                results.add(matrix[i][l]);
            }
            if( ++ l > r) {
                break;
            }
        }
        return results;
    }
}
