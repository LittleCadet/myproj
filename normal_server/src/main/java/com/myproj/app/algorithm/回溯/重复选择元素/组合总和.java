package com.myproj.app.algorithm.回溯.重复选择元素;

import com.myproj.app.algorithm.回溯.不重复选择元素.全排列;
import com.myproj.app.algorithm.回溯.不重复选择元素.组合;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 *      思路：
 *          回溯：
 *              - 重复选择元素： 与 {@link 全排列} 有异曲同工之妙。
     *              - 该题：是不主动改变i的值， 则 数值 可以做到重复选择。
     *              - {@link 全排列} 也没有主动改变 下标， 而是通过for循环的方式完成， 所以 数值可以重复选择
 *              - 与 {@link 组合} 类似的是：{@link 组合}是主动变更index的方式【index + 1】，做到不重复选择元素。
 *
 *
 * @author shenxie
 * @date 2025/9/19
 */
public class 组合总和 {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result=  new ArrayList<>();
        process(result, candidates, target, 0, new ArrayList(), 0);
        return result;

    }

    private static void process(List<List<Integer>> result, int[] candidates, int target, int sum , List<Integer> tmp, int index){
        if(sum >= target) {
            if(sum == target) {
                result.add(new ArrayList(tmp));
            }
            return;
        }

        // 注意： i 起始位置是 index: 保证：同一个元素不重复使用
        // i 从0 开始： 那是全排列。{@link 全排列} + {@link 全排列II}
        for(int i = index ; i<candidates.length; i++) {
            tmp.add(candidates[i]);
            // 这里 不主动改变i的值， 则 数值 可以做到重复选择。
            process(result, candidates, target, sum+candidates[i], tmp, i);
            tmp.remove(tmp.size() - 1);
        }
    }
}
