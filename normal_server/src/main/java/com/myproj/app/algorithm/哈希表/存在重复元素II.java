package com.myproj.app.algorithm.哈希表;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目：
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 *
 * 思想：
 *      1. 方法1： 快慢指针： 即为双重for循环： 太慢。
 *      2. 方法2【推荐】： hashMap。
 *                  涉及到2个元素， 所以一重for循环即可。
 *      3. 方法3【推荐】： 滑动窗口 + set集合。
 *                  滑动窗口： 至少1个下标即可
 *      4. 元素是否相等的表达方式： 非常重要： 代表： 一道题目： 有几种做法：
 *          A == B
 *          set.add(A)的返回值为false
 *          map.containsKey(A)的返回值为true;
 * @author shenxie
 * @date 2023/12/28
 */
public class 存在重复元素II {
    public static void main(String[] args) {
        // 方法1： 快慢指针： 太慢
//        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
//        // 方法2： hashMap
        System.out.println(containsNearbyDuplicateV2(new int[]{1,2,3,1,2,3}, 2));
//        // 方法3： 滑动窗口 + set集合
//        System.out.println(containsNearbyDuplicateV3(new int[]{1,2,3,1,2,3}, 2));
    }


    /**
     * 方法3： 滑动窗口 + set集合：
     *          核心思想：
     *              1. 滑动窗口： 不一定需要2个下标， 而是至少1个下标， 而滑动窗口的另一边是： 固定长度即可。
     *              2. 元素是否相等的表达方式：
     *                  2.1 A == B
     *                  2.2 set.add(A)的返回值为false
     *                  2.3 map.containsKey(A)的返回值为true;
     *
     *                  综上： 这里用set集合判定 元素是否相等。
     *              3. 两个相同的元素的下标之差 <= k的表达方法：
     *                  if(i > k) {
             *               set.remove(nums[i - k -1]);
             *           }
             *           // 加入set失败时， 代表元素已存在， 即为两个不相等的索引下标的值 相等。 且 在滑动窗口内。
             *           if( ! set.add(nums[i])) {
             *               return true;
             *           }
     *
     */
    public static boolean containsNearbyDuplicateV3(int[] nums, int k) {
        // 用于保存当前索引 i-k【从0开始】 至 i之间的滑动窗口的值。
        // i > k时， 移除最初的元素即可。
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i< nums.length; i++){
            if(i > k) {
                set.remove(nums[i - k -1]);
            }
            // 加入set失败时， 代表元素已存在， 即为两个不相等的索引下标的值 相等。 且 在滑动窗口内。
            if( ! set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法2： hashMap：
     *      核心思想： 因为涉及到2个元素， 所以一次for循环即可。
     */
    public static boolean containsNearbyDuplicateV2(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0; i< nums.length; i++) {
            // 判定条件： 两个元素相等，且 索引之差 <= k， 则返回true;
            if(map.containsKey(nums[i]) && i - map.get(nums[i]) <= k){
                return true;
            }
            map.put(nums[i], i );
        }
        return false;
    }

    /**
     * 方法1： 快慢指针： 即为双重for循环： 执行太慢： 超时。
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int l = 0;
        int r = 1;
        boolean isBreak =false;
        while(l < nums.length) {
            while(nums[l] != nums[r]) {
                if( ++r >= nums.length) {
                    isBreak = true;
                    break;
                }
            }
            // 找到2个相同元素。 并获取下标。
            if( !isBreak && nums[l] == nums[r]) {
                // 判定right - left <= k;
                if((r - l) <= k ) {
                    return true;
                }
            }
            l++;
            r = l+ 1;
            if(r >= nums.length) {
                return false;
            }
            isBreak = false;
        }
        return false;
    }
}
