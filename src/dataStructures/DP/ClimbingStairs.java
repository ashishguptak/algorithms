/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

/**
 * https://leetcode.com/articles/climbing-stairs/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        if(n < 2) return n;

        int[] dp = new int[n+1];
        dp[1] = 1; dp[2] =2;
        for(int i=3; i<=n; i++)
            dp[i] = dp[i-1] + dp[i-2];
        return dp[n];

        //return findWays(n-1) + findWays(n-2);
    }

    private int findWays(int n) {
        if(n < 0) return 0;
        if(n == 0) return 1;
        return findWays(n-1) + findWays(n-2);
    }
}
