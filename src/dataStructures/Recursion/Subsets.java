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
 *
 are all the elements unique? yes

 one approach is to start from the first element in the array

 recursive approach to find all the subsets starting from an index i till end of the array

 computation will result in a recursion tree where all the leaf nodes are the subsets of the main array

 DFS based tree

 backtrack the temp list between the recusion calls and remove the elements added in the end
 because when the recursive call goes back to the top level node the temp list must represent the values that belong to that node only.

 Time O(2^(n+1)) n is #size of array
 Space O(2^n)
 *
 *
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
        backtrack(nums, 0, ls, result);
        bfs(nums, result);

        return new ArrayList<>(result);
    }

    private void backtrack(int[] nums, int index, List<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for(int i=index; i<nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums, i+1, list, result);
            list.remove(list.size() -1);
        }
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

    /*
    While iterating through all numbers, for each new number, we can either pick it or not pick it
1, if pick, just add current number to every existing subset.
2, if not pick, just leave all existing subsets as they are.
We just combine both into our result.

For example, {1,2,3} intially we have an emtpy set as result [ [ ] ]
Considering 1, if not use it, still [ ], if use 1, add it to [ ], so we have [1] now
Combine them, now we have [ [ ], [1] ] as all possible subset

Next considering 2, if not use it, we still have [ [ ], [1] ], if use 2, just add 2 to each previous subset, we have [2], [1,2]
Combine them, now we have [ [ ], [1], [2], [1,2] ]

Next considering 3, if not use it, we still have [ [ ], [1], [2], [1,2] ], if use 3, just add 3 to each previous subset, we have [ [3], [1,3], [2,3], [1,2,3] ]
Combine them, now we have [ [ ], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3] ]
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int n : nums){
            int size = result.size();
            for(int i=0; i<size; i++){
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
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
