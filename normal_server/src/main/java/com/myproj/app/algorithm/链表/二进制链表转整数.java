package com.myproj.app.algorithm.链表;

import com.myproj.app.algorithm.链表.抽象类.ListNode;

/**
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * 请你返回该链表所表示数字的 十进制值 。
 * 最高位 在链表的头部。
 *
 * 示例 1：
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 *
 * 示例 2：
 * 输入：head = [0]
 * 输出：0
 *
 *
 *      思路：
 *          - 理解 二进制 && 十进制：
 *              - 从我们熟悉的十进制开始。类比把字符串（字符数组）转成十进制整数的方式，比如 [1,2,3] 转成 123：
 *
 *                  初始化答案为 0。
 *                  0×10+1=1。
 *                  1×10+2=12。
 *                  12×10+3=123。
 *
 *              - 本题是二进制，比如 1,1,0，目标是得到二进制数 110(2)。
 *                  初始化答案为 0。
 *                  0(2)×2+1=1(2)。
 *                  1(2)×2+1=11(2)。乘 2 等价于左移 1。
 *                  11(2)×2+0=110(2)
 *
 *
 * @author shenxie
 * @date 2025/11/11
 */
public class 二进制链表转整数 extends ListNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);

        System.out.println(getDecimalValue(head));
    }
    public static int getDecimalValue(ListNode head) {
        // 不管二进制 还是 十进制， 初始值都是0
        int ans = 0;
        while (head != null) {
            ans = ans * 2 + head.val;
            head = head.next;
        }
        return ans;
    }
}
