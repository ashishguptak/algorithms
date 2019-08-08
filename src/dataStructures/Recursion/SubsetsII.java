/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, result, new ArrayList<>());

        return result;
    }

    private void backtrack(int[] nums, int index, List<List<Integer>> result, List<Integer> temp) {
        result.add(new ArrayList<>(temp));
        for(int i=index; i<nums.length; i++) {
            if(i > index && nums[i] == nums[i-1]) continue;  //skip duplicates
            temp.add(nums[i]);
            backtrack(nums, i+1, result, temp);
            temp.remove(temp.size() -1);
        }
    }
}
