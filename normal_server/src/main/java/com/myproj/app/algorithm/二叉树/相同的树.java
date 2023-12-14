package com.myproj.app.algorithm.二叉树;

/**
 * 题目：
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 思路：
 * 深度优先算法：
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 相同的树 {

    public static void main(String[] args) {
        isSameTree(null, null);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(null == p && null == q){
            return true;
        }else if (null == p || null == q){
            return false;
        }else if(p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
