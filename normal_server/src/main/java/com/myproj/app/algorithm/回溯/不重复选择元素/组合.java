package com.myproj.app.algorithm.回溯.不重复选择元素;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 *      思路：
 *          回溯
 *
 * @author shenxie
 * @date 2025/9/19
 */
public class 组合 {

    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        process(n, k, 1,results, new ArrayList());
        return results;
    }

    private static void process(int n , int k, int index, List<List<Integer>> results, List<Integer> list){
        if(list.size() == k) {
            results.add(new ArrayList<>(list));
            return;
        }
        // 注意： i必定从index开始， 因为：题意要求：nums[1] 要比 nums[0] 大。
        // [2,4],
        //[3,4],
        //[2,3],
        //[1,2],
        //[1,3],
        //[1,4],
        for(int i = index ; i<= n; i++) {
            list.add(i);
            process(n, k, i+1, results, list);
            // 这里只能 remove list.size() - 1, 不能是 index, 因为index 可能是3，也可能是4， 但是list的size只能 是 k, k 可能比3 和 4都小。
            list.remove(list.size() - 1);
        }
    }
}
