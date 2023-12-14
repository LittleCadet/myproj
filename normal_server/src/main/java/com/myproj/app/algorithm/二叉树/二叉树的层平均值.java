package com.myproj.app.algorithm.二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目：
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 * 思路：
 * 层序遍历：
 *      1. 对每一层的值相加， 之后求avg即可。
 *      2. 层序遍历的核心： for循环queue.size次， 逐个放入LinkedList中即可。
 *
 * @author shenxie
 * @date 2023/12/13
 */
public class 二叉树的层平均值 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while( ! queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for(int i = 0; i< size; i++){
                TreeNode node = queue.poll();
                sum += node.val;
                if(null != node.left){
                    queue.offer(node.left);
                }
                if(null != node.right){
                    queue.offer(node.right);
                }
            }
            res.add(sum/size);
        }
        return res;
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
}
