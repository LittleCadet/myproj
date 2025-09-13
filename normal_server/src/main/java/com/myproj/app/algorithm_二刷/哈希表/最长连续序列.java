package com.myproj.app.algorithm_二刷.哈希表;

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
 *      1. 排序 + 连续的元素： 翻译下：就是nums[i-1] + 1 = nums[i]
 * @author shenxie
 * @date 2023/12/28
 */
public class 最长连续序列 {

    public int longestConsecutiveCopy(int[] nums) {
        int maxLength = 0;
        // length必须设置为1， 不然更新maxLength时， 是从0 开始计算的
        int length = 1;
        // 排序
        Arrays.sort(nums);

        // 判定元素是否重复
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i < nums.length; i++) {
            // 当元素存在时， 保持length不变
            if(set.add(nums[i])) {
                continue;
            }
            // 连续的元素： 翻译下：就是nums[i-1] + 1 = nums[i]
            // 更新maxLength
            if(nums[i] == nums[i-1] + 1) {
                maxLength = Math.max(maxLength, ++ length);
            }else{
                length = 1;
            }
        }
        return maxLength;
    }
}
