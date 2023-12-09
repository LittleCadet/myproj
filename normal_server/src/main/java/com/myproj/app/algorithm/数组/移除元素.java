package com.myproj.app.algorithm.数组;

/**
 * 题目：
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 分析：
 * 前半段是有效部分，存储的是不等于 val 的元素。
 * 后半段是无效部分，存储的是等于 val 的元素。
 *
 * 思路：
 * 1. 使用快慢指针的思想即可解决问题。
 * 2. 快指针在外侧， 慢指针在内侧， 当快指针对应的值 != val时， 将快指针的复制给慢指针。 从而达到“与目标值不同时， 元素往前移动的效果”。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 移除元素 {

    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow = 0;
        int length = nums.length;
        while(fast < length) {
            if(nums[fast] != val) {
                nums[slow] = nums[fast];
                slow ++;
            }

            fast++;
        }
        return slow;
    }
}
