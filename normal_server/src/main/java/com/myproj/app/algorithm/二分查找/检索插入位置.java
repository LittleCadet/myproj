package com.myproj.app.algorithm.二分查找;

import com.myproj.app.algorithm.二叉树.二叉搜索树.二叉搜索树中的插入操作;
import com.myproj.app.algorithm.区间.插入区间;
import com.myproj.app.algorithm.链表.对链表进行插入排序;

/**
 * 题目：
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 思路：
 * 1. 方法1 ： 正常循环求解。
 * 2. 方法2 ： 二分法求解：
 *              核心： 返回left, 而不是mid.
 *
 *  *              - {@link 对链表进行插入排序}： 链表的节点插入： 变为有序链表
 *  *              - {@link 检索插入位置}： 将target 插入 二叉搜索树中， 返回 插入位置。
 *  *              - {@link 插入区间}: 区间插入： 变为 有序区间
 *  *              - {@link 二叉搜索树中的插入操作}： 将target 插入二叉搜索树中， 并形成节点
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 检索插入位置 {

    public static void main(String[] args) {
//        System.out.println(searchInsertV1Copy(new int[]{1,3,5,6}, 4));
        System.out.println(searchInsertV2Copy(new int[]{1,3,5,6}, 3));
    }

    /**
     * 常规循环。
     */
    public static int searchInsertV1Copy(int[] nums, int target) {
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
    public static int searchInsertV2Copy(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1 ;
        int mid =0;
        while(left <= right) {
            mid = (right - left) / 2 + left;
            if(target > nums[mid]) {
                left = mid + 1;
            }else if(target < nums[mid]){
                right = mid - 1;
            }else{
                // 找到了， 直接return
                return mid;
            }
        }
        // 未找到，就返回left, 原因：插入的位置要靠左。
        // 1. left = mid + 1,  在mid前面。  而 right = mid - 1, 在mid后面。
        return left;
    }
}
