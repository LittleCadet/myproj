package com.myproj.app.algorithm.二分查找;

/**
 * 题目：
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 思路：
 *      1. 两次二分查找：
 *
 * @author shenxie
 * @date 2023/12/30
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
        searchRange(new int[]{1,4}, 4);
    }

    /**
     * 两次二分查找的原因： 当nums[mid] == target时， 不确定下一个相同的元素 在mid的左边 / 右边。
     * 所以： 当nums[mid] == target时， r = mid - 1 || l = mid + 1;
     */
    public static int[] searchRange(int[] nums, int target) {
        int l = 0, r = nums.length - 1, mid = 0, index1 = -1, index2 = -1;
        // 第一次二分查找： 设置index1;
        while(l <= r) {
            mid = l + (r - l) / 2;
            if(nums[mid] < target) {
                l = mid + 1;
            }else if (nums[mid] == target) {
                index1 = mid;
                r = mid -1;
            }else{
                r = mid - 1;
            }
        }

        // 第二次二分查找： 设置index2;
        l =0; r = nums.length - 1;
        while(l <= r) {
            mid = l + (r - l) / 2;
            if(nums[mid] < target) {
                l = mid + 1;
            }else if (nums[mid] == target) {
                index2 = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return new int[]{index1, index2};
    }
}
