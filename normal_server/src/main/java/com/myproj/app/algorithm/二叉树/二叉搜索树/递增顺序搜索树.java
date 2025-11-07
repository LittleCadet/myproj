package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 *
 *
 *
 *
 * @author shenxie
 * @date 2025/11/4
 */
public class 递增顺序搜索树 extends TreeNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        
        increasingBST(root);
    }

    public static TreeNode increasingBST(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        TreeNode dummy = new TreeNode(-1);
        TreeNode node = dummy;

        // 中序遍历 => list
        dfs(root, nums);

        // 只在右节点递增的搜索树
        for(int i = 0; i<nums.size(); i++) {
            node.right = new TreeNode(nums.get(i));
            node = node.right;
        }
        return dummy.right;
    }

    private static void dfs(TreeNode root, List<Integer> nums) {
        if(root == null) {
            return ;
        }
        dfs(root.left, nums);
        nums.add(root.val);
        dfs(root.right, nums);
    }
}
