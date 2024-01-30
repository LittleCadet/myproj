package com.myproj.app.algorithm.链表;

/**
 * 题目：
 * 若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。
 * 假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。
 * 例如，传入节点 c（位于单向链表 a->b->c->d->e->f 中），将其删除后，剩余链表为 a->b->d->e->f
 * 示例：
 * 输入：节点 5 （位于单向链表 4->5->1->9 中）
 * 输出：不返回任何数据，从链表中删除传入的节点 5，使链表变为 4->1->9
 *
 * 思路：
 *      1. 该题的核心思想：
 *          1.1 入参是一个中间节点。
 *                  即为： 通过node.next的方式让node处于中间某一个节点， 之后作为入参传入。
 *          1.2 如何将该中间节点去除。
 *                  即为：ListNode的数据结构： 包含 val + next。
 *                  所以去除该节点， 代表： 将这2个属性 替换为新的值即可。
 *          1.3 备注：
 *              // 删除节点的2种方式：
     *         // 方法1： pre.next = cur.next;
     *         // 方法2： 见《删除中间节点》： 删除当前节点：
     *         //        node.val = node.next.val;
     *         //        node.next = node.next.next;
 * @author shenxie
 * @date 2024/1/29
 */
public class 删除中间节点 {

    public static void main(String[] args) {
        ListNode node =  new ListNode(4);
        node.next = new ListNode(5);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(9);

        node = node.next;
        deleteNode(node);
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

    }
}
