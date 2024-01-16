package com.myproj.app.algorithm.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 题目：
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 思路：
 *      1. 排序 + 双重for循环：
 *          核心思想：
 *              1.1 定一定二找三。
 *              1.2 为了让三在一次for循环中找到： 所以： 三必须放入set集合中。
 *          去重问题：【题目要求】
 *              1.3 因为双重for循环， 所以： 相邻元素： 至少重复使用一次【起始位置就重复】，最多重复使用两次【非起始位置】
 *
 * @author shenxie
 * @date 2024/1/3
 */
public class 三数之和 {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,1,-4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> millionYuanList = new ArrayList<>();
        //人都不够仨，还三胎。。。
        if(nums.length < 3) {
            return millionYuanList;
        }

        Arrays.sort(nums);      //孩子们按个头排好队
        for(int i = 0; i < nums.length; i++) {
            //如果老大都大于0，后面的兄弟肯定都大于0，直接返回就行
            if(nums[i] > 0) break;
            int first = nums[i];    //老大出列，站好别动

            //老大想再往后占个位，多领一次奖，这可是不行滴。。。还是回家让妈妈再给生三个小弟弟吧^_^
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            //画个圈，让各家老二在里面呆着
            Set<Integer> set = new HashSet<>();
            for(int j = i + 1; j < nums.length; j++) {
                //老三出列，一会你和老大一块到圈里找老二
                int third = nums[j];
                int second = -(first + third);      //目标是：老大 + 老二 + 老三 = 0
                //找到老二了，记到中奖名单上
                if(set.contains(second)) {
                    millionYuanList.add(new ArrayList<>(Arrays.asList(first, third, -(first + third))));

                    //老三也想多领奖。。。额。。。等会一块回家找妈妈去吧
                    // 放在if判定中的原因： 保证：0,0,0这个场景： 能被录入。
                    // while用j循环的原因： 保证第二重for的循环中： 该元素只被执行一次。
                    while(j < nums.length - 1 && nums[j] == nums[j + 1]) {
                        j++;
                    }
                }
                set.add(third);
            }
        }
        return millionYuanList;

    }
}
