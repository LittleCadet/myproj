package com.myproj.app.algorithm.回溯.不重复选择元素;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * @author shenxie
 * @date 2025/11/3
 */
public class 子集 {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2}));
    }

    private static List<List<Integer>> res;
    /*
    和全排列类似，但是比全排列简单，全排列需要考虑那些元素已经放入了，所以需要动态维护一个集合，但是本题完全可以按照着数组的顺序来递归,
    如下图，遍历到最后一个元素时会自动不进入for循环，不用担心会有重复集合
    回溯题目的关键是在找到合适的树形结构
     123
    / | \
    1 2 3
    /\ \
    2 3 3
   /
   3
     */
    public static List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        backTracking(nums, 0, new ArrayList<>());
        return res;
    }

    /**
     *
     * @param nums 源数组，不需要动态维护
     * @param index 往集合中开始插入的位置
     * @param list 用于存放各种子集
     */
    public static void backTracking(int[] nums,int index,List<Integer> list) {
        System.out.println("index:" + index);
        //任何一个能进入递归的都一定是一个正确的集合，因为源数组每个元素都不相同，并且1只能和2或3组成集合,2只能和3组成集合,3只能和自己，
        //所以进入的一定是唯一的
        res.add(new ArrayList<>(list));
        //按层遍历 !!!
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            System.out.println("全排列前：list:" + list +",res: " + res);
            backTracking(nums,i+1,list);//递归
            list.remove(list.size()-1);//回溯
            System.out.println("全排列后：list:" + list + ", res: " + res);
        }
    }
}
