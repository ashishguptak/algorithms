/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/subsets-ii/description/
 * @author ashish gupta (akonda@expedia.com)
 */
public class Subsets {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> result = new HashSet<>();
        List<Integer> ls = new ArrayList<>();

        recurse(result, ls, nums, 0);
        return new ArrayList<>(result);
    }

    public void recurse(Set<List<Integer>> result, List<Integer> ls, int[] nums, int i) {
        if(i == nums.length) {
            result.add(ls);
            return;
        }

        List<Integer> include = new ArrayList<>(ls);
        include.add(nums[i]);
        recurse(result, ls, nums, i+1);
        recurse(result, include, nums, i+1);
    }
}
