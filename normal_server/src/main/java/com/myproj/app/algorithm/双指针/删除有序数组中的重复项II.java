package com.myproj.app.algorithm.双指针;

/**
 * 题目：
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 示例 1：
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
 *
 * 思路：
 *      快慢双指针：难点：
 *          1. 慢指针的移动时机： 问题拆解为：
 *             1.1 慢指针移动时： 必定是在元素替换以后， 即为nums[slow] = nums[fast];
 *             1.2 元素替换的时机： 必定是在慢指针的前两个元素与快指针不同时： 即为nums[slow - 2] != nums[fast];
 *          2. 保证元素出现次数 <= 2次的方法： nums[slow - 2] != nums[fast];
 * @author shenxie
 * @date 2023/12/26
 */
public class 删除有序数组中的重复项II {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3}));
    }

    public static int removeDuplicates(int[] nums) {
        int slow = 2;
        int fast = 2;
        if(nums.length < 2) {
            return nums.length;
        }

        while(fast < nums.length) {
            // 元素替换的时机： 必定是nums[slow - 2] != nums[fast].
            if(nums[slow -2] != nums[fast]) {
                // slow指针移动的场景： 必定是元素替换以后。
                nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow;
    }
}
