package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 *  
 * 
 * @author shenxie
 * @date 2025/11/11
 */
public class 二叉树的最小深度 extends TreeNode {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        System.out.println(minDepth(treeNode));
    }

    public static int minDepth(TreeNode root) {
        if(null == root) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        // 如果左子树或右子树的深度不为 0，即存在一个子树，那么当前子树的最小深度就是该子树的深度+1
        // 如果左子树和右子树的深度都不为 0，即左右子树都存在，那么当前子树的最小深度就是它们较小值+1
        return (left == 0 || right == 0 ) ? left + right + 1 : Math.min(left,right) + 1;
    }
}
