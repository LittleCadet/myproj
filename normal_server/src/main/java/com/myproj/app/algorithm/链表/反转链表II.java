package com.myproj.app.algorithm.链表;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 思路：
 *      1.方法1： 穿针引线：
 *          1.1 链表元素的调换： 涉及到三个元素：pre + cur + next.
 *          1.2 pre节点：永远是left的前一个元素。
 *          1.3 cur节点：是left的第一个元素。
 *          1.4 next节点： 永远是cur的下一个元素。
 *          1.5 穿针引线三大步： 记住图：https://leetcode.cn/problems/reverse-linked-list-ii/solutions/634701/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/?envType=study-plan-v2&envId=top-interview-150
 *              a. 执行操作 ①：把 curr 的下一个节点指向 next 的下一个节点；
 *              b. 执行操作 ②：把 next 的下一个节点指向 pre 的下一个节点；
 *              c. 执行操作 ③：把 pre 的下一个节点指向 next。
 *
 *      2. 方法3： 反转链表子区间 + 恢复原链表  【推荐】
 *          - 反转链表子区间： 与{@link 反转链表}完全相同
 *
 * @author shenxie
 * @date 2023/12/27
 */
public class 反转链表II {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
//        reverseBetween(head, 2,4);
//        reverseBetweenV3(head, 2,4);
        reverseBetweenV4(head, 2,4);
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        // 找到pre的节点： 逐个next的原因： 链表找元素只能逐个next.
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 找到当前节点
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            // 找到next节点。
            next = cur.next;
            // 穿针引线三大步：记住图
            cur.next = next.next;
            next.next = pre.next;
            // pre.next 永远指向next
            pre.next = next;
        }
        return dummyNode.next;
    }


    /**
     * 错误解法： 想法： 希望直接将两个节点的值替换完成， 就行了。
     *
     * 错误原因： 该题：替换的是值和引用， 而不只是 值。
     */
    public ListNode reverseBetweenV2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);
        ListNode curLeft = dummy;
        ListNode curRight = dummy;
        int leftv = 0 ;
        int rightv = 0;
        for(int i = 0 ; i<left; i++) {
            curLeft = curLeft.next;
        }
        leftv = curLeft.val;

        for(int i = 0 ; i<right; i++) {
            curRight = curRight.next;
        }
        rightv = curRight.val;

        curLeft.val = rightv;
        curRight.val = leftv;

        return dummy.next;

    }

    /**
     * 错误解法： 想法： 希望直接将两个节点的值替换完成， 就行了。
     * 错误原因： 该题：替换的是值和引用， 而不只是 值。
     */
    public static ListNode reverseBetweenV4(ListNode head, int left, int right) {
        ListNode cur = new ListNode(0);
        ListNode dummy = cur;
        List<Integer> nums = new ArrayList<>();
        while(null != head) {
            nums.add(head.val);
            head = head.next;
        }

        int leftVal = nums.get(left -1);
        int rightVal = nums.get(right -1);
        for(int i = 0 ; i<nums.size(); i++) {
            if(i + 1 == left) {
                nums.set(i, rightVal);
            }
            if(i + 1 == right) {
                nums.set(i, leftVal);
            }
            cur.next = new ListNode(nums.get(i));
            cur = cur.next;
        }

        return dummy.next;
    }


    /**
     * 方法3： 通俗易懂。
     */
    public static ListNode reverseBetweenV3(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode last = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = last;
        return dummyNode.next;
    }

    /**
     * 反转链表的子区间： 通用做法。
     * 与{@link 反转链表}完全相同： 即为 将 1 -> 2 -> 3 变为 3-> 2 -> 1
     */
    private static void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            // >>>>交换开始
            cur.next = pre;
            pre = cur;
            // >>>>交换结束
            cur = next;
        }
    }



    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
