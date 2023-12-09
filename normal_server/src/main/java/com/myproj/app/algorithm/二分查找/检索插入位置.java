package com.myproj.app.algorithm.二分查找;

/**
 * 题目：
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 检索插入位置 {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 4));
    }
    public static int searchInsert(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
