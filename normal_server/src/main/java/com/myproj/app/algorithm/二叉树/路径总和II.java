package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 *
 *      思路：
 *          - 回溯：【本质是 深度优先】
 *
 * @author shenxie
 * @date 2025/11/11
 */
public class 路径总和II extends TreeNode {

    public static void main(String[] args) {
        System.out.println(pathSum(null, 10));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(root, new ArrayList<>(), results, targetSum, 0);

        return results;
    }

    private static void dfs(TreeNode root, List<Integer> result , List<List<Integer>> results, int targetSum, int sum) {
        if(null == root) {
            return ;
        }
        sum+=root.val;
        result.add(root.val);

        if(sum == targetSum && null == root.left && null == root.right) {
            results.add(new ArrayList<>(result));
        }

        dfs(root.left, result, results, targetSum, sum);
        dfs(root.right, result, results, targetSum, sum);
        result.remove(result.size() - 1);
    }
}
