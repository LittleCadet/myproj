package com.myproj.app.algorithm.二分查找;

/**
 * 题目：
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 *
 * 思路：
 * 1. 用数组中原有的值 比较大小即可，
 *      而不是使用一个临时变量来比较大小，因为： 不管临时变量有多小， 肯定会有比他更小的值， 那时：难以处理。
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 寻找峰值 {

    public static void main(String[] args) {
//        System.out.println(findPeakElement(new int[]{1,2,3,1}));
        System.out.println(findPeakElementV2Copy(new int[]{-2147483648}));
//        System.out.println(findPeakElementV2Copy(new int[]{-2147483648,-2147483647}));
    }

    /**
     * 方法1： 逐个比较
     */
    public static int findPeakElementCopy(int[] nums) {
        // 此题不能用一个临时变量来比较大小： eg: int tmp = -Integer.MAX_VALUE; 因为：如果有值比tmp更小的时候， 难以处理。
        // 应该用数组中原有的值 来比较大小。
        int index = 0;
        for(int i = 0; i< nums.length; i++) {
            if(nums[i] > nums[index]) {
                index = i;
            }
        }
        return index;
    }

    /**
     * 方法2： 二分法：
     */
    public static int findPeakElementV2Copy(int[] nums) {
        int l = 0 , r= nums.length-1, mid = 0 ;
        // 如果 l <= r时， 在数组只有一个元素时， 会出现数组下标越界：nums[mid + 1]
        while(l < r) {
            mid = l + (r-l) / 2;
            // 使用 nums[mid] > nums[mid + 1] 的原因： 不知道 波峰在mid的左边还是右边， 所以当做 爬山【不恰当的比喻：贪心算法】。
            if(nums[mid] > nums[mid + 1]) {
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        // 返回 l , r 都行
        return r;
    }
}
