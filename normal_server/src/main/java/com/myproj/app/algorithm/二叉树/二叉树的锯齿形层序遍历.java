package com.myproj.app.algorithm.二叉树;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 思路：
 *      1. 层序遍历：
 *          注意：
 *              1.1 想要做到锯齿形遍历， 只能从LinkedList的放入的方法上入手： 即为offerFirst() / offerLast();
 *              1.2 不能通过 变更node.right / node.left的顺序，来放入LinkedList中，
 *                  因为这只会影响当前子树的下一层的遍历顺序。 而不是所有子树的下一层的遍历顺序。
 * @author shenxie
 * @date 2023/12/27
 */
public class 二叉树的锯齿形层序遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        // 正确方法
        System.out.println(zigzagLevelOrder(root));;
        // 错误方法
//        System.out.println(zigzagLevelOrderV2(root));;
    }

//    public static void main(String[] args) {
//        Deque<Integer> nums = new LinkedList<>();
//        nums.offer(1);
//        nums.offer(2);
//        nums.offer(3);
//        System.out.println(nums);
//        Deque<Integer> nums2 = new LinkedList<>();
//        nums2.offerFirst(1);
//        nums2.offerFirst(2);
//        nums2.offerFirst(3);
//        System.out.println(nums2);
//        Deque<Integer> nums3 = new LinkedList<>();
//        nums3.offerLast(1);
//        nums3.offerLast(2);
//        nums3.offerLast(3);
//        System.out.println(nums3);
//    }

    /**
     * 该方法是正确的：
     * 想要做到锯齿形遍历， 只能从LinkedList的放入的方法上入手： 即为offerFirst() / offerLast();
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(null == root) {
            return list;
        }
        queue.offer(root);
        // 必须要为true ， 因为根节点必定在列表的第一个元素的位置。
        boolean reverse = true;
        while( ! queue.isEmpty()){
            int size = queue.size();
            Deque<Integer> nums = new LinkedList<>();
            while(size != 0){
                TreeNode node = queue.poll();

                if(reverse){
                    // 从第一个开始插入， 其余元素向后移动。
                    nums.offerFirst(node.val);
                }else{
                    // 从最后一个开始插入， 其余元素向前移动。
                    // 与add的结果相同。
                    nums.offerLast(node.val);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                size --;
            }
            reverse = !reverse;
            list.add(new LinkedList(nums));
        }
        return list;
    }

    /**
     * 以下方法是错误的：
     *  往queue中先放入node.left / node.right, 代表的是： 当前子树的下一层的遍历顺序：eg:
     *      先放入node.right, 再放入node.left.   代表： 下一层的遍历顺序为： 先right 再left.
     *  如果想要做到锯齿形遍历， 只能从LinkedList的放入的方法上入手： 即为offerFirst() / offerLast();
     */
    public static List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(null == root) {
            return list;
        }
        queue.offer(root);
        boolean reverse = true;
        while( ! queue.isEmpty()){
            int size = queue.size();
            Deque<Integer> nums = new LinkedList<>();
            while(size != 0){
                TreeNode node = queue.poll();
                nums.add(node.val);
                if(reverse){
                    if(node.right != null) {
                        queue.offer(node.right);
                    }
                    if(node.left != null) {
                        queue.offer(node.left);
                    }
                }else{
                    if(node.left != null) {
                        queue.offer(node.left);
                    }
                    if(node.right != null) {
                        queue.offer(node.right);
                    }
                }

                size --;
            }
            reverse = !reverse;
            list.add(new LinkedList(nums));
        }
        return list;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
