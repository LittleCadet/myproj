package com.myproj.app.algorithm.矩阵;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 思路：
 *      1. 核心问题： 如何花式遍历二维数组。
 *          解法：
 *              1. 用4个变量代表上下左右。
 *              2. 该题本质：将二维数组中的每个数字录入ArrayList中，
 *                  所以一层for循环， 通过控制二维数组的一维 、 二维元素的位置即可实现   =========很重要！！！！！
 * @author shenxie
 * @date 2023/12/31
 */
public class 螺旋矩阵 {
    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

    public  static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> nums = new ArrayList<>();
        int u = 0, d = matrix.length-1 ;
        int l = 0, r = matrix[0].length-1 ;
        while(true){
            // 行： 从左到右：
            for(int i = l; i<=r; i++) {
                nums.add(matrix[u][i]);
            }
            if(++u > d){
                break;
            }

            // 列： 从上到下：
            for(int i = u; i<=d; i++) {
                nums.add(matrix[i][r]);
            }
            if(--r < l) {
                break;
            }
            // 行： 从右到左：
            for(int i = r; i>=l; i--) {
                nums.add(matrix[d][i]);
            }
            if(--d < u) {
                break;
            }

            // 列： 从下到上：
            for(int i = d; i>=u; i--) {
                nums.add(matrix[i][l]);
            }
            if(++l > r) {
                break;
            }
        }
        return nums;
    }
}
