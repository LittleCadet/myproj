package com.myproj.app.algorithm.tree;

/**
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * leetCode: Ex235
 *
 * @author shenxie
 * @date 2021/4/21
 */
public class CommonNode {

    private static TreeNode treeNode;


    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] nums =  {6,2,8,0,4,7,9,3,5};
        for (int num : nums) {
            insert(num);
        }
        System.out.println(treeNode.toString());
        System.out.println(lowestCommonAncestor(treeNode ,  new TreeNode(2) , new TreeNode( 8)));
    }





    /**
     * 定义树
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "value=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

    }

    /**
     * 利用二叉搜索树的特性
     * 1. 节点按照从左 => 右 ： 小 => 大
     * 2. 二叉树： 任意一个节点，最多只能有二个节点
     * 3. 树的检索： 肯定与递归有关
     *
     * @param root 理解为： 当前节点
     * @param p ：左/ 右节点
     * @param q : 右/ 左节点
     * @return 深度最大的父节点
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((p.val - root.val) * (q.val - root.val) <= 0) {
            return root;
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }

    public static void insert(int val){
        if(null == treeNode) {
            treeNode = new TreeNode(val);
        } else {
            TreeNode currentNode = treeNode;
            while(true){
                if(currentNode.val < val) {
                    if( null == currentNode.right) {
                        currentNode.right = new TreeNode(val);
                        return;
                    }

                    // 将当前节点替换为 右节点
                    currentNode = currentNode.right;
                }else if(currentNode.val > val){
                    if(null == currentNode.left) {
                        currentNode.left = new TreeNode(val);
                        return;
                    }
                    // 将当前节点替换为 左节点
                    currentNode = currentNode.left;
                }
            }

        }

    }
}
