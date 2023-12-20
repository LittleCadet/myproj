package com.myproj.app.algorithm.数组;

/**
 * @author shenxie
 * @date 2023/12/17
 */
public class 冒泡排序 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 3, 5, 6};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }

}
