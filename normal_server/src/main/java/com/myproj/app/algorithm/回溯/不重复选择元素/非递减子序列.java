package com.myproj.app.algorithm.回溯.不重复选择元素;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 *
 * 示例 1：
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 *
 * 示例 2：
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 *
 * 思路：
 *      - 方法1：回溯 + hashSet:
 *      - 方法2：回溯 + visit: 错误: 原因：
 *          - visit的boolean数组， 只适用于 {@link 全排列} + {@link 组合总和II}： 因为：visit数组 核心解决的是：排序后数组中的1a1b场景：
 *          - 对于该题而言： 本身就不要求排序， 而是用自身的顺序去求解。
 *
 * @author shenxie
 * @date 2026/1/12
 */
public class 非递减子序列 {

    public static void main(String[] args) {
//        System.out.println(findSubsequencesV2(new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1}));
        System.out.println(findSubsequences(new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1}));
    }

    /**
     * 方法1： 回溯 + hashSet
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(nums, new ArrayList<>(), results, 0);
        return results;
    }

    private static void dfs(int[] nums, List<Integer> list, List<List<Integer>> results, int index) {
        if(list.size() >= 2) {
            results.add(new ArrayList<>(list));
        }

        // 用hashSet 保证不重复选择。
        Set<Integer> set = new HashSet<>();
        for(int i = index ; i < nums.length ; i++ ) {
            // 不包含， 且 非递增： 则不行
            if(!set.add(nums[i]) || (list.size() != 0 && list.get(list.size() - 1) > nums[i])) {
                continue;
            }


            list.add(nums[i]);
            dfs(nums, list, results, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 方法2： 错误： 用 {@link 组合总和II}的visit数组控制 是否访问过， 不行。
     * 因为： visit的boolean数组， 只适用于 {@link 全排列} + {@link 组合总和II}： 因为：visit数组 核心解决的是：排序后数组中的1a1b场景：
     * 对于该题而言： 本身就不要求排序， 而是用自身的顺序去求解。
     */
    public static List<List<Integer>> findSubsequencesV2(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(nums, new ArrayList<>(), results, 0, new boolean[nums.length]);
        return results;
    }

    private static void dfs(int[] nums, List<Integer> list, List<List<Integer>> results, int index, boolean[] visit) {
        if(list.size() >= 2) {
            results.add(new ArrayList<>(list));
        }

        for(int i = index ; i < nums.length ; i++ ) {
            if((visit[i] || ( i> 0 && nums[i - 1] == nums[i] && ! visit[i - 1]))) {
                continue;
            }
            if(i >0 && nums[i] < nums[i -1]){
                continue;
            }
            visit[i] = true;
            list.add(nums[i]);
            dfs(nums, list, results, i + 1, visit);
            list.remove(list.size() - 1);
            visit[i] = false;
        }
    }
}
