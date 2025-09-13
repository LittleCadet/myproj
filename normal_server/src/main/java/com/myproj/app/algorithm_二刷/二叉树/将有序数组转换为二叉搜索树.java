package com.myproj.app.algorithm_二刷.二叉树;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵
 * 二叉搜索树。
 *
 * 示例 1：
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 *
 * 示例 2：
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
 *
 *  思路：
 *      - 高度平衡的二叉搜索树： 指的是 树：从左子树到右子树的值大小：是按照从小到大排序的， 高度平衡，意味着：取左子树和右子树的中间节点作为根节点 即可。
 *      - 二叉搜索树的中序排序：本身就是一个升序排列的数组。 所以本题本质使用 升序数组 恢复 一个二叉搜索树。
 *
 * @author shenxie
 **/
public class 将有序数组转换为二叉搜索树 extends TreeNode{

    public static void main(String[] args) {
        int[] nums = new int[]{-10,-3,0,5,9};
        process(nums, 0, nums.length-1);
    }

    private static TreeNode process(int[] nums, int left, int right) {
        if(left > right) {
            return null;
        }
        // 根据题意：高度平衡： 所以找到根节点位置: 左节点作为根节点 【左、右节点都可以】
        int mid = ( left + right ) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 左子树
        root.left = process(nums, left, mid-1);

        // 右子树
        root.right = process(nums, mid + 1, right);

        return root;
    }
}
