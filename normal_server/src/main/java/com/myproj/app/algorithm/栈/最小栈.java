package com.myproj.app.algorithm.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目：
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 *     MinStack() 初始化堆栈对象。
 *     void push(int val) 将元素val推入堆栈。
 *     void pop() 删除堆栈顶部的元素。
 *     int top() 获取堆栈顶部的元素。
 *     int getMin() 获取堆栈中的最小元素。
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 答案：
 * 1. 用2个栈来维护即可：
 *      1.1 第一个栈： FILO.
 *      1.2 第二个栈： 维护最小值的栈：即为当前值和 栈顶元素比较， 放入较小的元素即可。 这样栈顶： 就是最小的。
 *
 * @author shenxie
 * @date 2023/12/29
 */
public class 最小栈 {
    public static void main(String[] args) {
        Deque<Integer> minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
        minStack.push(Math.min(minStack.peek(), 5));
        minStack.push(Math.min(minStack.peek(), 3));
        minStack.push(Math.min(minStack.peek(), 6));
        minStack.push(Math.min(minStack.peek(), 7));
        System.out.println(minStack);
    }

    class MinStack {
        private Deque<Integer> stack;
        private Deque<Integer> minStack;

        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            minStack.push(Math.min(minStack.peek(), val));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

}
