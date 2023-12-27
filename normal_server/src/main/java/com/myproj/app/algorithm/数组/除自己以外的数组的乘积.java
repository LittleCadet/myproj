package com.myproj.app.algorithm.数组;

import java.util.Arrays;

/**
 * 题目：
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 思路：
 *      前缀积 后缀积的思想：
 *          1. 除nums[i]之外的各个元素的乘积： nums[i] = L[i] * R[i];
 *          2. 前缀积：
 *              2.1 第一个元素的左边没有元素， 所以L[0] = 1;
 *              2.2 L[i] = L[i - 1] * nums[i - 1];
 *          3. 后缀积：
 *              3.1 最后一个元素的右边没有元素， 所以R[nums.length - 1] = 1;
 *              3.2 R[i] = R[i + 1] * nums[i + 1];
 *
 * @author shenxie
 * @date 2023/12/26
 */
public class 除自己以外的数组的乘积 {

    public static void main(String[] args) {
//        productExceptSelf(new int[]{1,2,3,4});
        productExceptSelfV2(new int[]{1,2,3,4});
    }

    /**
     * 维护两个变量，beforeSum表示前缀和，afterSum表示后缀和
     */
    public static int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        Arrays.fill(ans,1);
        int beforeSum=1;
        int afterSum=1;
        for(int i=0,j=n-1;i<n;i++,j--){
            ans[i]*=beforeSum;
            ans[j]*=afterSum;
            beforeSum*=nums[i];
            afterSum*=nums[j];
            System.out.println();
        }
        return ans;
    }

    /**
     * 前缀和 后缀和 的思想。
     */
    public static int[] productExceptSelfV2(int[] nums) {
        int n = nums.length;
        int[] L = new int[nums.length];
        int[] R = new int[nums.length];
        int[] ans = new int[nums.length];
        // 第一个元素的左边没有元素， 所以L[0] = 1;
        L[0] = 1;
        // 计算前缀积
        for(int i = 1; i<n ; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }
        // 最后一个元素的右边没有元素， 所以R[n-1] = 1;
        R[n-1] = 1;
        // 计算后缀积
        for(int i = n - 2; i>= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }
        // 前缀积  *  后缀积
        for(int i = 0; i< n ; i++) {
            ans[i] = L[i] * R[i];
        }
        return ans;
    }
}
