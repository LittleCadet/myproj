package com.myproj.app.algorithm.哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：true
 *
 * 解释：
 * 元素 1 在下标 0 和 3 出现。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 *
 * 解释：
 * 所有元素都不同。
 *
 * 示例 3：
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 *
 *      思路：
 *          - hashMap
 * @author shenxie
 * @date 2025/11/15
 */
public class 存在重复元素 {

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1,2,3,1}));
    }

    public static boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num:nums) {
            int times = map.getOrDefault(num, 0);
            if(++ times >= 2) {
                return true;
            }
            map.put(num, times);
        }
        return false;
    }
}
