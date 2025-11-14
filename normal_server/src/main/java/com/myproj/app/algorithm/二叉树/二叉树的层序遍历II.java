package com.myproj.app.algorithm.二叉树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]
 *
 *      思路：
 *          - 自底向上： list.add(0, nums);
 *          - 此题与{@link 层序遍历} 代码几乎相同， 只有：
 *              - 本题： list.add(0, nums);
 *              - {@link 层序遍历} list.add(nums);
 *
 * @author shenxie
 * @date 2025/11/13
 */
public class 二叉树的层序遍历II extends TreeNode {

    public static void main(String[] args) {
        levelOrderBottom(null);
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(null == root) {
            return new ArrayList<>();
        }

        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while(! queue.isEmpty()){
            int size = queue.size();
            List<Integer> nums = new ArrayList<>();
            // 层序遍历的核心
            while( size != 0  ) {
                TreeNode node = queue.poll();
                nums.add(node.val);
                if(null != node.left){
                    queue.add(node.left);
                }
                if(null != node.right) {
                    queue.add(node.right);
                }
                size--;
            }

            // 自底向上
            list.add(0, nums);
        }
        return list;
    }
}
