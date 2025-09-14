package com.myproj.app.algorithm_二刷.二叉树;

/**
 * 题目：
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 思路： 前中后序遍历： 均可
 *
 *
 * @author shenxie
 **/
public class 对称二叉树 {

    public static void main(String[] args) {
        
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);

        isSymmetric(treeNode);
        
    }

    public static boolean isSymmetric(TreeNode root) {
        return checkCopy(root.left, root.right);
    }

    /**
     * 先序遍历
     */
    public static boolean checkCopy(TreeNode left, TreeNode right) {
        if(null == left && null == right) {
            return true;
        }
        if(null == left || null == right) {
            return false;
        }

        // 对称二叉树的条件： 两个节点比较，值相等， 且左边节点的左子树 == 右边节点的右子树， 且 左边节点的右子树 == 右边节点的右子树
        return left.val == right.val && checkCopy(left.left, right.right) && checkCopy(left.right, right.left);
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
