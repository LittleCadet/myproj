package com.myproj.app.algorithm.牛客.华为;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 对于给定的由可见字符和空格组成的字符串，按照下方的规则进行排序：
 * ∙按照字母表中的顺序排序（不区分大小写）；
 * ∙同一字母的大小写同时存在时，按照输入顺序排列；
 * ∙非字母字符保持原来的位置不参与排序；
 * 直接输出排序后的字符串。

 * 输出描述：
 * 输出一个字符串，代表按照规则排序后的字符串。
 * 示例1
 * 输入：BabA
 * 输出：aABb
 *
 * 示例2
 * 输入：Hello NowCoder!
 * 输出：CdeeH llNooorw!
 *
 * 思路：
 *  - 注意API:
 *      - 判定字符是否是字母： Character.isLetter(ch);
 *      - 字符之间的比较大小： Character.toLowerCase(ch1) - Character.toLowerCase(ch2)
 *
 *
 * @author shenxie
 * @date 2025/10/30
 */
public class 字符串排序 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            String result = sort(line);
            System.out.println(result);
        }
    }

    private static String sort(String str) {
        List<Character> list = new ArrayList<>();
        StringBuilder append = new StringBuilder();
        int i = 0 ;

        // 将字符放在list中
        for (char ch : str.toCharArray()) {
            // 注意API: 判定字符是否是字母： Character.isLetter(ch);
            if (Character.isLetter(ch)) {
                list.add(ch);
            }
        }

        // 字符排序： 因为list的元素是逐个放入的，有序的， 所以可以保证：同一字母的大小写同时存在时，按照输入顺序排列；
        // 所以 compare只需比较小写的char的大小即可
        list.sort(new Comparator<Character>() {
            @Override
            public int compare(Character ch1, Character ch2) {
                // 字符之间的比较大小： Character.toLowerCase(ch1) - Character.toLowerCase(ch2)
                return Character.toLowerCase(ch1) - Character.toLowerCase(ch2);
            }
        });

        // 将字符串按顺序放入 stringBuilder中 即可
        for (char ch : str.toCharArray()) {
            // 是字符时：从list中获取
            if (Character.isLetter(ch)) {
                append.append(list.get((i++)));
            } else {
                // 非字符时：直接放入即可
                append.append(ch);
            }
        }
        return append.toString();
    }
}
