/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

/**
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if(nums.length < 2) return nums.length;
        int max =1;

        int[] len = new int[nums.length];

        for(int i=0; i<nums.length; i++) {
            len[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i])
                    len[i] = Math.max(len[i], len[j]+1);
            }
        }

        for(int i=0; i< nums.length; i++)
            if(len[i] > max)
                max = len[i];
        return max;
    }


    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        int[] len =  new int[n], cnt = new int[n];
        for(int i = 0; i<n; i++){
            len[i] = cnt[i] = 1;
            for(int j = 0; j <i ; j++){
                if(nums[i] > nums[j]){
                    if(len[i] == len[j] + 1)cnt[i] += cnt[j];
                    if(len[i] < len[j] + 1){
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if(max_len == len[i])res += cnt[i];
            if(max_len < len[i]){
                max_len = len[i];
                res = cnt[i];
            }
        }
        return res;
    }

}
