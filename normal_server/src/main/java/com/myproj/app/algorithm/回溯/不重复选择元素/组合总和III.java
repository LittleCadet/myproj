package com.myproj.app.algorithm.回溯.不重复选择元素;

import com.myproj.app.algorithm.回溯.重复选择元素.组合总和;
import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 *     只使用数字1到9
 *     每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 *
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 *
 * 示例 3:
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 *
 *      思路：
 *          - 回溯：
 *  *              - 与{@link 组合总和}很类似：不同的是：
 *  *                  - {@link 组合总和}： 元素可以重复使用；
 *  *                  - {@link 组合总和II}： 要求： 元素不能重复使用， 且 解集不能包含重复的组合
 *  *                  - {@link 组合总和III}: 元素不可重复使用， 且 是求和问题
 *
 * @author shenxie
 * @date 2025/11/19
 */
public class 组合总和III {

    public static void main(String[] args) {
        System.out.println(combinationSum3(3,7));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(k, n, results, new ArrayList<>(), 1, 0);
        return results;
    }

    private static void dfs(int k, int n, List<List<Integer>> results, List<Integer> result, int index, int sum) {
        if(k == result.size()) {
            if(n == sum) {
                results.add(new ArrayList<>(result));
            }
            return;
        }

        // 从index开始， 因为题目要求不重复。
        // 1<= i<9, 因为题意要求：只能使用数字 1-9
        for(int i = index ; i<=9; i++) {
            // 剪枝：因为 根据题意：该for循环具备单调性【单调递增】， 所以 sum + i > n, 就不用执行了。
            if(sum + i > n) {
                return;
            }
            result.add(i);
            // 此处必定用 i + 1， 而不是 index + 1: 原因：
            // - index + 1: 代表： i 和 index的增长速度不一致， 会导致 i 比 index大， 从而导致 nums[0] 比 nums[1]大， 这是不符合题意的【看 例子】。
            // - i + 1: 代表： i 和 index的增长速度一致， 即为 nums[1] > nums[0] 永远成立。
            dfs(k, n, results, result, i+1, sum + i);
            result.remove(result.size() - 1);
        }
    }
}
