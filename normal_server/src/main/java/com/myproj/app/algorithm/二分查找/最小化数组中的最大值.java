package com.myproj.app.algorithm.二分查找;

import com.myproj.app.algorithm.动态规划.打家劫舍IV;

/**
 * 给你一个下标从 0 开始的数组 nums ，它含有 n 个非负整数。
 * 每一步操作中，你需要：
 *     选择一个满足 1 <= i < n 的整数 i ，且 nums[i] > 0 。
 *     将 nums[i] 减 1 。
 *     将 nums[i - 1] 加 1 。
 * 你可以对数组执行 任意 次上述操作，请你返回可以得到的 nums 数组中 最大值 最小 为多少。
 *
 * 示例 1：
 * 输入：nums = [3,7,1,6]
 * 输出：5
 * 解释：
 * 一串最优操作是：
 * 1. 选择 i = 1 ，nums 变为 [4,6,1,6] 。
 * 2. 选择 i = 3 ，nums 变为 [4,6,2,5] 。
 * 3. 选择 i = 1 ，nums 变为 [5,5,2,5] 。
 * nums 中最大值为 5 。无法得到比 5 更小的最大值。
 * 所以我们返回 5 。
 *
 * 示例 2：
 * 输入：nums = [10,1]
 * 输出：10
 * 解释：
 * 最优解是不改动 nums ，10 是最大值，所以返回 10 。
 *
 *
 *        思路：
 *          - 二分开区间：「最小化最大值」就是二分答案的代名词
 *              - 我们猜测一个上界 limit，即要求操作后所有元素均不超过 limit。由于 limit 越大越能够满足，越小越无法满足，可以二分猜答案
 *          - 与 {@link 打家劫舍IV}很类似：都是最小化 最大值的问题。
 *
 * @author shenxie
 * @date 2025/11/18
 */
public class 最小化数组中的最大值 {

    public static void main(String[] args) {
        System.out.println(minimizeArrayValue(new int[]{10,1}));
    }

    public static int minimizeArrayValue(int[] nums) {
        int left = -1;
        int right = 0;
        // 找到最大值
        for (int x : nums) {
            right = Math.max(right, x);
        }
        // 开区间二分，原理见 https://www.bilibili.com/video/BV1AP41137w7/
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // 即为 最小的最大值
        return right;
    }

    /**
     * 设nums = [2,3,7,1,6]
     * 由对前三个数进行操作，则我们可以得到的最小最大值为4
     * 怎么做到的捏？我们来一步步走
     * [2,3,7]
     * [3,2,7]
     * [4,1,7]
     * [4,2,6]
     * [4,3,5]
     * [4,4,4]
     * 一步步下来，我们发现，前方的较小的2和3承接了来自后方的7中的数，最终使得整个数组都整体变小了
     * 2承载了最终答案4中的，来自于7中的两个1
     * 3承载了最终答案4中的，来自于7中的一个1
     */
    private static boolean check(int[] nums, int limit) {
        // extra的意义在于：nums[i] 与 limit多出来的部分 即为 extra， 会分摊给所有的比nums[i]小的数上, 从而使得整个数组都整体变小了
        // 从而符合题意：nums[i] - 1， 而 num[i-1] + 1。
        long extra = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            // 把该题想象成一个积木堆， 把积木从右往左移动，可以多次移动，即为：从nums[i] 移动到 nums[i-1], 再移动到 nums[i-2].
            // 如果 nums[i]>limit，那么应当去掉多余的 extra=nums[i]−limit 加到 nums[i−1] 上，最后如果 nums[0]≤limit，则二分判定成功。
            extra = Math.max(nums[i] + extra - limit, 0);
        }
        // 让所有元素：都不超过 limit
        // limit的二分判定成功：说明：这是其中的一个最大值， 还要继续执行二分， 才能找到最小的最大值。
        return nums[0] + extra <= limit;
    }
}
