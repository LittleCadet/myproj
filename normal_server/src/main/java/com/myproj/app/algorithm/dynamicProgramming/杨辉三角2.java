package com.myproj.app.algorithm.dynamicProgramming;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *  *
 *  * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *  *
 *  * 示例:
 *  *
 *  * 输入: 3
 *  * 输出: [1,3,3,1]
 *  *
 *  * 进阶：
 *  *
 *  * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * @author shenxie
 * @date 2021/7/20
 */
public class 杨辉三角2 {

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }

    public static List<Integer> getRow(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1];
        Arrays.fill(dp,1);
        for(int i = 2;i < dp.length;i++){
            for(int j = i - 1;j > 0;j--) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        List<Integer> res = Arrays.asList(dp);
        return res;
    }
}
