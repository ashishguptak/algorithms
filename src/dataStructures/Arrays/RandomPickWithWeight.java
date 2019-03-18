/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class RandomPickWithWeight {
    private int[] arr;
    private Random rand;

    public RandomPickWithWeight(int[] w) {
        arr = w;
        rand = new Random();
        int sum = 0;
        for(int i=0 ; i < arr.length; i++) {
            sum += arr[i];
            arr[i] = sum;
        }
    }

    public int pickIndex() {
        int target = 1 + rand.nextInt(arr[arr.length-1]);
        int start = 0, end = arr.length-1;
        while(start <= end) {
            int mid = start + (end-start)/2;
            if(arr[mid] == target) return mid;
            else if (arr[mid] > target)
                end = mid -1;
            else
                start = mid +1;
        }
        return start;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

/**
 1 3 2
 0 1 2
 1  2-4 .  5-6
 arr = {0, 1,1,1, 2, 2}
 x = 1 + Random.nextInt(arr.size());
 return arr[x];

 1 4 6
 0 1 2
 **/