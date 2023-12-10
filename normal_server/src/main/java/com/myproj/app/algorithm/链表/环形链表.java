package com.myproj.app.algorithm.链表;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 思路：
 * 1. 哈希表： HashSet：
 * 2. 是否存在环的关键： 通过ListNode.next 判定该元素是否再次通过，如果通过， 则含有环， 否则 不包含环。
 *
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 环形链表 {

    public static void main(String[] args) {
        hasCycle(null);
    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (null != head) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
