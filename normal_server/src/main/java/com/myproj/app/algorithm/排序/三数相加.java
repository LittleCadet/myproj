package com.myproj.app.algorithm.排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 题目：
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：
 * 1. 答案中不可以包含重复的三元组， 这里的 ”重复的三元组“的含义是： 输出的顺序和三元组的顺序并不重要。即为：[-1, 0, 1] 与 [-1， 1，,0] 是同一组答案。
 *
 *
 * 解题思路：
 * 1. 先列出限制： 即列出所有不可能出现答案的限制。
 * 2. 先排序
 * 3. 确定一， 找二， 再找三。
 *  3.1 for循环: 确定一
 *  3.2 for循环：确定二
 *  3.3 通过三 = - (一 + 二) ： 确定三。
 *  3.4 通过set容器， 判定三是否在其中即可。
 *
 *  备注： set容器之所以可以通过第二重for循环放入元素的原因： 输出的顺序和三元组的顺序并不重要。即为：[-1, 0, 1] 与 [-1， 1，,0] 是同一组答案。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 三数相加 {

    public static void main(String[] args) {
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(threeSum(new int[]{0,0,0,0}));
        System.out.println(threeSum(new int[]{-1,0,1}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> millionYuanList = new ArrayList<>();
        //人都不够仨，还三胎。。。
        if(nums.length < 3) {
            return millionYuanList;
        }

        Arrays.sort(nums);      //孩子们按个头排好队
        System.out.println("排序后的数组：[-4, -1, -1, 0, 1, 2]" );
        for(int i = 0; i < nums.length; i++) {
            //如果老大都大于0，后面的兄弟肯定都大于0，直接返回就行
            if(nums[i] > 0) break;
            Integer first = nums[i];    //老大出列，站好别动

            //老大想再往后占个位，多领一次奖，这可是不行滴。。。还是回家让妈妈再给生三个小弟弟吧^_^
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            };

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
                    while(j < nums.length - 1 && nums[j] == nums[j + 1]) j++;
                }
                set.add(third);
            }
            System.out.println("老二数组：" + set);
        }
        return millionYuanList;

    }
}
