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
 * https://leetcode.com/explore/interview/card/uber/290/recursion-and-backtracking/1689/discuss/122645/3ms-easiest-solution-no-backtracking-no-bit-manipulation-no-dfs-no-bullshit
 * https://leetcode.com/explore/interview/card/uber/290/recursion-and-backtracking/1689/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
 * @author ashish gupta (akonda@expedia.com)
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();

        recurse(result, ls, nums, 0);
        bfs(nums, result);

        return new ArrayList<>(result);
    }

    private void recurse(List<List<Integer>> result, List<Integer> ls, int[] nums, int i) {
        if(i == nums.length) {
            result.add(new ArrayList<>(ls));
            return;
        }

        ls.add(nums[i]);
        recurse(result, ls, nums, i+1);
        ls.remove(ls.size() -1);
        recurse(result, ls, nums, i+1);
    }

    private void bfs(int[] nums, List<List<Integer>> result) {
        result.add(new ArrayList<>());
        for(int i: nums) {
            List<List<Integer>> temp = new ArrayList<>();
            for(List<Integer> ls : result)
                temp.add(new ArrayList<>(ls));
            for(List<Integer> ls : temp)
                ls.add(i);
            result.addAll(temp);
        }
    }
}
