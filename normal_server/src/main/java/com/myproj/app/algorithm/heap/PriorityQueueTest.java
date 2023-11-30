package com.myproj.app.algorithm.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author shenxie
 * @date 2023/11/28
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        int[] nums = {1,3,2,7,9,8};
        Queue<Integer> integers = topKHeap(nums, 3);
        System.out.println("topK的元素为： " + integers);
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
        System.out.println(integers.poll());
    }

    /**
     * topK的问题：
     * 最优解： 基于堆查找数组中最大的 k 个元素
     */
    private static Queue<Integer> topKHeap(int[] nums, int k) {
        // 初始化小顶堆
        Queue<Integer> heap = new PriorityQueue<>();
        // 将数组的前 k 个元素入堆
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        // 从第 k+1 个元素开始，保持堆的长度为 k
        for (int i = k; i < nums.length; i++) {
            // 若当前元素大于堆顶元素，则将堆顶元素出堆、当前元素入堆
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        return heap;
    }

}
