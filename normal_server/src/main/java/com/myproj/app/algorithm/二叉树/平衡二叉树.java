package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;

/**
 * 给定一个二叉树，判断它是否是 平衡二叉树
 *
 *      思路：
 *          - 平衡二叉树： 即为左右子树的高度差 <= 1;
 *
 * @author shenxie
 * @date 2025/11/11
 */
public class 平衡二叉树 extends TreeNode {

    public static void main(String[] args) {
        System.out.println(isBalanced(null));
    }

    public static boolean isBalanced(TreeNode root) {
        if(null == root) {
            return true;
        }
        // 判定当前节点是否是平衡二叉树： Math.abs(height(root.left) - height(root.right)) <= 1
        // 再判定左右子节点 是否是平衡二叉树： isBalanced(root.left) && isBalanced(root.right)
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private static int height(TreeNode root) {
        if(null == root) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
