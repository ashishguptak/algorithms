/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

/**
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 * https://leetcode.com/problems/maximum-product-subarray/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MaximumSumAndProductSubArray {

    public int maxSubArray(int[] nums) {
        int sum=0, maxSum=Integer.MIN_VALUE;

        for (int num : nums) {
            sum += num;
            maxSum = Math.max(sum, maxSum);
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }
}
