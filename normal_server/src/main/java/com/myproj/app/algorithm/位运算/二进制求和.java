package com.myproj.app.algorithm.位运算;

/**
 * 思路：
 *      方法1【不适用】： java的高精度运算。
 *      方法2【不好理解】：牵扯到ASCII, [-'0'] 和 [+'0']： 不好理解。
 *      方法3【推荐】： 自制getInt()函数。 全程用int (十进制运算)， 最后用StringBuffer.reverse()即可。
 * @author shenxie
 * @date 2023/12/30
 */
public class 二进制求和 {

    public static void main(String[] args) {
//        System.out.println(addBinaryV2("0", "0"));
        System.out.println(addBinaryV3("100", "110010"));
    }

    /**
     * 方法1： java的高精度运算：将二进制 转换为十进制， 执行逻辑运算， 最后将十进制 转换为 二进制。
     * 如果字符串超过 333333 位，不能转化为 Integer
     * 如果字符串超过 656565 位，不能转化为 Long
     * 如果字符串超过 500000001500000001500000001 位，不能转化为 BigInteger
     */
    public static String addBinary(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    public static String addBinaryV2(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            // a.charAt(a.length() - 1 - i) - '0': 二进制数与二进制数之间的运算
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            // carry % 2 + '0': 将二进制转化为十进制。
            // (char) (carry % 2 + '0') : 将十进制转换为二进制。
            ans.append((char) (carry % 2 + '0'));
            // 二进制： 逢2进1
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }


    /**
     *  如果[-'0'] ASCII码看不懂的话，可以写个getInt小函数：
     *  这样： 从头到尾 用int计算： 会方便很多。
     */
    public static String addBinaryV3(String a, String b) {
        StringBuffer buffer = new StringBuffer();
        int n = Math.max(a.length(),  b.length());
        int carry = 0;
        for(int i = 0; i< n; i++) {
            int ia = i < a.length()? getInt(a.charAt(a.length() - 1-i)) : 0;
            int ib = i < b.length()? getInt(b.charAt(b.length() - 1-i)) : 0;
            // 每一位相加
            carry += ia + ib;
            buffer.append(carry % 2);
            carry /=2;
        }
        // 逢二进一
        if(carry > 0) {
            buffer.append(1);
        }
        return buffer.reverse().toString();
    }

    private static int getInt(char num){
        return num == '0' ? 0: 1;
    }
}
