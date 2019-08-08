/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/articles/coin-change/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class CoinChange {
    /*
    Sort the Array

Fewest coins can be achieved by starting with the largest element in array and checking for all combinations of other coins till target value is reached such that global min is minimized.

a trivial solution is to enumerate all subsets of coin freq that satisyfy the constraints, comput etheri sums and return the min among them.

More like a Recursive function to find the target value
     - iteraively check for lower denomination values for every combination of higher order denominations values till minCoins is achieved or target < 0 to end recursion

while recusively trying for other combinations of lower target amt, if we find the
     */

    //Top down approach
    public int coinChange(int[] coins, int amount) {
        if(coins.length < 1) return -1;
        if(amount == 0) return 0;
        Arrays.sort(coins);
        System.out.println(Arrays.toString(coins));
        Map<Integer, Integer> map = new HashMap<>();
        return recurse(coins, amount, map);
    }

    private int recurse(int[] coins, int amt, Map<Integer, Integer> map) {
        if(amt < 0) return -1;
        if(amt == 0) return 0;
        if(map.containsKey(amt))
            return map.get(amt);

        int minCount = Integer.MAX_VALUE;

        for(int coin: coins) {
            if(coin > amt) continue;
            int count = recurse(coins, amt - coin, map);
            if(count == -1)  continue;
                minCount = Math.min(minCount, count);
                //System.out.println( "coin:" + coin + " amt is " + amt + " count:"+ count);
        }
        minCount = minCount == Integer.MAX_VALUE ? -1 : minCount+1;
        map.put(amt, minCount);
        return minCount;
    }

    //bottom-up solution
    public int coinChange2(int[] coins, int amount) {
        if(coins.length == 0) return -1;

        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int i=1; i<= amount; i++)
            for(int coin : coins)
                if( i - coin >= 0)
                    dp[i] = Math.min(1 + dp[i-coin], dp[i]);

        return dp[amount] == amount +1 ? -1 : dp[amount];
    }

    // time O(S^n)
    private int coinChange4(int index, int[] coins, int amt) {
        if(amt == 0) return 0;

        if( index >= coins.length || amt > 0)
            return -1;

        int maxVal = amt/coins[index];
        int minCost = Integer.MAX_VALUE;
        for(int x=0; x <= maxVal; x++) {
            if(amt >= x*coins[index]) continue;
            int res = coinChange4(index+1, coins, amt - x*coins[index]);
            if(res != -1)
                minCost = Math.min(minCost, res+x);
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    /**
     * Top down dynamic programing. Using map to store intermediate results.
     * Returns Integer.MAX_VALUE if total cannot be formed with given coins
     *
     * F(S) min coins needed to make change for amt S using coin denominations [c0,..cn-1]
     *
     * The optimal solution ca be constructed from optimal solutions of its subproblems
     * how to split the porlbme into subproblems?
     * F(S) = F(S-C) +1
     *
     *  F(S) = min (0, n-1) F(S- Ci) +1 subject to
     */
    public int minimumCoinTopDown(int total, int coins[], Map<Integer, Integer> map) {

        //if total is 0 then there is nothing to do. return 0.
        if ( total == 0 ) {
            return 0;
        }

        //if map contains the result means we calculated it before. Lets return that value.
        if ( map.containsKey(total) ) {
            return map.get(total);
        }

        //iterate through all coins and see which one gives best result.
        int min = Integer.MAX_VALUE;
        for ( int i=0; i < coins.length; i++ ) {
            //if value of coin is greater than total we are looking for just continue.
            if( coins[i] > total ) {
                continue;
            }
            //recurse with total - coins[i] as new total
            int val = minimumCoinTopDown(total - coins[i], coins, map);

            //if val we get from picking coins[i] as first coin for current total is less
            // than value found so far make it minimum.
            if( val < min ) {
                min = val;
            }
        }

        //if min is MAX_VAL dont change it. Just result it as is. Otherwise add 1 to it.
        min =  (min == Integer.MAX_VALUE ? min : min + 1);

        //memoize the minimum for current total.
        map.put(total, min);
        return min;
    }

    public int coinChange5(int[] coins, int amount) {
        if(coins.length == 0 || amount <= 0) return -1;
        Arrays.sort(coins);

        int[] amt = new int[amount+1];
        Arrays.fill(amt, amount+1);
        amt[0] = 0;
        for(int i=1; i<= amount; i++)
            for(int j=0; j< coins.length; j++)
                if(i >= coins[j])
                    amt[i] = Math.min(amt[i], amt[i - coins[j]] + 1);

        return amt[amount] > amount ? -1 : amt[amount];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1,2,5};
        System.out.println("min coin is " + coinChange.coinChange5(coins, 11));
    }
}
