package com.myproj.app.algorithm_二刷.链表;


/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 *
 * 解法：
 *      方法1：
 *          双指针： 即为迭代的方式：
 *              通过引入两个变量的方式来完成替换：
 *                  变量1： 最终输出的答案：pre
 *                  变量2： 临时节点： tmp: 用于暂存head.next的节点。
 *
 * @author shenxie
 **/
public class 反转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // 方法1：
//        reverseList(head);
        // 方法2：
        recur(head, null);
    }

    /**
     * 方法1： 迭代： 即为双指针
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while(null != head) {
            // 暂存head.next
            ListNode tmp = head.next;
            // 修改next的引用指向
            head.next = pre;
            // pre暂存head
            pre = head;
            // head访问下一个节点
            head = tmp;

        }
        return pre;
    }

    /**
     * 方法2： 递归调用
     */
    private static ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // 终止条件
        ListNode res = recur(cur.next, cur);  // 递归后继节点
        cur.next = pre;              // 修改节点引用指向
        return res;                  // 返回反转链表的头节点
    }

     public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

}
