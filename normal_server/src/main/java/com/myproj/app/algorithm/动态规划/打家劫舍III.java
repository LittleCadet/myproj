package com.myproj.app.algorithm.动态规划;

import com.myproj.app.algorithm.二叉树.二叉树中所有距离为k的节点;
import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 *
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 *
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 *
 *      思路：
 *          - 动态规划：
 *              - 二叉树的节点检索问题： 与{@link 二叉树中所有距离为k的节点}有点类似：
 *                  - {@link 二叉树中所有距离为k的节点}: 通过 hashMap 记录各个节点的父节点， 从而完成向上递归
 *                  - {@link 打家劫舍III}: 通过 hashMap记录各个节点的最大权益值， 从而完成 看似 向上递归 但 本质 向下递归的目标。
 *
 * @author shenxie
 * @date 2025/11/18
 */
public class 打家劫舍III extends TreeNode {

    public static void main(String[] args) {
        System.out.println(rob(null));
    }

    // k-v: 节点选择时，当前节点的最大权益值
    static Map<TreeNode, Integer> f = new HashMap<>();
    // k-v: 节点不选择时， 当前节点的最大权益值
    static Map<TreeNode, Integer> g = new HashMap<>();

    /**
     * 动态规划：分两种情况： 当前节点选中 和 不选中：
     * - 当前节点选中时：最大权益值 = root.val + 左右子节点不选中时的最大权益值
     * - 当前节点不选中时：最大权益值 = Math.max(左节点选中，左节点不选中) + Math.max(右节点选中， 右节点不选中);
     */
    public static int rob(TreeNode root) {
        dfs(root);
        // 返回 root节点，在选中和不选中时，最大权益值
        return Math.max(f.getOrDefault(root,0), g.getOrDefault(root,0));
    }

    private static void dfs(TreeNode root) {
        if(null == root) {
            return ;
        }

        dfs(root.left);
        dfs(root.right);

        // 因为选中该节点， 所以最大权益值为： root.val + left 和 right的不选中时的最大权益值的和
        f.put(root, root.val + g.getOrDefault(root.left, 0) + g.getOrDefault(root.right, 0));
        // 因为不选中该节点， 所以最大权益值为：left 和 right在选中和不选中两种情况下的最大权益值的和
        g.put(root, Math.max(f.getOrDefault(root.left,0), g.getOrDefault(root.left, 0)) + Math.max(f.getOrDefault(root.right, 0), g.getOrDefault(root.right, 0)));
    }
}
