package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 * 示例 1：
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 *
 * 示例 2：
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *
 *      思路：
 *          - 中序遍历：累加求和
 *              - 实际：前中后序排序， 都行： 但 二叉搜索树 优先使用中序遍历：不容易出错： eg: {@link 把二叉搜索树转换为累加树}：只能用中序遍历
 *
 * @author shenxie
 * @date 2025/11/4
 */
public class 二叉搜索树的范围和 extends TreeNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(rangeSumBST(root, 1,4));
    }

    private static int sum = 0 ;
    public static int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return sum;
    }

    private static void dfs(TreeNode root, int low, int high) {
        if(null == root) {
            return ;
        }
        dfs(root.left,low, high);
        // 实际上： 前中后序遍历， 都行
        if(low <= root.val && root.val <= high) {
            sum += root.val;
        }
        dfs(root.right, low, high);
    }
}
