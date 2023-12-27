package com.myproj.app.algorithm.双指针;

/**
 * 题目：
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * 然后返回 nums 中唯一元素的个数。考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 *     更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 *     返回 k 。
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 思路：
 * 1. 快慢指针。
 *
 * @author shenxie
 * @date 2023/12/9
 */
public class 删除有序数组中的重复项 {

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }

    public static int removeDuplicates(int[] nums) {
        // 快指针从1开始的原因： 与慢指针从0开始刚好可以完成对比。
        int fast = 1;
        int slow = 0;
        int length = nums.length;
        while(fast < length ) {

            if( nums[slow] != nums[fast] ) {
                // 要保持相对顺序， 在num[slow] = nums[fast]时， 不移动元素。
                // 所以在num[slow] != nums[fast]时， 需要吧fast元素替换为slow +1上。
                // 如果替换到slow上， 相对顺序会改变。
                nums[slow + 1] = nums[fast];
                slow++;
            }

            fast++;
        }
        return slow + 1;
    }
}
