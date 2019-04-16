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

    /**
     * Top down dynamic programing. Using map to store intermediate results.
     * Returns Integer.MAX_VALUE if total cannot be formed with given coins
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

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {186,419,83,408};
        System.out.println("min coin is " + coinChange.coinChange(coins, 6249));
    }
}
