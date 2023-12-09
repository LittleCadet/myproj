package com.myproj.app.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shenxie
 * @date 2023/12/8
 */
public class 层序遍历 {

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

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if(null == root) {
                return new ArrayList<>();
            }

            List<List<Integer>> list = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();

            stack.push(root);
            while( ! stack.isEmpty()) {
                int count = stack.size();
                List<Integer> nums = new ArrayList<>();
                while( count != 0 ) {

                    TreeNode node = stack.pop();
                    nums.add(node.val);
                    if(null != node.left){
                        stack.push(node.left);
                    }
                    if(null != node.right) {
                        stack.push(node.right);
                    }
                    count --;
                }
                list.add(nums);
            }
            return list;
        }
    }
}
