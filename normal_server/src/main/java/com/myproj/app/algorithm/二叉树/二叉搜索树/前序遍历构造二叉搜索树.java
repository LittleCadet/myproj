package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二叉树.从前序与中序遍历序列构造二叉树;
import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
 * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
 * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , Node.right 的任何后代的值 严格大于 Node.val。
 * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
 *
 * 示例 1：
 * 输入：preorder = [8,5,1,7,10,12]
 * 输出：[8,5,10,1,7,null,12]
 *
 * 示例 2:
 * 输入: preorder = [1,3]
 * 输出: [1,null,3]
 *
 *      思路：
 *          - 用前序遍历的数组 构造 中序遍历的数组【只有二叉搜索树 能通过 Arrays.sort(preorder)的方式完成】， 之后就是 {@link 从前序与中序遍历序列构造二叉树}
 *          - 此题 与 {@link 有序链表转换为二叉搜索树} 很类似：
 *              - {@link 有序链表转换为二叉搜索树}：即为通过 中序遍历 构造二叉搜索树。
 *              - 本题： 是通过 前序遍历 构造二叉搜索树
 *
 * @author shenxie
 * @date 2025/11/4
 */
public class 前序遍历构造二叉搜索树 extends TreeNode {

    public static void main(String[] args) {
//        System.out.println(bstFromPreorder(new int[]{8,5,1,7,10,12}));
        System.out.println(bstFromPreorderV2(new int[]{8,5,1,7,10,12}));
    }

    static Map<Integer, Integer> map = new HashMap<>();

    /**
     * 方法1： 前序遍历 + 中序遍历
     */
    public static TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = new int[preorder.length];

        System.arraycopy(preorder,0,inorder,0,preorder.length);

        // 构造中序排序： 因为是二叉搜索树， 所以中序排序，一定是升序的。
        Arrays.sort(inorder);

        for(int i = 0 ; i< inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(preorder, inorder, 0, preorder.length - 1, 0 , inorder.length - 1);
    }

    private static TreeNode dfs(int[] preorder, int[] inorder, int preorderStart, int preorderEnd, int inorderStart, int inorderEnd){
        if(preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        int val = preorder[preorderStart];
        int mid = map.get(val);
        TreeNode root = new TreeNode(val);

        // 重要： 找到左子树的剩余个数
        int left_size = mid - inorderStart;

        root.left = dfs(preorder, inorder, preorderStart + 1, preorderStart + left_size, inorderStart, mid-1);
        root.right = dfs(preorder, inorder, preorderStart + left_size + 1, preorderEnd, mid + 1, inorderEnd);

        return root;
    }


    /**
     * 方法2： 二分法
     */
    public static TreeNode bstFromPreorderV2(int[] preorder) {
        return dfs(preorder, 0, preorder.length -1);
    }

    private static TreeNode dfs(int[] preorder, int left, int right){
        if(left > right) {
            return null;
        }

        // root节点， 一定是 left所在的元素，因为是前序遍历
        TreeNode root = new TreeNode(preorder[left]);

        // 在区间 [left..right] 里找最后一个小于 preorder[left] 的下标
        int l = left, r=right;
        while(l<r) {
            // 注意mid: r-l + 1： 因为：preorder的首个元素是root节点，一定不包含左右子树，所以 + 1
            int mid = l + (r-l + 1) / 2;
            if(preorder[mid] < preorder[left]) {
                // 下一轮搜索区间是 [mid, r]
                l = mid;
            }else {
                // 下一轮搜索区间是 [l, mid - 1]
                r = mid - 1;
            }
        }

        // 左子树区间：[left + 1, l]
        root.left = dfs(preorder, left + 1, l);
        // 右子树区间：[l + 1, right]
        root.right = dfs(preorder, l + 1, right);
        return root;

    }
}
