package com.myproj.app.algorithm.动态规划;

/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度均为 n 。
 * 让我们定义另一个下标从 0 开始、长度为 n 的整数数组，nums3 。对于范围 [0, n - 1] 的每个下标 i ，你可以将 nums1[i] 或 nums2[i] 的值赋给 nums3[i] 。
 * 你的任务是使用最优策略为 nums3 赋值，以最大化 nums3 中 最长非递减子数组 的长度。
 * 以整数形式表示并返回 nums3 中 最长非递减 子数组的长度。
 * 注意：子数组 是数组中的一个连续非空元素序列。
 *
 * 示例 1：
 * 输入：nums1 = [2,3,1], nums2 = [1,2,1]
 * 输出：2
 * 解释：构造 nums3 的方法之一是：
 * nums3 = [nums1[0], nums2[1], nums2[2]] => [2,2,1]
 * 从下标 0 开始到下标 1 结束，形成了一个长度为 2 的非递减子数组 [2,2] 。
 * 可以证明 2 是可达到的最大长度。
 *
 * 示例 2：
 * 输入：nums1 = [1,3,2,1], nums2 = [2,2,3,4]
 * 输出：4
 * 解释：构造 nums3 的方法之一是：
 * nums3 = [nums1[0], nums2[1], nums2[2], nums2[3]] => [1,2,3,4]
 * 整个数组形成了一个长度为 4 的非递减子数组，并且是可达到的最大长度。
 *
 * 示例 3：
 * 输入：nums1 = [1,1], nums2 = [2,2]
 * 输出：2
 * 解释：构造 nums3 的方法之一是：
 * nums3 = [nums1[0], nums1[1]] => [1,1]
 * 整个数组形成了一个长度为 2 的非递减子数组，并且是可达到的最大长度。
 *
 *
 *
 *      思路：
 *          - 动态规划：最优子结构：
 *              dp1[i] = Math.max(dp1[i], dp1[i-1] + 1);
 *
 * @author shenxie
 * @date 2026/1/14
 */
public class 构造最长非递减子数组 {

    public static void main(String[] args) {
        System.out.println(maxNonDecreasingLength(new int[]{2,3,1}, new int[]{1,2,1}));
    }

    public static int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        if (n == 0) {
            return 0;
        }

        // dp1[i] 表示以nums1[i]作为nums3[i]时，最长非递减子数组的长度（以i结尾）
        // dp2[i] 表示以nums2[i]作为nums3[i]时，最长非递减子数组的长度（以i结尾）
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        // 初始化：第一个位置，选nums1[0]或nums2[0]，长度都是1
        dp1[0] = 1;
        dp2[0] = 1;

        // 记录最大长度，初始为1
        int maxLen = 1;

        // 从第二个元素开始遍历
        for (int i = 1; i < n; i++) {
            // 初始化当前位置的dp1和dp2为1（默认只包含自身）
            dp1[i] = 1;
            dp2[i] = 1;

            // 情况1：当前选nums1[i]，判断与前一个位置的两种选择是否满足非递减
            // 前一个位置选nums1[i-1]
            if (nums1[i] >= nums1[i-1]) {
                dp1[i] = Math.max(dp1[i], dp1[i-1] + 1);
            }
            // 前一个位置选nums2[i-1]
            if (nums1[i] >= nums2[i-1]) {
                dp1[i] = Math.max(dp1[i], dp2[i-1] + 1);
            }

            // 情况2：当前选nums2[i]，判断与前一个位置的两种选择是否满足非递减
            // 前一个位置选nums1[i-1]
            if (nums2[i] >= nums1[i-1]) {
                dp2[i] = Math.max(dp2[i], dp1[i-1] + 1);
            }
            // 前一个位置选nums2[i-1]
            if (nums2[i] >= nums2[i-1]) {
                dp2[i] = Math.max(dp2[i], dp2[i-1] + 1);
            }

            // 更新最大长度
            maxLen = Math.max(maxLen, Math.max(dp1[i], dp2[i]));
        }

        return maxLen;
    }

}
