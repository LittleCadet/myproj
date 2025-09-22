package com.myproj.app.algorithm.堆;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,1,1,2,2,3], k = 2
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 示例 3：
 * 输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * 输出：[1,2]
 *
 * 思路：
 *      priorityQueue + HashMap: 即可
 *
 * @author shenxie
 * @date 2025/9/18
 */
public class 前K个高频元素 {

    public static void main(String[] args) {
        topKFrequent(new int[]{5,3,1,1,1,3,73,1}, 2);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        int[] results = new int[k];
        Map<Integer, Integer> map = new HashMap<>();

        /**
         * 大顶堆： 即为按照出现频率的高低：倒序
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a1, Integer a2) {
                return map.get(a2) - map.get(a1);
            }
        });

        // 计数每个num的出现次数。
        for(int i = 0 ; i <nums.length; i++) {
            int times = map.getOrDefault(nums[i], 1);
            map.put(nums[i], ++ times );
        }

        for(Integer key : map.keySet()) {
            queue.offer(key);
        }

        for(int i = 0 ; i<k; i++) {
            results[i] = queue.poll();
        }
        return results;
    }

    static class A{
        int num;
        int times;

        public A(int num, int times) {
            this.num = num;
            this.times = times;
        }
    }
}
