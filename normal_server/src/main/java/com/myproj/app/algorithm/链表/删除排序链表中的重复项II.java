package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 思路：
 *      1. dummy节点：
 *          该题的核心难点：如何删除所有的重复项。
 *          解决方式：
 *              1. 记录当前重复项的值val， 如果下一个节点的依旧是val, 则将cur.next = cur.next.next; 即可
 *              2. 如何删除第一个重复项：
 *                  直接从dummy开始遍历即可，  这样就可以从第一个重复项开始了【即为从cur.next开始了】。 cur.next = cur.next.next;
 * @author shenxie
 * @date 2023/12/28
 */
public class 删除排序链表中的重复项II {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        deleteDuplicates(head);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while(cur != null) {
            if(cur.next != null && cur.next.next != null && cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while(cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
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
