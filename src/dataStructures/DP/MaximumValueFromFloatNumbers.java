/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

import java.util.Map;

/**
 Given a list of float numbers, insert “+”, “-”, “*” or “/” between each consecutive pair of numbers to find the maximum value you can get. For simplicity, assume that all operators are of equal precedence order and evaluation happens from left to right.

 Example:
 (1, 12, 3) -> 1 + 12 * 3  = 39

 float getMaxNumber(float[] nums)...

 A brute-force solution (generating all possible numbers) would be a good start (and a good coding exercise). Once it’s running, we can then discuss and possibly implement some optimizations.

 https://pastebin.com/HHgikx3s

 * @author ashish gupta (akonda@expedia.com)
 */
public class MaximumValueFromFloatNumbers {

    private float maxValue = Integer.MIN_VALUE;

    // O(4^n-1) soln
    private void findMaxRecurse2(float[] nums, int index, float pathValue) {
        if(index == nums.length) {
            maxValue= Math.max(maxValue, pathValue);
            return;
        }

        findMaxRecurse2(nums, index+1, pathValue+nums[index]);
        findMaxRecurse2(nums, index+1, pathValue-nums[index]);
        if(nums[index] != 0) findMaxRecurse2(nums, index+1, pathValue/nums[index]);
        findMaxRecurse2(nums, index+1, pathValue*nums[index]);
    }

    //TODO: solve the top down approach
    private void findMaxTopDown(float[] nums, int index, float pathValue, Map<String, Integer> map) {
        if(index == nums.length) {
            maxValue = Math.max(maxValue, pathValue);
            return;
        }
    }

    private void findMaxRecurse(float[] nums, char[] operators, int index, float pathValue) {
        if(index == nums.length) {
            maxValue= Math.max(maxValue, pathValue);
            return;
        }
        for(char ch: operators) {
            float curr = pathValue;
            if(ch == '+')
                curr += nums[index];
            else if (ch == '-')
                curr -= nums[index];
            else if (ch == '*')
                curr *= nums[index];
            else if (nums[index] != 0)
                curr /= nums[index];
            findMaxRecurse(nums, operators, index+1, curr);
        }
    }


    //for positive nos only, Greedy soln
    private float findMaxBottomUp(float[] nums) {
        float[] dp = new float[nums.length];
        dp[0] = nums[0];

        for(int i=1; i< nums.length; i++) {
            dp[i] =  Math.max(dp[i-1] + nums[i], dp[i-1] - nums[i]);
            dp[i] =  Math.max(dp[i-1]*nums[i], dp[i]);
            if(nums[i] != 0) dp[i] =  Math.max(dp[i-1]/nums[i], dp[i]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        MaximumValueFromFloatNumbers mf = new MaximumValueFromFloatNumbers();
        float[] nums = {0, 1, 12};
        char[] operators = new char[]{'+', '-', '*', '/'};
        mf.findMaxRecurse2(nums,1, nums[0]);
        System.out.println("new algo "  + mf.maxValue);
        mf.findMaxRecurse(nums,operators,1, nums[0]);
        System.out.println(mf.maxValue);
        System.out.println(mf.findMaxBottomUp(nums));
        //mf.findMaxTopDown(nums, );
        System.out.println(mf.maxValue);
    }

    /**
     *
     *
     *  (1,2 ,3)
     *    ->  1+2        1-2         1*2      1/2
     *             2+3         2+3      2+3      2+3
     *             2-3         2-3      2-3      2-3
     *             2*3         2*3      2*3      2*3
     *             2/3         2/3      2/3      2/3
     *      many repeated computations
     *      sub problems
     *      relate sub problems by memoizing and reusing the earlier computations
     *      O(4*n) recursive call new computations - top down approach
     *
     */
}
