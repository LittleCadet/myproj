package com.myproj.app.algorithm.区间;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，
 * 并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 *     "a->b" ，如果 a != b
 *     "a" ，如果 a == b
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * 思路：
 *      1. 对题目的理解： 要求连续的元素形成区间：连续即为： nums[i] = nums[i - 1] + 1;
 *      2. 共有4种情况：
 *          2.1 未到最后一个元素时： 一个都不连续的场景：
 *          2.2 未到最后一个元素时， 连续的场景。
 *          2.3 到最后一个元素时， 一个都不连续的场景。
 *          2.4 到最后一个元素时， 连续的场景。
 *      3. 起点： 在 连续 、 断续的时候， 指定。
 *
 * @author shenxie
 * @date 2023/12/29
 */
public class 汇总区间 {

    public static void main(String[] args) {
//        System.out.println(summaryRanges(new int[]{0,1,2,4,5,7}));
        System.out.println(summaryRanges(new int[]{0,2,3,4,6,8,9}));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int start = Integer.MAX_VALUE;

        if(nums.length == 1) {
            result.add(nums[0] + "");
            return result;
        }

        for(int i = 1; i< nums.length; i++) {
            if(nums[i-1] + 1 == nums[i]) {
                if(start == Integer.MAX_VALUE) {
                    start = i - 1;
                }
                // 最后一个元素，连续
                if( i == nums.length - 1){
                    result.add(nums[start] + "->" + nums[i]);
                }
            }else{
                // 非最后一个元素，但是不连续，逐个逐个区间, 则处理 之前的元素
                if(start == Integer.MAX_VALUE) {
                    result.add(nums[i - 1] + "");
                }else{
                    // 非最后一个元素，但之前的连续， 则处理 之前的元素
                    result.add(nums[start] + "->" + nums[i - 1]);
                }

                // 最后一个元素了， 但是不连续， 则处理当前元素。
                if( i == nums.length - 1) {
                    result.add(nums[i] + "");
                }


                start = Integer.MAX_VALUE;
            }
        }
        return result;
    }
}
