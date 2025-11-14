package com.myproj.app.algorithm.数组;

/**
 * 给你两个 版本号字符串 version1 和 version2 ，请你比较它们。版本号由被点 '.' 分开的修订号组成。修订号的值 是它 转换为整数 并忽略前导零。
 * 比较版本号时，请按 从左到右的顺序 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 0。
 * 返回规则如下：
 *     如果 version1 < version2 返回 -1，
 *     如果 version1 > version2 返回 1，
 *     除此之外返回 0。
 *
 * 示例 1：
 * 输入：version1 = "1.2", version2 = "1.10"
 * 输出：-1
 *
 * 解释：
 * version1 的第二个修订号为 "2"，version2 的第二个修订号为 "10"：2 < 10，所以 version1 < version2。
 *
 * 示例 2：
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 *
 * 解释：
 * 忽略前导零，"01" 和 "001" 都代表相同的整数 "1"。
 *
 * 示例 3：
 * 输入：version1 = "1.0", version2 = "1.0.0.0"
 * 输出：0
 *
 * 解释：
 * version1 有更少的修订号，每个缺失的修订号按 "0" 处理。
 *
 *
 *      思路：
 *          -
 *
 * @author shenxie
 * @date 2025/11/14
 */
public class 比较版本号 {

    public static void main(String[] args) {
        System.out.println(compareVersion("1.0", "1.0.0.0"));
    }

    public static int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");

        for(int i = 0 ; i<s1.length || i < s2.length; i++) {
            int x = 0 , y = 0 ;
            if(i < s1.length){
                x = Integer.parseInt(s1[i]);
            }
            if(i < s2.length) {
                y = Integer.parseInt(s2[i]);
            }

            if(x > y) {
                return 1;
            }
            if(x < y) {
                return -1;
            }
        }

        return 0;

    }
}
