package com.myproj.app.algorithm.位运算;

/**
 * @author shenxie
 * @date 2023/12/30
 */
public class 位1的个数 {

    public static void main(String[] args) {
//        System.out.println(hammingWeight(00000000000000000000000000001011));
        System.out.println(hammingWeightV2(00000000000000000000000000001011));
    }

    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    /**
     * 错误解法：
     * 虽然输入是32位二进制字符串， 但是入参类型是int， 会自动转化为整数。
     * 所以直接计算1的个数： 是不行的
     */
    public static int hammingWeightV2(int n) {
        int ans = 0;
        String num = String.valueOf(n);
        for(int i = 0; i< num.length(); i++) {
            if(num.charAt(i) == 1) {
                ans ++;
            }
        }
        return ans;
    }
}
