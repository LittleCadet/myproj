package com.myproj.app.algorithm.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 题目：
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * 注意：
 *     有效的算符为 '+'、'-'、'*' 和 '/' 。
 *     每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 *     两个整数之间的除法总是 向零截断 。
 *     表达式中不含除零运算。
 *     输入是一个根据逆波兰表示法表示的算术表达式。
 *     答案及所有中间计算结果可以用 32 位 整数表示。
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *
 * 思路：
 *      1. 逆波兰表达式定义： 将运算符【即为：+,-*,/】写在操作符【即为数字】的后面。 又称为“后缀表达式”。
 *                          而我们看见的通俗易懂的表达式： 是“中缀表达式”。
 *      2. 对本题而言： 栈：   2.1 将操作符【即为数字】压入栈中。
 *                          2.2 每次碰到一个运算符时， 从栈中取出2个数字， 第一个数字是：运算符右边的数， 第二个数字是：运算符左边的数。
 *                          2.3 将运算结果，再次放入栈中，重复2.2 和 2.3的步骤。 直到tokens循环完成。
 *                          2.4 最后栈中取出唯一的那个元素： 即可
 * @author shenxie
 * @date 2023/12/29
 */
public class 逆波兰表达式求值 {
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
    }

    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for(int i =0; i<tokens.length; i++) {
            if(isNumber(tokens[i])){
                stack.push(Integer.parseInt(tokens[i]));
            }else{
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch(tokens[i]){
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                }

            }
        }
        return stack.pop();
    }

    private static boolean isNumber(String str){
        return !("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str));
    }
}
