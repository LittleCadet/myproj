package com.myproj.app.algorithm.回溯.不重复选择元素;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 *      思路：
 *          - 回溯 + 排序
 *              此题 与 {@link 全排列}非常类似， 不同的是：
 *              - 本题要求：返回不重复的全排列，所以：判定条件为：vis[i] || i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]
 *                  - vis[i]: 解决相同位置的元素不重复选择。
 *                  - i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]： 解决：相邻位置但值相等 的 不重复选择。
 *              - 而{@link 全排列}因为 nums数组不包含重复元素，所以全排列的不重复元素判定：只需要booean[] selected 即可
 *
 * @author shenxie
 * @date 2025/11/10
 */
public class 全排列II {

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1,1,2}));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm, new boolean[nums.length]);
        return ans;
    }

    public static void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm, boolean[] vis) {
        if (perm.size() == nums.length) {
            ans.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            // 对于 !vis[i-1]的原因：
            // 需要将两个相邻的重复数字 去重：eg: 11, 假设 1a代表第一个1， 1b代表第二个1， 所以排列有1a1b 或者 1b1a。 但题意是只保留 1a1b。
            // 解决办法： 在前后数字相同时：只有处理完nums[i-1] 时，再去访问nums[i]即可。 即为先有vis[i-1]为true时， 再处理vis[i]。
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm, vis);
            vis[i] = false;
            perm.remove(perm.size() -1);
        }
    }
}
