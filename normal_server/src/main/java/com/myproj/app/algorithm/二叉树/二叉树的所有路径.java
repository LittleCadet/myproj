package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * 叶子节点 是指没有子节点的节点。
 * 示例 1：
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：["1"]
 *
 *
 *      思路：
 *          - 方法1：递归：
 *          - 方法2：回溯：
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 二叉树的所有路径 extends TreeNode {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(4);


        binaryTreePaths(treeNode);
        binaryTreePathsV2(treeNode);
    }

    static List<String> results = new ArrayList<>();

    /**
     * 递归：stringBuilder 必须每次都创建。
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        dfs(root, new StringBuilder());
        return results;
    }

    /**
     * 回溯： stringBuilder 只需要创建一次， 但需要用 回溯的复原
     */
    public static List<String> binaryTreePathsV2(TreeNode root) {
        dfsV2(root, new StringBuilder());
        return results;
    }

    private static void dfs(TreeNode root, StringBuilder builder){
        if(root == null) {
            return;
        }

        // stringBuilder每次重新创建， 可以避免回溯
        StringBuilder tmp = new StringBuilder(builder);
        tmp.append(root.val);

        if(root.left == null && root.right == null) {
            results.add(tmp.toString());
        }else{
            tmp.append("->");
            dfs(root.left, tmp);
            dfs(root.right, tmp);
        }
    }


    private static void dfsV2(TreeNode root, StringBuilder cur) {
        if (root == null){
            // 不能在这里，执行以下代码， 因为左子树 + 右子树 最终都会走到这里， 所以导致相同的数据放置了两次。
            // 只能用叶子节点的定义 来执行
//            results.add(cur.toString() + root.val);
            return;
        }

        if (root.left == null && root.right == null) {
            results.add(cur.toString() + root.val);
            return;
        }
        int sz = cur.length();
        cur.append(root.val).append("->");
        dfs(root.left, cur);
        dfs(root.right, cur);
        // 回溯的复原： 删除 [sz , cur.length()]的数据。
        // 类似{@link 括号生成} , 但这是二叉树，左右节点都执行完成后，才能执行， 所以不能使用 cur.deleteCharAt(index);
        cur.delete(sz, cur.length());
    }
}
