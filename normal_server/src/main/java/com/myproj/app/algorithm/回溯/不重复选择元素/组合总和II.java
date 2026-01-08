package com.myproj.app.algorithm.回溯.不重复选择元素;

import com.myproj.app.algorithm.回溯.重复选择元素.组合总和;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 *      思路：
 *          - 回溯：
 *              - 与{@link 组合总和}很类似：不同的是：
 *                  - {@link 组合总和}： 元素可以重复使用；
 *                  - {@link 组合总和II}： 要求： 元素不能重复使用， 且 解集不能包含重复的组合
 *                  - {@link 组合总和III}: 元素不可重复使用， 且 是求和问题
 *              - 与{@link 全排列II}很类似：在相邻元素 且 值相等时， 不能重复使用：
 *                  - visit[i] || i>0 && candidates[i] == candidates[i-1] && ! visit[i-1]
 *              - 与{@link 子集II}很类似：在相邻元素 且 值相等时， 不能重复使用：
 *                  - i>index && nums[i-1] == nums[i]
 *
 * @author shenxie
 * @date 2025/11/11
 */
public class 组合总和II {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        // 必须排序， 不然"相邻的相同元素不能重复使用"的语义：无法完成
        Arrays.sort(candidates);
        dfs(candidates, target, 0, new ArrayList(), results, 0, new boolean[candidates.length]);
        return results;
    }

    private void dfs(int[] candidates, int target , int index, List<Integer> result, List<List<Integer>> results, int sum, boolean[] visit) {
        if(sum == target) {
            results.add(new ArrayList<>(result));
            return;
        }
        if(sum > target) {
            return;
        }

        // 注意： i 起始位置是 index: 保证：同一个元素不重复使用
        for(int i = index ; i<candidates.length; i++) {
            // 保证：相邻的相同元素不能重复使用
            // 这里与使用《全排列II》的判定方式相同： if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]))
            if(visit[i] || i>0 && candidates[i] == candidates[i-1] && ! visit[i-1]){
                continue;
            }

            visit[i] = true;
            result.add(candidates[i]);
            // 此处必定用 i + 1， 而不是 index + 1: 原因：
            // - index + 1: 代表： i 和 index的增长速度不一致， 会导致 i 比 index大， 从而导致 nums[0] 比 nums[1]大， 这是不符合题意的【看 例子】。
            // - i + 1: 代表： i 和 index的增长速度一致， 即为 nums[1] > nums[0] 永远成立。
            dfs(candidates, target, i + 1, result, results, sum+candidates[i], visit);
            result.remove(result.size() - 1);
            visit[i] = false;
        }
    }
}
