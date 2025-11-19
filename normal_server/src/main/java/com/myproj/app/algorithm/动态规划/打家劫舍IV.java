package com.myproj.app.algorithm.动态规划;

import com.myproj.app.algorithm.二分查找.最小化数组中的最大值;

/**
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
 * 返回小偷的 最小 窃取能力。
 *
 * 示例 1：
 * 输入：nums = [2,3,5,9], k = 2
 * 输出：5
 * 解释：
 * 小偷窃取至少 2 间房屋，共有 3 种方式：
 * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
 * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
 * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
 * 因此，返回 min(5, 9, 9) = 5 。
 *
 * 示例 2：
 * 输入：nums = [2,7,9,3,1], k = 2
 * 输出：2
 * 解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
 *
 *      思路：
 *          - 二分法：
 *              此题 与 {@link 打家劫舍} + {@link 打家劫舍II} + {@link 打家劫舍III} 都不同：原因：
 *                  - 他们看重的是 窃取总金额的最大值。
 *                  - {@link 打家劫舍IV} 看重的是：最小的窃取能力 【窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 】
 *
 *          - 与 {@link 最小化数组中的最大值}很类似：都是最小化 最大值的问题。
 *
 * @author shenxie
 * @date 2025/11/18
 */
public class 打家劫舍IV {

    public static void main(String[] args) {
        System.out.println(minCapability(new int[]{2,3,5,9}, 2));
    }

    /**
     * 二分法： 选择二分法的原因：最小化最大值问题，都可以用二分法来解决。
     *
     * 因为二分法：二分的值越小，越不能/能满足要求；二分的值越大，越能/不能满足要求。有单调性的保证，就可以二分答案了
     */
    public static int minCapability(int[] nums, int k) {
        int lower = 0;
        int upper = 0;
        for(int i = 0 ; i<nums.length ;i++) {
            lower = Math.min(nums[i], lower);
            upper = Math.max(nums[i], upper);
        }

        // 模拟所有间隔抢劫的可能性
        while (lower <= upper) {
            int middle = lower + (upper - lower) / 2;
            int count = 0;
            // 用visited 控制：间隔抢， 而不是连续抢
            boolean visited = false;
            for (int x : nums) {
                if (x <= middle && !visited) {
                    count++;
                    visited = true;
                } else {
                    visited = false;
                }
            }

            // 只有 抢劫次数 >= k 才符合要求， 但不一定就是最小的窃取能力， 所以 upper = middle - 1， 再次循环。
            if (count >= k) {
                upper = middle - 1;
            } else {
                // 抢劫次数不达标， 不符合要求，所以 lower = middle + 1, 再来一轮。
                lower = middle + 1;
            }
        }

        // 返回lower的原因：题意： 要求返回最小的窃取能力。
        return lower;
    }
}
