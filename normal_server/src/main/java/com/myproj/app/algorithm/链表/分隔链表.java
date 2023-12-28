package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 思路：
 *      1. 用2个链表实现：
 *          核心思想： 一个链表small用来填充 < x 的节点， 一个链表large用来填充 >=x的节点。 最后将small.next = large即可。
 *      2. 错误想法：
 *          不要妄想： 用一堆指针来移动节点， 太麻烦了。
 *
 *
 * @author shenxie
 * @date 2023/12/28
 */
public class 分隔链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        System.out.println(partition(head, 3));
    }

    public static ListNode partition(ListNode head, int x) {
        // 用2个哑巴节点 模拟2个链表
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode smallMove = small;
        ListNode largeMove = large;
        while(head != null) {
            // 小于x的节点， 放入small链表中。
            if(head.val < x) {
                smallMove.next = head;
                smallMove = smallMove.next;
            }else{
                // >= x的节点， 放入large链表中。
                largeMove.next = head;
                largeMove = largeMove.next;
            }
            head = head.next;
        }
        // 需要置空的原因： 防止放入large链表的节点 含有 next的引用。 所以这里要断开。
        largeMove.next = null;
        // small节点的next 给到 large的首节点即可。
        smallMove.next = large.next;
        return small.next;
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
