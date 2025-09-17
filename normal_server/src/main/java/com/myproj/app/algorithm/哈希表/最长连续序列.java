package com.myproj.app.algorithm.哈希表;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 思路：
 *      1. 方法1： 排序 + 遍历： 但排序的时间复杂度： 至少 0 log(N)。  不符合题意：
 *          连续的元素： 翻译下：就是nums[i-1] + 1 = nums[i]
 *      2. 方法2：set容器：
 *          核心： 每个数都判断一次： 这个数是不是连续序列的开头那个数
 *
 * @author shenxie
 * @date 2023/12/28
 */
public class 最长连续序列 {

    /**
     * 方法1： 排序 + 遍历
     */
    public int longestConsecutiveCopy(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        // 用于判定元素是否重复
        Set<Integer> set = new HashSet<>();
        int length = 1;
        int maxLength = 1;
        // 排序
        Arrays.sort(nums);
        for(int i =1; i< nums.length; i++) {
            // 当元素存在时， 保持length不变。
            if( ! set.add(nums[i])) {
                continue;
            }
            // 连续的元素： 翻译下：就是nums[i-1] + 1 = nums[i]
            if(nums[i-1] + 1 == nums[i]) {
                length ++;
                // 当出现多串连续元素时， 取最大length: 。
                maxLength = Math.max(length, maxLength);
            }else{
                length = 1;
            }
        }
        return maxLength;
    }


    /**
     * 方法2： set容器
     */
    public static int longestConsecutiveV2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }

        int result = 0 ;
        for(int num : set) {
            // 如果set中包含 num - 1, 则代表：当前num 一定不是连续的数中的最小值。
            // 所以 这里是 if( ! set.contains(num - 1)); 代表： 该数 可能是连续的数中的最小值。
            if( ! set.contains(num - 1)) {
                int tmp = num;
                int tmpResult = 1 ;
                // while循环， 当存在连续的数时，  tmpResult ++;
                while(set.contains( ++ tmp)) {
                    tmpResult ++;
                }
                result = Math.max(result, tmpResult);
            }
        }
        return result;
    }


}
