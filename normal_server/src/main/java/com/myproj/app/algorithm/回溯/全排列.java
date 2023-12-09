package com.myproj.app.algorithm.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shenxie
 * @date 2023/12/7
 */
public class 全排列 {

    /**
     * 给定数组的元素，在数字不重复的情况下，  输出所有可能的排序。
     */
    public static void main(String[] args) {
        System.out.println(permutationsI(new int[]{1,3,2}));
    }

    /* 全排列 I */
    public static List<List<Integer>> permutationsI(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
        return res;
    }

    /**
     *
     * @param state 直到目前为止， 已被选择的元素
     * @param choices 数组元素
     * @param selected
     * @param res 所有可能的排序结果
     */
    /* 回溯算法：全排列 I */
    public static void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // 当状态长度等于元素数量时，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<Integer>(state));
            return;
        }
        // 遍历所有选择
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            // 剪枝：不允许重复选择元素
            if (!selected[i]) {
                // 尝试：做出选择，更新状态
                selected[i] = true;
                state.add(choice);
                // 进行下一轮选择
                backtrack(state, choices, selected, res);
                // 回退：撤销选择，恢复到之前的状态
                selected[i] = false;
                state.remove(state.size() - 1);
            }
        }
    }

}
