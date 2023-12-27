package com.myproj.app.algorithm.双指针;

/**
 * 题目：
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * 思路：
 *  方法1： 快慢双指针：
 *      核心思想： 类似于双重for循环。
 *  方法2： 首尾双指针：
 *      核心思想： 类似于二分法。
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 两数之和II输入有序数组 {

    public static void main(String[] args) {
        twoSum(new int[]{5,25,75}, 100);
    }

    /**
     * 方法一： 快慢双指针: 类似于双重for循环。
     * 评价： 速度比方法二慢。
     */
    public static int[] twoSum(int[] numbers, int target) {
        int slow = 0;
        int fast = 1;
        int sum = 0;
        int[] ans = new int[2];
        int n = numbers.length;
        while(slow < n ) {
            sum = numbers[slow] + numbers[fast];
            if(target == sum){
                ans[0] = slow + 1;
                ans[1] = fast + 1;
                break;
            }

            // 当fast = n 时， 代表： 以slow为始点的循环结束， 需要开始下一轮循环： 即为slow ++, 且 fast = slow + 1
            if(++fast == n) {
                slow ++;
                fast = slow + 1;
            }
        }
        return ans;
    }

    /**
     * 方法二： 首尾双指针： 类似于： 二分法：
     *
     */
    public static int[] twoSumV2(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0, r = n - 1;
        int sum = 0;
        int[] ans = new int[2];

        while(l < r ) {
            sum = numbers[l] + numbers[r];
            if(target == sum){
                ans[0] = l + 1;
                ans[1] = r + 1;
                break;
            }

            if(target > sum) {
                l++;
            }else{
                r--;
            }
        }
        return ans;
    }
}
