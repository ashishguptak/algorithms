/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * Recursive solutions do not work well for large input sizes
 *
 * 1) Optimal substructure - if the optimal solution of the given problem can be obtained using the optimal solutions of the sub-problems
 * 2) Repeated subproblems - when solutions of same subproblems are needed again
 * Two ways to store the subproblem solutions
 * Top Down ( Memoization)
 * Bottom up
 *
 * check the data limits or input range for question before solving
 *
 *  Soln approach-
 *  For any cell, we can find out the # of ways to reach it, by making use of the number of ways to reach the cell directly above it and to the cell to left of it.
 *  this is because there are only two directions from which the robot can come to the current cell
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class UniquePathsII {

    private int uniquePaths = 0;

    // DP soln -> O(m*n) time and space
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        /* where dp[i, j] refers to #unique paths from (0,0) to (i,j)
        */
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        if (obstacleGrid[0][0] == 1) return 0;

        dp[0][0] = 1;
        for (int i = 1; i < obstacleGrid.length; i++)
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];

        for (int j = 1; j < obstacleGrid[0].length; j++)
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];

        for (int i = 1; i < obstacleGrid.length; i++)
            for (int j = 1; j < obstacleGrid[0].length; j++)
                if (obstacleGrid[i][j] == 0)
                  dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }


    // DP soln -> O(m*n) time only and in-memory soln
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        /* where dp[i, j] refers to #unique paths from (0,0) to (i,j)
        */
        if (obstacleGrid[0][0] == 1 ||
                obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length -1] == 1) return 0;

        obstacleGrid[0][0] = 1;
        for (int i = 1; i < obstacleGrid.length; i++)
            obstacleGrid[i][0] = obstacleGrid[i][0] == 1 ? 0 : obstacleGrid[i - 1][0];

        for (int j = 1; j < obstacleGrid[0].length; j++)
            obstacleGrid[0][j] = obstacleGrid[0][j] == 1 ? 0 : obstacleGrid[0][j - 1];

        for (int i = 1; i < obstacleGrid.length; i++)
            for (int j = 1; j < obstacleGrid[0].length; j++)
                if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
                else obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];

        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }


    // O(2^n) time complexity and space is O(m+n) for recursive stack
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return uniquePaths;
        if(obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1] == 1)
            return uniquePaths;

        recursePaths(obstacleGrid, 0, 0);
        return uniquePaths;
    }

    private void recursePaths(int[][] obstacleGrid, int i, int j) {
        if( i > obstacleGrid.length -1 ||
                j > obstacleGrid[0].length - 1) return;

        if(i ==  obstacleGrid.length -1 && j == obstacleGrid[0].length-1) {
            uniquePaths+=1;
            return;
        }

        if(obstacleGrid[i][j] == 1) return;

        recursePaths(obstacleGrid, i+1, j);
        recursePaths(obstacleGrid, i, j+1);
    }
}
