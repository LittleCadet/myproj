package com.myproj.app.algorithm.链表;

import com.myproj.app.algorithm.链表.抽象类.ListNode;

/**
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 *      思路：
 *  *          {@link 移除重复节点}：删除重复的元素【不包括本元素】： 站在前一个节点执行删除动作。 因为链表未排序。
 *  *          {@link 删除排序链表中的重复元素}：删除重复元素【不包含本元素】： 站在当前节点执行删除动作。 因为链表排序。
 *  *          {@link 删除排序链表中的重复元素II}： 所有重复元素【包括本元素】：类似于{@link 移除链表元素}： 站在前一个节点执行删除动作。 因为链表未排序。 与 {@link 移除重复节点}相同
 *
 *
 * @author shenxie
 * @date 2025/11/11
 */
public class 删除排序链表中的重复元素 extends ListNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        deleteDuplicates(head);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(null == head) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        while(head.next != null) {
            // 站在当前元素的角度， 删除下一个元素
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return dummy.next;
    }
}
