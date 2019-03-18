/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/566/discuss/20193/DP-solution-and-some-thoughts
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class LargestSumContiguousSubArray {

    public int maxSubArray(int[] nums) {
        if(nums.length < 1) return -1;
        int max=nums[0], currMax=nums[0];
        for(int i=1; i< nums.length; i++) {
            currMax = Math.max(currMax + nums[i], nums[i]);
            max = Math.max(max, currMax);
        }
        return max;
    }
}
