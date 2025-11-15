package com.myproj.app.algorithm.动态规划;

import java.util.ArrayList;
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
 *      解法：
 *          - 方法1： 动态规划：
 *          - 方法2：动态规划： 推荐
 *
 * @author shenxie
 * @date 2021/7/20
 */
public class 杨辉三角2 {

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }

    /**
     * 动态规划： 用dp数组 + List
     */
    public static List<Integer> getRow(int rowIndex) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] nums = new int[rowIndex+1][rowIndex+1];
        for(int i= 0; i<= rowIndex ; i++){
            List<Integer> sub = new ArrayList<>();
            for(int j = 0 ; j<=i; j++){
                if(j==0 || i == j){
                    nums[i][j] = 1;
                }else{
                    nums[i][j] = nums[i-1][j-1] + nums[i-1][j];
                }
                sub.add(nums[i][j]);
            }
            result.add(sub);
        }
        return result.get(rowIndex);
    }

    /**
     * 动态规划： 去除dp数组
     */
    public static List<Integer> getRowV2(int rowIndex) {
        List<List<Integer>> result = new ArrayList<>();
        for(int i= 0; i<= rowIndex ; i++){
            List<Integer> sub = new ArrayList<>();
            for(int j = 0 ; j<=i; j++){
                if(j==0 || i == j){
                     sub.add(1);
                }else{
                     sub.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
                }
            }
            result.add(sub);
        }
        return result.get(rowIndex);
    }
}
