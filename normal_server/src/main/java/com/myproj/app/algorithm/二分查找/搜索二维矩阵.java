package com.myproj.app.algorithm.二分查找;

/**
 * 题目：
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *     每行中的整数从左到右按非严格递增顺序排列。
 *     每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * 示例1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 思路：
 * 1. 方法1： n次二分查找：
 *              即为： for循环二维数组， 则matrix[i]为一维数组， 最终在一维数组中： 执行二分查找。
 * 2. 方法2：1次二分查找：
 *              即为：
 *                  1. left=0, right = high * width - 1,
 *                  2. 核心子结构： target > matrix[mid / width][mid % width]。
     *                  2.1. 用mid 对 width取商，得到二维数组的高度，
     *                  2.2. 用mid 对 width取模，得到二维数组的宽度。
 *
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 搜索二维矩阵 {

    public static void main(String[] args) {
//        System.out.println(searchMatrixV1(new int[][]{{1,3,5,7},{10,11,16,20}, {23,30,34,60}}, 3));
        System.out.println(searchMatrixV2(new int[][]{{1,3,5,7},{10,11,16,20}, {23,30,34,60}}, 3));
//        System.out.println(searchMatrixV2(new int[][]{{1,1}},2));

    }

    /**
     * n次二分查找： n=matrix.length;
     */
    public static boolean searchMatrixV1(int[][] matrix, int target) {
        for(int i = 0; i< matrix.length; i++) {
            int[] nums = matrix[i];
            int left = 0;
            int right = nums.length -1 ;
            while(left <= right) {
                int mid = (left + right) / 2;
                if(target > nums[mid]) {
                    left = mid + 1;
                }else if (target == nums[mid]){
                    return true;
                }else{
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 一次二分查找。
     */
    public static boolean searchMatrixV2(int[][] matrix, int target) {
        int high = matrix.length;
        int width = matrix[0].length;
        int right = high * width - 1;
        int left = 0;
        while(left <= right) {
            // + left的原因： 避免出现： right = left = 1, 导致mid = 0, 且属于target > matrix[mid / width][mid % width]的情况， 则left = 1,
            // 最终导致： 死循环。
            int mid = (right - left) / 2 + left;
            if(target > matrix[mid / width][mid % width]){
                left = mid + 1;
            }else if(target == matrix[mid / width][mid % width]){
                return true;
            }else {
                right = mid -1;
            }
        }

        return false;
    }

}
