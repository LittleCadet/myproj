package com.myproj.app.algorithm_二刷.二叉树;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 示例 2：
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 *      思路：
 *          - 二叉树的翻转【只要是深度遍历即可】：不能是中序遍历的原因：必须在已知当前节点的左右子树的情况下， 才能完成交换，但是中序节点不知道当前节点的左右子树。
 *              - 对于前序遍历而言：类似于数组中的两数交换：
 *                  TreeNode tmp = root.left;
 *                  root.left = root.right;
 *                  root.right = tmp;
 *              - 对于后序遍历而言：
 *                  root.left = right;
 *                  root.right = left;
 * @author shenxie
 **/
public class 翻转二叉树 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        invertTree(treeNode);
    }

    /**
     * 方法1：前序遍历
     */
    public static TreeNode invertTree(TreeNode root) {
        if(null == root) {
            return null;
        }

        // 当前节点的左右子树交换
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        // 交换当前节点的左子树
        invertTree(root.left);
        // 交换当前节点的右子树
        invertTree(root.right);
        return root;
    }

    /**
     * 方法2： 后序遍历。
     */
    public static TreeNode invertTreeV2(TreeNode root) {
        if(null == root) {
            return null;
        }
        TreeNode left = invertTreeV2(root.left);
        TreeNode right = invertTreeV2(root.right);
        root.left = right;
        root.right = left;
        return root;
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
