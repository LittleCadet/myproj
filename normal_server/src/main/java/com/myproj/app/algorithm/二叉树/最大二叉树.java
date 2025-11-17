package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;

/**
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 *     创建一个根节点，其值为 nums 中的最大值。
 *     递归地在最大值 左边 的 子数组前缀上 构建左子树。
 *     递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 *
 * 示例 1：
 * 输入：nums = [3,2,1,6,0,5]
 * 输出：[6,3,5,null,2,0,null,null,1]
 * 解释：递归调用如下所示：
 * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
 *     - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
 *         - 空数组，无子节点。
 *         - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
 *             - 空数组，无子节点。
 *             - 只有一个元素，所以子节点是一个值为 1 的节点。
 *     - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
 *         - 只有一个元素，所以子节点是一个值为 0 的节点。
 *         - 空数组，无子节点。
 *
 *
 *       思路：
 *          - 递归： 按照题意： 找到最大值做root, 那么[left, root - 1] 是 左子树， [root + 1, right] 是 右子树
 *  *          - 与 {@link 最大二叉树} 类似：
 *  *              - {@link 最大二叉树II}：将入参：val 放入 已有的最大二叉树中， 使其重新变为 最大二叉树
 *  *              - {@link 最大二叉树}： 将数组 变成 最大二叉树
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 最大二叉树 extends TreeNode {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length -1);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if(left > right) {
            return null;
        }
        int rootIndex = left ;
        // 注意始终点：
        // 找到 在[left, right]中的最大值的下标
        for(int i = left ; i<=right; i++) {
            if(nums[i] > nums[rootIndex]) {
                rootIndex = i;
            }
        }

        TreeNode root = new TreeNode(nums[rootIndex]);
        root.left = dfs(nums, left , rootIndex - 1);
        root.right = dfs(nums, rootIndex + 1, right);

        return root;
    }
}
