package com.myproj.app.algorithm.二分查找;

/**
 * 题目：
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 思路：
 * 1. 方法1 ： 正常循环求解。
 * 2. 方法2 ： 二分法求解：
 *              核心： 返回left, 而不是mid.
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 检索插入位置 {

    public static void main(String[] args) {
//        System.out.println(searchInsertV1(new int[]{1,3,5,6}, 4));
        System.out.println(searchInsertV2(new int[]{1,3,5,6}, 2));
    }

    /**
     * 常规循环。
     */
    public static int searchInsertV1(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 二分查找
     */
    public static int searchInsertV2(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1 ;
        int mid =0;
        while(left <= right) {
            mid = (left + right) / 2 + left;
            if(target > nums[mid]) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        // 返回left的原因：
        // 1. 不用right的原因： target > nums[mid]时， 插入元素才符合要求，
        // 2. 不同mid的原因： 如果数组中只有2个元素 且自增, 且 target比第一个元素大时，mid = (0 + 1) / 2 = 0， 如果此时插在0的位置， 则不符合要求。
        return left;
    }
}
