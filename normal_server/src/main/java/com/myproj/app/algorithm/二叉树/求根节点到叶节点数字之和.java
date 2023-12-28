package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *     例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 *
 * 思路：
 *      1. 深度优先算法：
 *          难点：如何将根节点到叶子节点的数字拼接起来
 *          解法： preSum * 10 + root.val
 * @author shenxie
 * @date 2023/12/27
 */
public class 求根节点到叶节点数字之和 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumNumbers(root));;
    }

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int preSum) {
        if(root == null) {
            return 0;
        }
        // 难点： 如何将根节点到叶子节点的数字拼接起来
        // 解法： preSum * 10 + root.val
        int sum = preSum * 10 + root.val;
        // 这个判定很重要，代表： 当前dfs循环的正确返回值：即为根节点到叶子节点的数字拼接以后的结果。
        // 如果没有这个判定， 那么代表dfs循环的返回值为0；【即为该方法的最初的返回值。 】
        if(null == root.left && null == root.right) {
            return sum;
        }else{
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
