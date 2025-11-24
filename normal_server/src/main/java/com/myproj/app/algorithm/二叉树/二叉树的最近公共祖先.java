package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.二叉搜索树.二叉搜索树的最近公共祖先;

/**
 * 题目：
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 思路：【先序 + 后序遍历】
 *      先序遍历： 自顶而下， 用于数据的前置处理。
 *      后序遍历： 自底而上， 用于数据的后置处理。
     * 递归左子树： 判定在左子树是否能找到节点 p/q
     * 递归右子树： 判定在右子树是否能找到节点 p/q
     * 找到的标准： root = null || root = p || root == q
 *
 * 所以分为三种情况：
 * 1. 节点p/q在左子树： 则right = null , 此时返回left 就是最近公共祖先。
 * 2. 节点p/q在右子树： 则left = null, 此时返回right 就是最近公共祖先。
 * 3. 节点在 左子树 + 右子树： 则left != null && right != null, 此时root就是最近公共祖先。
 *
 *
 *      - 与{@link 二叉搜索树的最近公共祖先}：解法一致：
 *          但{@link 二叉搜索树的最近公共祖先}可以用二叉搜索树的特性【left.val < root.val < right.val;】求解：
 *
 * @author shenxie
 * @date 2023/12/11
 */
public class 二叉树的最近公共祖先 {

    public static void main(String[] args) {
        lowestCommonAncestor(null, null , null);
    }

    /**
     * 方法1：前序遍历 + 后续遍历
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null ) {
            return null;
        }

        if(root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 如果left != null， 代表： 在左子树找到了节点p / q
        // 如果right != null, 代表： 在右子树找到了节点p / q
        // 如果left != null && right !=null : 代表： 在root节点的左子树和右子树， 分别找到了p / q， 所以此时 root就是最近公共祖先。
        if (left != null && right != null) {
            return root;
        }
        // 如果left = null, 代表： p / q节点都在右子树。 而递归本质上是： 只要符合条件就返回， 所以返回的right节点， 就是最近公共祖先。
        if (left == null) {
            return right;
        }
        // 如果right = null, 代表: p / q节点都在左子树。
        return left;
    }

    /**
     * 方法2： 本质与 方法1 一致， 只是为了便于理解， 换种写法。
     */
    public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null ) {
            return null;
        }
        if(p == root || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestorV2(root.left, p, q);
        TreeNode right = lowestCommonAncestorV2(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }

        if(left != null && right == null) {
            return left;
        }

        if(left == null && right != null) {
            return right;
        }

        return null;

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
