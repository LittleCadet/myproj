package com.myproj.app.algorithm.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.utils.Lists;

/**
 * 题目：
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 思路：
 * 1. 回溯算法：
 *      核心问题：
 *          1.1 如何控制给定数组元素的循环
 *              答案： for循环数组元素， 但是起始下标为 i=index.
 *          1.2 如何维护生成的新数组。
 *              答案： 替换元素： 使用Collections.swap(list, index, i);; 在list中将index和i位置的元素互换。
 *          1.3 如何撤销操作。
 *              答案： 把元素换回来即可 : Collections.swap(list, i, index);
 *          1.4 何时执行回溯操作。
 *              答案： 在1.2 - 1.3之间
 *          1.5 当前回溯完成的标志是什么 ？
 *              答案： 下标 = 给定数组的长度时， 当前回溯完成
 *
 *         1.6  关于方法2【Collections.swap】：注意：
 *              a. 要注意： 完成标志！！！
 *              b. 要注意： 如何放入lists中！！！
 *              c. 要注意： i的起始位置！！！
 *
 * @author shenxie
 * @date 2023/12/7
 */
public class 全排列 {

    /**
     * 给定数组的元素，在数字不重复的情况下，  输出所有可能的排序。
     */
    public static void main(String[] args) {
        // 方法1：
//        System.out.println(permutationsI(new int[]{1,3,2}));
//        List<Integer> list=  Lists.newArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        Collections.swap(list, 0, 4);
//        System.out.println(list);

        // 方法2：
        System.out.println(permute(new int[]{1,2,3}));
    }


    /**
     * 解法一：
     *
     */
    public static List<List<Integer>> permutationsI(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
        return res;
    }

    /**
     * 解法二：
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }
        dfs(lists, nums, 0, output);
        return lists;
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


    /**
     *
     * @param lists 输出的集合
     * @param nums 输入的数组
     * @param index 起始下标
     * @param list 子集合
     */
    public static void dfs(List<List<Integer>> lists, int[] nums, int index, List<Integer> list){
        // 一次组合完成的标志
        // 要注意： 完成标志！！！
        if(index == nums.length) {
            // 需要new ArrayList(), 而不是直接使用list的原因： list在堆中一直都是同一个地址， 即为变更N次， 都是最后一次都数据。
            System.out.println("结束："+ list);
            // 要注意： 如何放入lists中！！！
            lists.add(new ArrayList<>(list));
        }else{
            // 如何控制给定数组元素的循环。
            // 要注意： i的起始位置！！！
            for(int i = index; i< nums.length; i++) {
                // 维护操作：将index 和 i的位置互换
                Collections.swap(list, index, i);
                System.out.println("swap前：index:" + index + ", i:" + i + ",list:"+ list);
                // 回溯
                dfs(lists, nums, index + 1, list);
                // 撤销操作：将i 和 index的位置互换
                Collections.swap(list, i, index);
                System.out.println("swap后：index:" + index + ", i:" + i + ",list:"+ list);
            }
        }
    }

}
