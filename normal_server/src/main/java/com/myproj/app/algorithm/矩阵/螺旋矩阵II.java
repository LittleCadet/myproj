package com.myproj.app.algorithm.矩阵;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 *      思路：
 *          - 循环： 与{@link 螺旋矩阵}类似：只不过出入参 不同。
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 螺旋矩阵II {

    public static void main(String[] args) {
        generateMatrix(3);
    }

    public static int[][] generateMatrix(int n) {
        int[][] nums = new int[n][n];
        int u = 0, d = n-1 ;
        int l = 0, r = n-1 ;
        int index = 0 ;
        while(true){
            // 行： 从左到右：
            for(int i = l; i<=r; i++) {
                nums[u][i] = ++ index;
            }
            if(++u > d){
                break;
            }

            // 列： 从上到下：
            for(int i = u; i<=d; i++) {
                nums[i][r] = ++ index;
            }
            if(--r < l) {
                break;
            }
            // 行： 从右到左：
            for(int i = r; i>=l; i--) {
                nums[d][i] = ++ index;
            }
            if(--d < u) {
                break;
            }

            // 列： 从下到上：
            for(int i = d; i>=u; i--) {
                nums[i][l] = ++ index;
            }
            if(++l > r) {
                break;
            }
        }
        return nums;
    }
}
