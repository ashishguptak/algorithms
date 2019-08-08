/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.matrix;

/**
 *
 *https://leetcode.com/problems/search-a-2d-matrix/description/
 *https://leetcode.com/problems/search-a-2d-matrix/discuss/26220/Don't-treat-it-as-a-2D-matrix-just-treat-it-as-a-sorted-list
 * O(log(m) + log(n)) is same as O(log(mn))
 * @author ashish gupta (akonda@expedia.com)
 */
public class Search2dMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length < 1 || matrix[0].length < 1) return false;
        if( target < matrix[0][0] || target > matrix[matrix.length-1][matrix[0].length-1])
            return false;

        int rowIdx = bsRow(matrix, target, 0, matrix.length-1);
        System.out.println(rowIdx);
        if(rowIdx == -1) return false;

        return bsCol(matrix, target, rowIdx, 0, matrix[0].length-1);
    }

    public boolean bsCol(int[][] matrix, int target, int row, int start, int end) {
        if( end < start) return false;

        int mid = start +(end-start)/2;

        if(matrix[row][mid] == target) return true;
        else if (matrix[row][mid] < target)
            return bsCol(matrix, target, row, mid+1, end);
        else
            return bsCol(matrix, target, row, start, mid-1);
    }

    public int bsRow(int[][] matrix, int target, int start, int end) {
        if( end < start) return -1;

        int mid = start +(end-start)/2;

        if( matrix[mid][0] <= target
                && matrix[mid][matrix[0].length-1] >= target)
            return mid;
        else if (matrix[mid][0] > target)
            return bsRow(matrix, target, start, mid-1);
        else
            return bsRow(matrix, target, mid+1, end);
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0, rows = matrix.length, cols = matrix[0].length;
        int end = rows * cols - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid / cols][mid % cols] == target) {
                return true;
            }
            if (matrix[mid / cols][mid % cols] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
