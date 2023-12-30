package com.myproj.app.algorithm.位运算;

import java.util.Arrays;

/**
 * 题目：
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * 示例 1 ：
 * 输入：nums = [2,2,1]
 * 输出：1
 *
 * 思路：
 *      1. 方法1：排序：
 *              3种情况：
 *                  1. 唯一数字 出现在首位。
 *                  2. 唯一数字 出现在中间。
 *                  3. 唯一数字 出现在最后一位。
 *      2. 方法2：异或运算符：
 *              2.1 任何数和 000 做异或运算，结果仍然是原来的数，即 a⊕0=a
 *              2.2 任何数和其自身做异或运算，结果是 000，即 a⊕a=0
 *              2.3 异或运算满足交换律和结合律。
 *
 *
 * @author shenxie
 * @date 2023/12/30
 */
public class 只出现一次的数字 {

    public static void main(String[] args) {
//        System.out.println(singleNumber(new int[]{2,2,1}));
        System.out.println(singleNumberV2(new int[]{4,1,2,1,2}));
    }

    /**
     * 方法2：异或运算符
     */
    public static int singleNumberV2(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    /**
     * 方法1： 排序
     */
    public static int singleNumber(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        int times = 1;

        for(int i = 1 ; i< nums.length; i++) {
            // 相邻2个数相同时， times + 1, 否则-1；
            if(nums[i-1] == nums[i]){
                times ++;
            }else{
                times--;
            }
            // 一旦times =0, 意味连续2个数 / 3个数都不同，
            // 2个数的情况：
            //  即为第一个数字就是唯一的。
            // 3个数的情况：
            //  即为唯一数在中间的情况：
            //      即为： num[i-2]时，times是2； nums[i-1]时， times是1； nums[i]时， times为0 。
            //      times为2时， 往左看： 相邻2个数相同的;
            //      times为1的时候，往左看：相邻2个数不同， 但往右看， 相邻2个数可能相同；
            //      times为0时， 往左看， 相邻2个数不同。
            //      这时： 判定出： nums[i-1]一定是唯一的
            if(times == 0) {
                return nums[i-1];
            }
            // 如果已经是最后一个数字， 且times=1， 意味着他就是唯一的。
            if(i == nums.length -1 && times == 1) {
                return nums[nums.length -1 ];
            }
        }
        return Integer.MAX_VALUE;
    }
}
