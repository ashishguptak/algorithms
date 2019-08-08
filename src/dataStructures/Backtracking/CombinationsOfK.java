/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class CombinationsOfK {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if( n < 1 || k < 1) return result;

        backtrack(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int n, int k, int index, List<Integer> list, List<List<Integer>> result ) {
        if(k == 0) {
            //System.out.println(list);
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i=index; i<=n; i++) {
            list.add(i);
            backtrack(n, k-1, i+1, list, result);
            list.remove(list.size() -1);
        }
    }
}

/**

 1...n
 1 2 3 4
 k

 1,2
 1, 3
 1 4
 2 3
 2 4
 3 4


 start with the first index i=0, at every index, we have two choices

 1) include the elemnt index+1
 2) not include the element index+1

 as we iterate rest of elements, cehck for #elements added in resultant array. If its k, add to result. Else iterate

 Recursive coding.
 DFS based execution

 backtrack and then proceed in program execution


 time
 space O(k)

 **/