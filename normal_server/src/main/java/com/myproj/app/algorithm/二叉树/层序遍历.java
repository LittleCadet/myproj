package com.myproj.app.algorithm.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author shenxie
 * @date 2023/12/8
 */
public class 层序遍历 {

    public static void main(String[] args) {
        levelOrder(null);
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

    public static List<List<Integer>> levelOrder(TreeNode root) {
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
            list.add(nums);
        }
        return list;
    }
}
