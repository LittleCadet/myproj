package com.myproj.app.algorithm.数组;

/**
 * 题目：
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * 思路：
 *      核心思想： 因为获取最长公共前缀， 所以如果存在， 那么第一个元素的前N个字符一定是最长公共前缀，之后配合String.startwith()即可判定。
 *                  当不满组 String.startwith()时， 返回拼接的字符串： tmp.substring(0, tmp.length() -1) 即可
 *      困难点： 如何用变量表示所有元素。
 *      解决方式： 直接使用数组下标的方式 获取 各个元素。
 *
 *      备注：
 *      substring()： 是左闭右开 区间。
 *
 *
 * @author shenxie
 * @date 2023/12/25
 */
public class 最长公共前缀 {

    public String longestCommonPrefix(String[] strs) {
        String str = strs[0];
        char[] chars = str.toCharArray();
        String tmp = "";
        for(int i = 0; i < chars.length; i++) {
            // 逐个拼接字符
            tmp = tmp + chars[i] + "";
            for(int j = 0; j< strs.length; j++){
                String element = strs[j];
                // 逐个元素匹配 拼接后的字符。 当不满足时， 返回即可
                if( ! element.startsWith(tmp)) {
                    return tmp.substring(0, tmp.length() -1);
                }
            }
        }
        return tmp;
    }
}
