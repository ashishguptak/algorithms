/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change-2/description/
 *
 * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-java-dp-solution-with-detailed-explanation
 * https://leetcode.com/problems/coin-change-2/discuss/141076/Logical-Thinking-with-Clear-Java-Code
 *
 *
 * this is a classical knapsack problem.
 * dp[i][j] - #combinations to make up amt j using the first i types of coins
 *
 * 1) not using the ith coin, only using the i-1 coins to make up amt j, then we have dp[i-1][j] ways
 * 2) using the ith coin, since we can use unlimited same coin, we need to know how many ways to make up amt j- coins[i-1]
 * @author ashish gupta (akonda@expedia.com)
 */
public class CoinChange2 {

    /*
        this is a classic knapsack problem.
        dp[i][j] is the number of combinations to mkae up amt j using the first i types of coins
        State transition:
        1)  not using the ith coin, only using the first i-1 coins to make up the amount j, then we have dp[i-1][j] ways
        2) using the ith coin,since we can use unlimited same coin, we need to know how many ways to makae up amt j - coins[i] by using the first i (including ith coin) which is dp[i][j - coins[i]]

        dp[i][0] = 1

        time O(S*n)
     */
    public int change2(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;
        for(int i=1 ;i<coins.length; i++) {
            dp[i][0] = 1;
            for(int j=1; j<= amount; j++) {
                    dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j - coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }


    public int change(int amount, int[] coins) {
        if(amount == 0) return 1;
        Arrays.sort(coins);
        return recurse(amount, coins, 0);
    }

    private int recurse(int amount, int[] coins, int index) {
        if(amount == 0) return 1;
        if(amount < 0 || index == coins.length) return 0;

        int ways = 0;
        int maxfreq = amount/coins[index];
        for(int i=0; i<= maxfreq; i++)
            ways += recurse(amount - coins[index]*i, coins, index+1);
        return ways;
    }

    public static void main(String[] args) {
        CoinChange2 coinChange = new CoinChange2();
        int[] coins = {1,2,5};
        System.out.println("no of ways is " + coinChange.change( 5, coins));
    }

}
