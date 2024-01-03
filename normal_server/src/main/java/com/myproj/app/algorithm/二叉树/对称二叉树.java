package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 思路：
 * 深度优先算法：
 *      1. 对称二叉树 与 相同的树， 本质上是一个题目。
 *      2. 方法一：搞两颗树对比。
 *      3. 方法二：一棵树的左右子树对比：
 *      4. 2个方法的核心思想相同:  当前节点的左右子树对比。
 *
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 对称二叉树 {

    public static void main(String[] args) {
        isSymmetricV1(null);
        isSymmetricV2(null);
    }

    /**
     * 方法1： 搞2棵树对比
     */
    public static boolean isSymmetricV1(TreeNode root) {
        return check(root, root);
    }

    /**
     * 方法2： 1棵树的左右子树对比
     */
    public static boolean isSymmetricV2(TreeNode root) {
        return check(root.left, root.right);
    }

    public static boolean check(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        } else if (null == p || null == q) {
            return false;
        }
        // 当前节点的左右子节点对比。
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
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
