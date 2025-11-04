package com.myproj.app.algorithm.链表.抽象类;

/**
 * @author shenxie
 * @date 2025/11/3
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
