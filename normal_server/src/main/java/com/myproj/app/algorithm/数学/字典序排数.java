package com.myproj.app.algorithm.数学;

import java.util.ArrayList;
import java.util.List;

/**
 *给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 * 示例 1：
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：[1,2]
 *
 *
 *      思路：
 *          - 字典序排数： 顾名思义：
 *              会有低位 向 高位进阶的过程，之后 number ++ ,
 *              但不会一直这样， 因为对于个位数9 和 number + 1>n 的要特殊处理：不然对于199 ，加1直接变为200的场景， 是不满足字典排序的。
 *              所以会有 高位 向 低位转化的过程：
 *
 *
 * @author shenxie
 * @date 2025/11/19
 */
public class 字典序排数 {

    public static void main(String[] args) {
        System.out.println(lexicalOrder(11));
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        // number必须从1开始， 因为 1 * 任何数= 任何数。
        int number = 1;
        for (int i = 0; i < n; i++) {
            ret.add(number);
            // 低位 向 高位转换
            // 使得 number从低位向高位检索： eg: n为11时， 1,10,11,2....会有个位数1上升到十位数1， 之后开始高位检索，
            if (number * 10 <= n) {
                number *= 10;
            } else {
                // 高位检索：即为 + 1处理。
                // number * 10的搜索已完成的标识： number % 10 == 9 || number + 1 > n：
                //  - number % 10 == 9 的原因： 个位数的范围: [0,9]。 当到9时，代表下一次就要进位了：eg: 199 => 200, 这显然不符合字典序排序，因为假如 n = 200， 应该是 1,10,11...199,20,21...200,30
                //  - number + 1 > n的原因：代表当前数超过了上限n, eg: n=11时， 应该是1，10，11，2，,3。。。。 所以触碰到上限n时，应该重新开始最高位的字典检索。
                // eg: n = 11时： 1，10,11,2等等。
                while (number % 10 == 9 || number + 1 > n) {
                    // 高位 向 低位转换
                    number /= 10;
                }
                number++;
            }
        }
        return ret;
    }
}
