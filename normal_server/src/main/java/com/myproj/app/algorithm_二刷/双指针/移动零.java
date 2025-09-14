package com.myproj.app.algorithm_二刷.双指针;

import java.util.Arrays;
import java.util.Collections;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 思路：
 *      1. 数组的快慢双指针法。
 *          - 在快指针 不为0时， 将值与慢指针的值 替换即可。
 *      2. 重点：移动0时， 同时保持非0 元素的相对顺序。
 *          - 所以两个指针 一定是同向移动， 而不是相向而行。【如果相向而行， 则非0元素的顺序刚好是反的】
 *
 *
 * @author shenxie
 **/
public class 移动零 {

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroesCopy(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void moveZeroesCopy(int[] nums) {
        // left指针： 指向数组中现在元素的最初的0的位置；这样就可以吧：非0数字 移动到现在元素的最初0的位置了。
        int left = 0 ;
        for(int right = 0 ; right < nums.length; right ++) {
            if(nums[right] != 0 ) {
                // 把右边的非0值， 移动到左边
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;

                // 左指针 往右移动。
                left ++;
            }
        }
    }

    public static void moveZeroesCopyV2(int[] nums) {
        for(int i = 1 ; i< nums.length; i++) {
            if(nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = tmp;
            }
        }
    }
}
