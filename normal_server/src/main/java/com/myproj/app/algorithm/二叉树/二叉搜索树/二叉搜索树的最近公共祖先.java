package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二叉树.二叉树的最近公共祖先;
import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 *      解法：
 *          - 方法1【通解】： 与{@link 二叉树的最近公共祖先}完全一样的解法
 *          - 方法2【推荐】： 利用 二叉搜索树的特性：left.val < root.val < right.val;
 *
 * @author shenxie
 * @date 2025/11/4
 */
public class 二叉搜索树的最近公共祖先 extends TreeNode {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

//        lowestCommonAncestor(root, null, null);
        lowestCommonAncestorV2(root, null, null);
    }

    /**
     * 方法1： 与{@link 二叉树的最近公共祖先}完全一样的解法
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(null == root) {
            return null;
        }

        if(p == root || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) {
            return root;
        }
        if(left != null) {
            return left;
        }


        return right;

    }


    /**
     * 方法2：利用 二叉搜索树的特性：left.val < root.val < right.val;
     *
     */
    public static TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        while(true){
            if(p.val < root.val && q.val < root.val) {
                root = root.left;
            }else if(p.val > root.val && q.val > root.val) {
                root = root.right;
            }else{
                break;
            }
        }
        return root;
    }
}
