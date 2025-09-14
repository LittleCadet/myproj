package com.myproj.app.algorithm_二刷.链表;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 *     方法2：
 *          后续排序：先将 1-> 2 -> 3 -> 4 -> 5 , 变成 5，,4，,3，,2，,1 ， 之后修改当前节点的next指针 指向 前一个节点 即可。
 *
 *     方法3： 利用 list反转 + dummy节点实现。
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
        recurCopy(head, null);
    }

    /**
     * 方法1： 迭代： 即为双指针
     */
    public static ListNode reverseListCopy(ListNode head) {
        // 此处只能为null, 如果设置为 new ListNode(0); 则 原本为1-2-3-4-5， 反转后 ，会变为：5-4-3-2-1-0
        ListNode pre = null;
        while(null != head) {
            // 暂存head.next
            ListNode tmp = head.next;
            // head.next的引用指向 上一个节点pre
            head.next = pre;
            // pre指向head
            pre = head;
            // head访问下一个节点
            head = tmp;

        }
        return pre;
    }

    /**
     * 方法2： 递归调用
     *  使用：后续查找的原因：先将 1-> 2 -> 3 -> 4 -> 5 , 变成 5，,4，,3，,2，,1 ， 之后修改当前节点的next指针 指向 前一个节点 即可。
     */
    private static ListNode recurCopy(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // 终止条件
        ListNode res = recurCopy(cur.next, cur);  // 递归后继节点
        cur.next = pre;              // 指针反转， 将当前节点的next指针指向 之前的节点 即可。
        return res;                  // 返回反转链表的头节点
    }


    /**
     * 方法3： 利用 list反转 + dummy节点实现。
     */
    public ListNode reverseListV3Copy(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        List<Integer> nums = new ArrayList<>();
        // 将链表元素 放入 list中
        while(null != head) {
            nums.add(head.val);
            head = head.next;
        }
        // 反转list
        Collections.reverse(nums);

        // 将list的元素 重新写回 cur中， 并更新cur的引用， 让下次循环使用
        for(Integer num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummy.next;


    }

     public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

}
