package com.myproj.app.algorithm_二刷.数组;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 *      思路：
 *          - 最大值：首先考虑PriorityQueue, 但因为需要保留每个滑动窗口的最大值， 所以PriorityQueue存储的是 二元组： {nums[i], i} , i 用于在窗口滑动的过程中， 不断更新该窗口内的最大值。所以需要记录下元素位置。
 *
 *
 * @author shenxie
 **/
public class 滑动窗口的最大值 {

    public static void main(String[] args) {
        int[] nums = maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (int num:nums) {
            System.out.println(num);
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 最大值的问题： 首先尝试PriorityQueue 即为 堆的数据结构， 队列的第一个元素就是最大值
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            // priorityQueue中存放的是二维数组，即为 {nums[i], i}.
            // 需要是二维数组的原因：i 用于在窗口滑动的过程中， 不断更新该窗口内的最大值。所以需要记录下元素位置。
            // 即为队首虽然是最大值， 但是超过了当前窗口的范围[i-k, i], 最会被踢出。此时队首是当前窗口最大值
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        // 将前K个元素，放入队列中
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        // 需要存储每个滑动窗口内的最大值，数量有 n-k+1 个
        int[] ans = new int[n - k + 1];
        // 将第一个滑动窗口的最大值放入ans中
        ans[0] = pq.peek()[0];
        // 从k的index开始，执行滑动窗口
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            // 队首虽然是最大值， 但是超过了当前窗口的范围（i-k, i], 实为[i-k+1, i], 最会被踢出。此时队首师当前窗口最大值
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            // 将当前队列的最大值，放入ans中
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}
