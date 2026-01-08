package com.myproj.app.algorithm.二分查找;

/**
 * 题目：
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 *     若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *     若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *
 * 思路：
 *      1. 方法1： 找到旋转次数k。 那么k对应的位置， 就是最小值。
 *      2. 方法2： 二分法：
 * @author shenxie
 * @date 2023/12/30
 */
public class 寻找旋转排序数组中的最小值 {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
        System.out.println(findMinV2Copy(new int[]{3,4,5,1,2}));
    }

    public static int findMin(int[] nums) {
        int k = 0;
        // 找到旋转次数
        for(int i = 1; i<nums.length; i++) {
            if(nums[i-1] > nums[i]){
                k = i;
            }
        }
        return nums[k];
    }

    /**
     *  只有一个波峰 波谷的情况 也可以用二分法。
     *  二分法： 至少三个元素： l + r + mid + target: 如果target没有， 就用mid替换target.
     *          三个元素 与 四个元素 的二分法： 有些许不同： 画图就好。
     */
    public static int findMinV2Copy(int[] nums) {
        int l = 0, r = nums.length -1, mid = 0;
        while(l < r) {
            mid = l + (r - l) / 2;
            // 如果nums[mid] < nums[r]时， 则说明在[l,mid]的区间内，存在最小值， 所以 r = mid
            // 用 mid 与 r 比较大小， 而不用 mid 与 l 的原因：
            // - 现求最小值：用 mid 与 r 比较： 是缩小 r 【最大值】的范围。
            // - 如果求最大值， 则 用 mid 与 l比较： 是缩小 l 【最小值】的范围
            if(nums[mid] < nums[r]){
                r = mid ;
            }else{
                // 如果nums[mid] > nums[r]时，则说明在[mid , r]的区间内， 存在最小值。所以 l = mid + 1;
                // 因为是旋转排序数组， 只有一个波峰和一个波谷
                l = mid + 1;
            }
        }
        // 返回 num[l] 或者 nums[r] 都行。 但是nums[mid]不行。
        return nums[l];
    }
}
