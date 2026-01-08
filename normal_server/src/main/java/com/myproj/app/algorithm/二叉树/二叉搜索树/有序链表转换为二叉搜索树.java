package com.myproj.app.algorithm.二叉树.二叉搜索树;

import com.myproj.app.algorithm.二叉树.抽象类.TreeNode;
import com.myproj.app.algorithm.链表.抽象类.ListNode;

/**
 * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为二叉搜索树。
 * 示例 1:
 * 输入: head = [-10,-3,0,5,9]
 * 输出: [0,-3,9,-10,null,5]
 * 解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
 *
 * 示例 2:
 * 输入: head = []
 * 输出: []
 *
 *      思路：
 *          - 高度平衡的二叉搜索树：
 *              - 理解：让 root 节点的左子树 与 右子树的数量尽可能接近， 则可以达到高度差 <= 1的需求。
 *                  所以需要借助 中位数 作为 root节点来解决 。
 *                   - 如果链表中的元素个数为奇数，那么唯一的中间值为中位数；
 *                   - 如果元素个数为偶数，那么唯二的中间值都可以作为中位数，而不是常规定义中二者的平均值。
 *          - 有序链表：
 *              - 理解： 二叉搜索树的中序排序 就是有序链表本身。所以可以将中序遍历 与 分治结合起来， 降低时间复杂度。
 *          - 最终：
 *              分治 + 中序遍历。
 *          - 此题 与 {@link 前序遍历构造二叉搜索树} + {@link 将二叉搜索树变平衡} 很类似：
 *              - 本题：即为通过 中序遍历 构造二叉搜索树。
 *              - {@link 前序遍历构造二叉搜索树}： 是通过 前序遍历 构造二叉搜索树
 *              - {@link 将二叉搜索树变平衡}：是中序遍历后， 用数组 变成二叉平衡树， 而本题是链表【中序遍历】 变成 二叉搜索树
 *
 * @author shenxie
 * @date 2025/11/13
 */
public class 有序链表转换为二叉搜索树 extends TreeNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        sortedListToBST(head);

    }


    static ListNode globalHead;

    /**
     * 分治 + 中序遍历。
     */
    public static TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public static int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

    public static TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        // mid = left + (right - left ) / 2 也可以： 因为中位数：可以是唯二的数字中的任意一个。
        int mid = left + (right - left + 1) / 2;
        // 此处使用占位符 即为 空节点的方式表达 root节点。等到 中序遍历遍历到的时候， 再去填充该值即可。
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        // 中序遍历的本质： 虽然真正的root节点在 链表的中间位置，
        // 但是root.left 和 root.right的之间的位置说明：当前位置是 二叉搜索树的最小节点， 即为 最左节点。 即为：链表的当前值。
        // 所以在此时： 用 globalHead.val 填充 root节点 ：本质上是 填充最左节点。
        // 即为： 先填充最左节点 ，再填充 root节点， 最后填充 最右节点
        // 思维发散：为啥 《将二叉搜索树变平衡》中序遍历形成的有序数组， 可以直接形成 真正的root节点， 而 链表 需要用占位符：先填充 left, 再root, 最后 right ？？
        // 因为： 数组找到 mid 节点【即为 root节点】很简单， 直接 arr[mid] 即可。 但是链表不行： 为了 降低 复杂度， 所以 用占位符， 先填充left, 再 root, 最后right.
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }

}
