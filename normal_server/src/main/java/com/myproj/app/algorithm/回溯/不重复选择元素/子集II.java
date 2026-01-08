package com.myproj.app.algorithm.回溯.不重复选择元素;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的
 * （幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *      思路：
 *          - 回溯：
 *              - 方法1：回溯： ”相邻的相同元素不重复使用“的判定方式：i>index && nums[i-1] == nums[i]
 *              - 方法2【推荐】：回溯： ”相邻的相同元素不重复使用“的判定方式：vis[i] || (i > 0 && nums[i-1] == nums[i] && ! vis[i-1]
 *              - 与{@link 子集} 很类似：
 *                  前者：相邻的相同元素不能重复使用： vis[i] || (i > 0 && nums[i-1] == nums[i] && ! vis[i-1]
 *                      - 与{@link 全排列II}很类似：vis[i] || i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]
 *                      - 与{@link 组合总和II}很类似：visit[i] || i>0 && candidates[i] == candidates[i-1] && ! visit[i-1]
 *                  后者： 随便搞
 * @author shenxie
 * @date 2025/11/17
 */
public class 子集II {

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }

    /**
     * 方法1： 回溯： ”相邻的相同元素不重复使用“的判定方式：i>index && nums[i-1] == nums[i]
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, results, new ArrayList<>(), 0);
        return results;
    }

    private static void dfs(int[] nums, List<List<Integer>> results, List<Integer> result, Integer index){
        results.add(new ArrayList(result));
        for(int i = index; i<nums.length; i++) {
            // 相邻的相同元素不重复使用：
            if(i>index && nums[i-1] == nums[i]){
                continue;
            }
            result.add(nums[i]);
            dfs(nums, results, result, i+1);
            result.remove(result.size() - 1);
        }
    }

    /**
     * 方法2： 回溯： ”相邻的相同元素不重复使用“的判定方式：vis[i] || (i > 0 && nums[i-1] == nums[i] && ! vis[i-1]
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDupV2(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        dfsV2(nums, results, new ArrayList<>(), 0, new boolean[nums.length]);
        return results;
    }

    private void dfsV2(int[] nums, List<List<Integer>> results, List<Integer> result, Integer index, boolean[] vis){
        results.add(new ArrayList(result));
        for(int i = index; i<nums.length; i++) {
            if(vis[i] || (i > 0 && nums[i-1] == nums[i] && ! vis[i-1])){
                continue;
            }
            vis[i] = true;
            result.add(nums[i]);
            // 此处必定用 i + 1， 而不是 index + 1: 原因：
            // - index + 1: 代表： i 和 index的增长速度不一致， 会导致 i 比 index大， 从而导致 nums[0] 比 nums[1]大， 这是不符合题意的【看 例子】。
            // - i + 1: 代表： i 和 index的增长速度一致， 即为 nums[1] > nums[0] 永远成立。
            dfsV2(nums, results, result, i+1, vis);
            result.remove(result.size() - 1);
            vis[i] = false;
        }
    }


}
