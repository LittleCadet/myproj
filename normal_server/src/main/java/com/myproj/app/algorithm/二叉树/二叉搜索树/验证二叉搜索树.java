package com.myproj.app.algorithm.二叉树.二叉搜索树;

/**
 * 题目：
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 思路：
 * 1. 中序查找：原因：
 *      二叉搜索树的特性：
 *          一定成立：root.left.val < root < root.right.val
 *          一定不成立：root.val <= pre
 *
 * @author shenxie
 * @date 2023/12/16
 */
public class 验证二叉搜索树 {

    /**
     * 注意： 是 Long 而不是 Integer的最小值， 因为测试用例中包含Integer.MIN_VALUE的测试。
     */
    static long pre = Long.MIN_VALUE;

    public static void main(String[] args) {
        System.out.println(isValidBST(null));
    }
    public static boolean isValidBST(TreeNode root) {
        return dfsCopy(root);
    }

    public static boolean dfsCopy(TreeNode root){
        if(null == root) {
            return true;
        }
        boolean left = dfsCopy(root.left);
        if(root.val <= pre) {
            return false;
        }
        pre = root.val;
        boolean right = dfsCopy(root.right);

        return left && right;
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
