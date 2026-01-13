package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 *
 * 示例 1：
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 *
 * 示例 2：
 * 输入: root = [2,1,3]
 * 输出: [2,1,3]
 *
 *      思路：
 *          - 与{@link 有序链表转换为二叉搜索树}很类似：
 *              - 前者： 中序遍历后的数组，形成 平衡的二叉搜索树
 *              - 后者： 链路【中序遍历】, 形成二叉搜索树
 *
 *           *      - 构建二叉搜索树：
 *      *     - {@link 不同的二叉搜索树}： 求的是共计多少种 “二叉搜索树” 的组合方式： 用 动态规划
 *      *     - {@link 不同的二叉搜索树II}：求的是 穷举所有 “二叉搜索树” 的组合： 用 递归
 *      *     - {@link 前序遍历构造二叉搜索树}: 用二分法：“二叉搜索树”
 *      *     - {@link 将二叉搜索树变平衡}: 二分法： “平衡二叉搜索树”
 *      *     - {@link 把二叉搜索树转换为累加树}: 递归： 中序遍历： “二叉搜索树”
 *      *     - {@link 有序链表转换为二叉搜索树}: 二分法 +  中序遍历： “二叉搜索树”
 *      *     - {@link 递增顺序搜索树}: 中序遍历： “二叉搜索树”
 *
 * @author shenxie
 * @date 2025/11/4
 */
public class 将二叉搜索树变平衡 extends TreeNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        balanceBST(root);
    }

    public static TreeNode balanceBST(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        // 先中序遍历
        dfs(root, nums);
        // 重建二叉平衡树
        return build(0, nums.size()-1, nums);
    }

    private static void dfs(TreeNode root, List<Integer> nums) {
        if(null == root) {
            return ;
        }
        dfs(root.left, nums);
        nums.add(root.val);
        dfs(root.right, nums);
    }

    /**
     * 入参必须是 l,r:
     * 因为如果使用nums.get(0), nums.get(nums.size()-1), 则 mid = l + (r-l) /2, 此时极大可能：nums中没有该值！！！
     *
     *
     * @param l 左子树：起始个数：
     * @param r 右子树：终止个数
     * @param nums 中序遍历的数组
     * @return
     */
    private static TreeNode build(int l, int r, List<Integer> nums){
        int mid = l + (r-l) /2;
        // 数组 找到 mid节点 即为 root节点 很简单，
        // 但是链表 很麻烦：详见 《有序链表转换为二叉搜索树》, 为了降低复杂度， 用 占位符 ：先填充left, 再 root, 最后right
        TreeNode root = new TreeNode(nums.get(mid));
        // “二叉搜索树变平衡”的语义：左右子树的高度差 <= 1, 在二叉搜索树中很特殊：升序数组【中序遍历】，用索引 配合 mid 即可完成 “左右子树高度差<=1”：
        // 因为： 在数组中： index 是 均匀分布在mid周边的：即为： 要么 index >= mid , 要么 index <= mid.
        // 即为： l<mid -1 或者 r>= mid + 1
        if(l <= mid - 1) {
            root.left = build(l, mid - 1, nums);
        }
        if(r >= mid + 1) {
            root.right = build(mid + 1, r, nums);
        }
        return root;
    }
}
