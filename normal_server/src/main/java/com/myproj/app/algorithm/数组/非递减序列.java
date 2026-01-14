package com.myproj.app.algorithm.数组;

/**
 * 给你一个长度为 n 的整数数组 nums ，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
 *
 * 示例 2:
 *
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *
 *      思路：
 *          - pre , cur, next的三个元素的大小比较： 是建立在 cur > next时， 才有意义。
 *
 * @author shenxie
 * @date 2026/1/14
 */
public class 非递减序列 {

    public static void main(String[] args) {
        System.out.println(checkPossibility(new int[]{4,2,3}));
    }

    /**
     * 本质是： pre , cur, next的三个元素的大小比较： 是建立在 cur > next时， 才有意义。
     */
    public static boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int cur = nums[i], next = nums[i + 1];
            // 在cur > next时：对count累加, 超过1次， 即为不满足要求
            if (cur > next) {
                if (++count > 1) {
                    return false;
                }

                // 在cur > next 且 pre > next时,  即为： pre <= cur  且 pre > next时： 此时只需要将next 置为 cur， 即可满足题意： “非递减序列”
                if (i > 0 && nums[i - 1] > next) {
                    nums[i+1] = cur;
                }
            }

        }

        return true;
    }

}
