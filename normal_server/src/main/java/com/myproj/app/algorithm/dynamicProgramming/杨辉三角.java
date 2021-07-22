package com.myproj.app.algorithm.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shenxie
 * @date 2021/7/20
 */
public class 杨辉三角 {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    /**
     * 注意点：
     * 1.形成杨辉三角的外形： 即为第i层共有j个元素, 且j = i;
     * 2.每个数是它左上方和右上方的数的和
     * @param numRows 共多少行
     * @return 杨辉三角的元素的数组
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] nums = new int[numRows][numRows];
        for(int i= 0; i< numRows ; i++){
            List<Integer> sub = new ArrayList<>();
            for(int j = 0 ; j<=i; j++){
                if(j==0 || i == j){
                    nums[i][j] = 1;
                }else{
                    nums[i][j] = nums[i-1][j-1] + nums[i-1][j];
                }
                sub.add(nums[i][j]);
            }
            result.add(sub);
        }
        return result;
    }
}
