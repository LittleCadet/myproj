package com.myproj.app.algorithm_二刷.二叉树;

/**
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 *
 * 示例 2：
 * 输入：root = [1,2]
 * 输出：1
 *
 *      思路：
 *          - 后续遍历：本题题意： 求的是 直径， 不包含节点本身的长度
 *
 * 这题很容易 与 《二叉树的最大深度》混淆， 这里说明一下：
 * 先看这题的解法：
 *
 * class Solution {
 *     int max = 0;
 *     public int diameterOfBinaryTree(TreeNode root) {
 *         depth(root);
 *         // 因为节点本身不算深度，但由于左子树 和 右子树的深度都+1了， 所以要-2
 *         return max -2 ;
 *     }
 *
 *     public int depth(TreeNode node) {
 *         if(null == node) {
 *             return 0;
 *         }
 *
 *         int left = depth(node.left) +1;
 *         int right = depth(node.right) +1;
 *         // 更新最大深度
 *         max = Math.max(left + right, max);
 *         // 返回当前节点的最大深度
 *         return Math.max(left, right) ;
 *     }
 * }
 *
 * 再看《二叉树的最大深度》的解法：
 *
 * class Solution {
 *     public int maxDepth(TreeNode root) {
 *         return depth(root);
 *     }
 *
 *     public int depth(TreeNode root) {
 *         if(null == root) {
 *             return 0;
 *         }
 *         int leftHight = depth(root.left) + 1;
 *         int rightHight = depth(root.right) + 1;
 *         return Math.max(leftHight, rightHight);
 *     }
 * }
 * 
 * 是不是感觉很奇怪， 两题的解法很类似， 但为啥《二叉树的最大深度》的返回值没有 - 2的操作， 因为：
 * 《二叉树的最大深度》在题意上已经标明：节点本身算深度， 所以返回值没有-1 或者 -2的这种骚操作 ！！！
 * 而本题在题意上也标明： 算的是边的长度， 而不是节点 与 节点的深度，而返回值由于左子树 和 右子树的深度都+1了， 所以最终要 -2 ！！！！！
 *
 *
 *
 * @author shenxie
 **/
public class 二叉树的直径 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);
        System.out.println(diameterOfBinaryTree(treeNode));
    }


    static int max = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        depthCopy(root);
        // 因为节点本身不算深度，但由于左子树 和 右子树的深度都+1了， 所以要-2
        return max - 2;
    }

    public static int depthCopy(TreeNode node) {
        if (null == node) {
            return 0;
        }

        int left = depthCopy(node.left) + 1;
        int right = depthCopy(node.right) + 1;
        // 更新最大深度
        max = Math.max(left + right, max);
        // 返回当前节点的最大深度
        return Math.max(left, right);
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
