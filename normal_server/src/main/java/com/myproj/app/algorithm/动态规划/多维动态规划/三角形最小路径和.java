package com.myproj.app.algorithm.动态规划.多维动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 思路：
 *      1. 方法1： 递归【自底向上】+ 记忆数组。
 *      2. 方法2： 动态规划【自底向上】
 *      3. 2个方法核心思想：
 *              当前节点的最小路径和 = 当前节点值 + 下一行的相邻2个节点的最小值。
 *              该三角形： 是长宽都相等的三角形！！！
 * @author shenxie
 * @date 2023/12/31
 */
public class 三角形最小路径和 {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> ints1 = new ArrayList<>();
        ints1.add(2);
        List<Integer> ints2 = new ArrayList<>();
        ints2.add(3);
        ints2.add(4);
        List<Integer> ints3 = new ArrayList<>();
        ints3.add(6);
        ints3.add(5);
        ints3.add(7);
        List<Integer> ints4 = new ArrayList<>();
        ints4.add(4);
        ints4.add(1);
        ints4.add(8);
        ints4.add(3);
        triangle.add(ints1);
        triangle.add(ints2);
        triangle.add(ints3);
        triangle.add(ints4);
//        System.out.println(minimumTotal(triangle));
        System.out.println(minimumTotalV2(triangle));
    }

    static Integer[][] nums ;

    /**
     * 方法一： 递归【自底向上】 + 记忆数组：
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        nums = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }

    private static int dfs(List<List<Integer>> triangle, int i , int j ){
        if(i >= triangle.size()) {
            return 0;
        }

        if(nums[i][j] != null) {
            return nums[i][j];
        }
        return nums[i][j] = Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }

    /**
     * 方法2： 动态规划: 自底向上求解。
     */
    public static int minimumTotalV2(List<List<Integer>> triangle) {
        int size = triangle.size();
        // 数组大小为 size + 1的原因： nums[j + 1]中j的最大值为size - 1.  所以j + 1: 最大值为size - 1 + 1 = size.
        // 而nums[size] 会数组下标越界。
        int[] nums = new int[size + 1];
        // 自底向上：
        for(int i = size -1 ; i>=0; i--) {
            // 该三角形： 是长宽都相等的三角形！！！
            for(int j = 0; j <= i; j++) {
                // 思想： 当前节点的最小路径和 = 当前节点 + 下一行的相邻2个节点的最小值。
                nums[j] = Math.min(nums[j], nums[j+1]) + triangle.get(i).get(j);
            }
        }
        return nums[0];
    }
}
