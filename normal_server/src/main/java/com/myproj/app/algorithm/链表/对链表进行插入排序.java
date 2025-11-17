package com.myproj.app.algorithm.链表;

/**
 * ‘给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 * 插入排序 算法的步骤:
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 * 对链表进行插入排序。
 * <p>
 * 示例 1：
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 * <p>
 * 示例 2：
 * 输入: head = [-1,5,3,4,0]
 * 输出: [-1,0,3,4,5]
 * <p>
 *
 *      思路：
 *          - 本质是玩转3个节点：prev + lastSorted + curr:
 *              - prev: 待插入的前一个节点
 *              - lastSorted: 链表已排序部分的最后一个节点
 *              - curr: 待插入的节点。
 *          - 若 lastSorted.val <= curr.val，说明 curr 应该位于 lastSorted 之后，将 lastSorted 后移一位，curr 变成新的 lastSorted。
 *              否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入：
 *
 *              让插入顺序：prev + lastSorted + curr 变成 prev + curr + lastSorted
 *              lastSorted.next = curr.next
 *              curr.next = prev.next
 *              prev.next = curr
 *
 *              注意： 插入顺序的调整：与 {@link 反转链表} 不同：
 *                  - 本题： 强调 插入顺序的调整: 对于一个插入元素而言： 是一次性调整。
 *                  - 后者： 强调 链表反转： 对于链表而言： 是递归调整
 *
 * @author shenxie
 * @date 2025/11/17
 */
public class 对链表进行插入排序 {

    public static void main(String[] args) {
        ListNode node = new ListNode(4);
        node.next = new ListNode(2);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(3);

        insertionSortList(node);
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // lastSorted: 代表：链表已排序部分的最后一个节点
        // curr: 代表: 当前要插入的节点
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            // 当curr较大时， 将 lastSorted 后移一位，curr 变成新的 lastSorted。
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                // 当curr较小时， 需要从头遍历链表的节点， 找到插入位置： 即为要插入位置的前一个元素：prev.next 就是curr的插入位置。
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                // 三个位置的节点【从小到大的顺序： prev + lastSorted + curr】：相互颠倒， 完成插入：
                // 插入完成的顺序： prev + curr + lastSorted
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            // 更新待插入的节点
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
