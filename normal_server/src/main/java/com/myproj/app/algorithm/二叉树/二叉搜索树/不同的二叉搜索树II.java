package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 *      思路：
 *          - 递归：
 *              - 方法1： 正确：是穷举所有可能的 “二叉搜索树” ！！！
 *              - 方法2： 错误：原因： 这是用二分法构建 “平衡二叉搜索树”： 而不是  “二叉搜索树” ！！！ 详见：{@link 将二叉搜索树变平衡}
 *          - 构建二叉搜索树：
 *              - {@link 不同的二叉搜索树}： 求的是共计多少种 “二叉搜索树” 的组合方式： 用 动态规划
 *              - {@link 不同的二叉搜索树II}：求的是 穷举所有 “二叉搜索树” 的组合： 用 递归
 *              - {@link 前序遍历构造二叉搜索树}: 用二分法：“二叉搜索树”
 *              - {@link 将二叉搜索树变平衡}: 二分法： “平衡二叉搜索树”
 *              - {@link 把二叉搜索树转换为累加树}: 递归： 中序遍历： “二叉搜索树”
 *              - {@link 有序链表转换为二叉搜索树}: 二分法 +  中序遍历： “二叉搜索树”
 *              - {@link 递增顺序搜索树}: 中序遍历： “二叉搜索树”
 *
 *
 * @author shenxie
 * @date 2026/1/13
 */
public class 不同的二叉搜索树II extends TreeNode {

    public static void main(String[] args) {
//        generateTrees(3);
        generateTreesV2(3);
    }


    /**
     * 方法1： 递归：
     * 求 1...n 的所有可能。
     * 我们只需要把 1 作为根节点，[ ] 空作为左子树，[ 2 ... n ] 的所有可能作为右子树。
     * 2 作为根节点，[ 1 ] 作为左子树，[ 3...n ] 的所有可能作为右子树。
     * 3 作为根节点，[ 1 2 ] 的所有可能作为左子树，[ 4 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
     * 4 作为根节点，[ 1 2 3 ] 的所有可能作为左子树，[ 5 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
     * ...
     * n 作为根节点，[ 1... n ] 的所有可能作为左子树，[ ] 作为右子树。
     * 至于，[ 2 ... n ] 的所有可能以及 [ 4 ... n ] 以及其他情况的所有可能，可以利用上边的方法，把每个数字作为根节点，然后把所有可能的左子树和右子树组合起来即可。
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        return dfs(1 , n );
    }

    private static List<TreeNode> dfs(int left, int right) {
        List<TreeNode> results = new ArrayList<>();
        if(left > right) {
            results.add(null);
            return results;
        }
        for(int i = left; i <= right ; i++) {
            // 构建左子树: i 为 root节点， 则 左子树为： [left,  i -1]
            List<TreeNode> leftNodes = dfs(left, i - 1);
            // 构建右子树: i 为 root节点， 则 右子树为：[i+1, right]
            List<TreeNode> rightNodes = dfs(i + 1, right);
            for(TreeNode leftNode : leftNodes){
                for(TreeNode rightNode : rightNodes) {
                    // 构建 root 节点， 左右子树与root节点挂钩。
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    results.add(root);
                }
            }
        }

        return results;
    }

    public static List<TreeNode> generateTreesV2(int n) {
        List<TreeNode> nodes = new ArrayList<>();
        dfsV2(1 , n , nodes);
        return nodes;
    }


    /**
     * 方法2： 递归： 错误：原因： 这是用二分法构建 “平衡二叉搜索树”： 而不是  “二叉搜索树” ！！！
     * 详见：{@link 将二叉搜索树变平衡}
     */
    private static TreeNode dfsV2(int left, int right, List<TreeNode> nodes) {
        if(left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(mid);
        nodes.add(root);
        if(left <= mid - 1) {
            root.left = dfsV2(left , mid - 1, nodes);
        }
        if(right >= mid + 1) {
            root.right = dfsV2(mid + 1, right, nodes);
        }
        return root;
    }
}
