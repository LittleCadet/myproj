package com.myproj.app.algorithm.回溯.不重复选择元素;

import com.myproj.app.algorithm.回溯.重复选择元素.组合总和;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 题目：
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 思路：
 * 1. 回溯算法：对回溯的理解： 回溯 与 深度优先算法： 有异曲同工之妙， 都属于 穷尽所有场景， 尽可能的找出答案。
 *                  其中回溯： 在形成一遍检索以后， 需要恢复之前的状态，之后再执行第二遍检索。
 *      核心问题：
 *          1.1 如何控制给定数组元素的循环
 *              答案： for循环数组元素， 但是起始下标为 i=index.
 *          1.2 如何维护生成的新数组。
 *              答案： 替换元素： 使用Collections.swap(list, index, i);; 在list中将index和i位置的元素互换。
 *          1.3 如何撤销操作。
 *              答案： 把元素换回来即可 : Collections.swap(list, i, index);
 *          1.4 何时执行回溯操作。
 *              答案： 在1.2 - 1.3之间
 *          1.5 当前回溯完成的标志是什么 ？
 *              答案： 下标 = 给定数组的长度时， 当前回溯完成
 *
 *         1.6  关于方法2【Collections.swap】：注意：
 *              a. 要注意： 完成标志！！！
 *              b. 要注意： 如何放入lists中！！！
 *              c. 要注意： i的起始位置！！！
 *
 *         解法1【推荐： 通用】： 回溯 + 剪枝：用list完成： list.add() + list.remove();
 *              此题 与 {@link 全排列II}非常类似， 不同的是：
 *              - {@link 全排列II}要求：返回不重复的全排列，所以：判定条件为：vis[i] || i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]
 *                  - vis[i]: 解决相同位置的元素不重复选择。
 *                  - i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]： 解决：相邻位置但值相等 的 不重复选择。
 *              - 而本题因为 nums数组不包含重复元素，所以全排列的不重复元素判定：只需要booean[] selected 即可
 *
 *
 *              - 重复选择元素： 与 {@link 组合总和} 有异曲同工之妙。
 *                  - {@link 组合总和}：是不主动改变i的值， 则 数值 可以做到重复选择。
 *                  - {@link 全排列} 也没有主动改变 下标， 而是通过for循环的方式完成， 所以 数值可以重复选择
 *              - 不重复选择元素：
 *                  - 与 {@link 组合} / {@link 单词搜索} / {@link 子集} / {@link 括号生成} / {@link 电话号码的字母组合}类似：
 *                      - 是主动变更index的方式【index + 1】，做到不重复选择元素。
 *         解法2： 回溯： 用list完成：Collections.swap(list, index, i) +  Collections.swap(list, i, index);
 *         解法3： 同解法1： 好懂：“不重复”的语义：依赖 if(! list.contains(key)) 而不是 vis[i]。
 *
 * @author shenxie
 * @date 2023/12/7
 */
public class 全排列 {

    /**
     * 给定数组的元素，在数字不重复的情况下，  输出所有可能的排序。
     */
    public static void main(String[] args) {
        // 方法1：
//        System.out.println(permutationsICopy(new int[]{1,3,2}));
//        List<Integer> list=  Lists.newArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        Collections.swap(list, 0, 4);
//        System.out.println(list);

        // 方法2：
        System.out.println(permutationsICopy(new int[]{1,2,3}));
    }


    /**
     * 解法一：回溯 + 剪枝： 用list完成： list.add() + list.remove();
     *
     */
    public static List<List<Integer>> permutationsICopy(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
        return res;
    }
    /**
     * 解法三：回溯 + 剪枝： 用list完成： list.add() + list.remove();
     * 整体同解法一： 唯一的不同是：不重复使用元素：依赖 if( ! list.contains(key))， 而没用 visit的boolean数组
     *
     */
    public static List<List<Integer>> permutationsICopyV2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        backtrackV2(new ArrayList<Integer>(), nums,  res);
        return res;
    }

    /**
     * 解法二：回溯： 用list完成：Collections.swap(list, index, i) +  Collections.swap(list, i, index)
     */
    public static List<List<Integer>> permuteCopy(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }
        dfs(lists, nums, 0, output);
        return lists;
    }

    /**
     * 如果不通过 selected 的剪枝：则输出结果如下： 全是元素的重复使用：
     * [
     *  [1, 1, 1], [1, 1, 2], [1, 1, 3], [1, 2, 1], [1, 2, 2], [1, 2, 3], [1, 3, 1], [1, 3, 2], [1, 3, 3],
     *  [2, 1, 1], [2, 1, 2], [2, 1, 3], [2, 2, 1], [2, 2, 2], [2, 2, 3], [2, 3, 1], [2, 3, 2], [2, 3, 3],
     *  [3, 1, 1], [3, 1, 2], [3, 1, 3], [3, 2, 1], [3, 2, 2], [3, 2, 3], [3, 3, 1], [3, 3, 2], [3, 3, 3]
     * ]
     *
     * @param state 直到目前为止， 已被选择的元素
     * @param choices 数组元素
     * @param selected 数组：代表：是否已经使用
     * @param res 所有可能的排序结果
     */
    /* 回溯算法：全排列 I */
    public static void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        // 当状态长度等于元素数量时，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<Integer>(state));
            return;
        }
        // 遍历所有选择
        // i从0 开始： 只有全排列是 这样， 其他都是从 index开始
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            // 剪枝：不允许重复选择元素
            if (!selected[i]) {
                // 尝试：做出选择，更新状态
                selected[i] = true;
                state.add(choice);
                // 进行下一轮选择
                backtrack(state, choices, selected, res);
                // 回退：撤销选择，恢复到之前的状态
                selected[i] = false;
                state.remove(state.size() - 1);
            }
        }
    }
    public static void backtrackV2(List<Integer> state, int[] choices, List<List<Integer>> res) {
        // 当状态长度等于元素数量时，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<Integer>(state));
            return;
        }
        // 遍历所有选择
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            // 剪枝：不允许重复选择元素
            if (!state.contains(choice)) {
                // 尝试：做出选择，更新状态
                state.add(choice);
                // 进行下一轮选择
                backtrackV2(state, choices,  res);
                // 回退：撤销选择，恢复到之前的状态
                state.remove(state.size() - 1);
            }
        }
    }


    /**
     *
     * @param lists 输出的集合
     * @param nums 输入的数组
     * @param index 起始下标
     * @param list 子集合
     */
    public static void dfs(List<List<Integer>> lists, int[] nums, int index, List<Integer> list){
        // 一次组合完成的标志
        // 要注意： 完成标志！！！
        if(index == nums.length) {
            // 需要new ArrayList(), 而不是直接使用list的原因： list在堆中一直都是同一个地址， 即为变更N次， 都是最后一次都数据。
            System.out.println("结束："+ list);
            // 要注意： 如何放入lists中！！！
            lists.add(new ArrayList<>(list));
        }else{
            // 如何控制给定数组元素的循环。
            // 要注意： i的起始位置！！！
            for(int i = index; i< nums.length; i++) {
                // 维护操作：将index 和 i的位置互换
                Collections.swap(list, index, i);
                System.out.println("swap前：index:" + index + ", i:" + i + ",list:"+ list);
                // 回溯
                dfs(lists, nums, index + 1, list);
                // 撤销操作：将i 和 index的位置互换
                Collections.swap(list, i, index);
                System.out.println("swap后：index:" + index + ", i:" + i + ",list:"+ list);
            }
        }
    }

}
