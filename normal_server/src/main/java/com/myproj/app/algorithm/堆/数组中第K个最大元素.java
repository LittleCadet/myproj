package com.myproj.app.algorithm.堆;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 思路：
 *      1. 方法1： PriorityQueue
 *                  在构造方法中： 重写Comparator的compare方法
 *      2. 方法2： 数组排序
 * @author shenxie
 * @date 2023/12/30
 */
public class 数组中第K个最大元素 {
    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 3));
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
    public static int findKthLargest(int[] nums, int k) {
        int ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer> () {
            public int compare(Integer i1, Integer i2){
                if(i1 < i2) {
                    return 1;
                }else if(i1 == i2) {
                    return 0;
                }else{
                    return -1;
                }
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
