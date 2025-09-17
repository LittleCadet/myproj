package com.myproj.app.algorithm.堆;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 * 思路：
 *      1. 方法1： PriorityQueue
 *                  在构造方法中： 重写Comparator的compare方法
 *      2. 方法2： 数组排序
 * @author shenxie
 * @date 2023/12/30
 */
public class 数组中第K个最大元素 {
    public static void main(String[] args) {
        System.out.println(findKthLargestCopy(new int[]{3,2,1,5,6,4}, 3));
        System.out.println(findKthLargestV2(new int[]{3,2,1,5,6,4}, 3));
    }

    /**
     * 方法2：数组排序
     */
    public static int findKthLargestV2(int[] nums, int k) {
        int ans = 0;
        Arrays.sort(nums);
        for(int i =nums.length -1; i >= nums.length -k ; i--) {
            ans = nums[i];
        }
        return ans;
    }


    /**
     * 方法1： PriorityQueue： 大顶堆。
     */
    public static int findKthLargestCopy(int[] nums, int k) {
        int ans = 0;
        // 默认正序排序【从小到大】， 需要倒序【从大到小】
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer> () {
            public int compare(Integer i1, Integer i2){
                return i2 - i1;
            }
        });
        for(int i = 0; i<nums.length; i++) {
            queue.offer(nums[i]);
        }
        while(k -- > 0) {
            ans = queue.poll();
        }
        return ans;
    }
}
