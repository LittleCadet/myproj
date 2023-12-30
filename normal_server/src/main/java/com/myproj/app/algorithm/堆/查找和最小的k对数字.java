package com.myproj.app.algorithm.堆;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 题目：
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * 思路：
 *      1. 大顶堆：
 *          使用大顶堆的原因：
 *              1. 在堆中保留 K 个元素， 当超过K个元素时， 堆顶 是最大值， 直接queue.peek() 与 当前元素比较 ， 取小入堆中。
 *                  这样就可实现： 大顶堆中放入的是top K小的元素了。
 *          核心思路：
 *              1. 降低内存： 在大顶堆中最多存入K个元素。
 *              2. 降低RT: 因为是递增序列： 所以 当queue.peek().sum <= nums1[i] + nums2[j]时， 第二层for循环直接break.
 *
 *
 * @author shenxie
 * @date 2023/12/30
 */
public class 查找和最小的k对数字 {

    public static void main(String[] args) {
        System.out.println(kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 2));
    }
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> numsLists = new ArrayList<>();
        List<Integer> nums= null;
        // 大顶堆
        PriorityQueue<A> queue = new PriorityQueue<>(new Comparator<A>(){
            @Override
            public int compare(A a1, A a2){
                return a2.sum - a1.sum;
            }
        });
        // 在大顶堆中放入k个元素 ， 避免：内存超限。
        for(int i = 0; i < nums1.length; i++) {
            for(int j =0; j < nums2.length; j++) {
                if(queue.size() < k){
                    queue.offer(new A(nums1[i],nums2[j], nums1[i] + nums2[j]));
                }else{
                    if(queue.peek().sum > nums1[i] + nums2[j]){
                        queue.poll();
                        queue.offer(new A(nums1[i],nums2[j], nums1[i] + nums2[j]));
                    }else{
                        // 因为是入参是递增数组。 所以： 后面都不用看了。
                        // 避免时间超限。
                        break;
                    }
                }
            }
        }

        // 取出大顶堆中的所有内容。
        for(A a : queue){
            nums = new ArrayList<>();
            nums.add(a.a);
            nums.add(a.b);
            numsLists.add(nums);
        }
        return numsLists;
    }
    static class A{
        int a;
        int b;
        int sum;
        public A(int a, int b, int sum){
            this.a = a;
            this.b = b;
            this.sum = sum;
        }
    }
}
