/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, result, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] candidates, int target, int index, List<List<Integer>> result, List<Integer> list) {
        if (target == 0)
            result.add(new ArrayList<>(list));
        else if (target > 0) {
            for(int i=index; i<candidates.length; i++) {
                if(target < candidates[i]) break;
                list.add(candidates[i]);
                backtrack(candidates, target - candidates[i], i, result, list);
                list.remove(list.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        CombinationSum sum = new CombinationSum();
        int[] nums = {2,3,6,7};
        System.out.println(sum.combinationSum(nums, 7));
    }
}

/**
 set of candidate nos and a target
 all unique combinations in set that sum to target

 same no can be repeated multiple times

 all are +ve nos

 candidates = [2,3,6,7], target = 7,
 [
 [7],
 [2,2,3]
 ]


 candidates = [2,3,5], target = 8,

 [3, 5]
 [3, 3, 2]
 [2,2,2,2]

 is the array sorted?

 try to find all combination of nos that sum to target starting from index i=0

 2, 3, 6 ,7

 target = 7

 7 % 2 = 1

 Start with the first index and try to find if the repeated number of times target exists.
 If yes, add to result
 then two possibilities,
 consider both first and second index
 program finding target in all combinations of x and y times of those nums
 if yes add to result
 consider third wlemtn

 Got stuck at point regarding how to program multiple combinations of summing list of integers to get the target value.
 obviously for loop is not an option.

 Recursive based approach can be used to achieve this.
 how to solve with recursion?
 consider every integer in all cbinationss till it does not exceed the target value and then consider subsequent indices. Because we are generating all combinations for an integer, we mut added to a temp array and then the terminating condition for the recursive call is if target == 0 or target < 0 return
 else
 for(int i=start; i <nums.length; i++)
 list.add(nums[i])
 backtrack(result, list, nums, i, target - nums[i])
 list.remove(list.size()-1)

 DFS based target element matching and then backtracking to other values

 how to ensure duplicates are not added? Iteration order is such that moving from left to right, and since all elements are unique, we can guarantee that a particular combination from older indices are not added again.

 **/