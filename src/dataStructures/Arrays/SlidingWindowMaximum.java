/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k/
 * Test cases
 * input containg neg and pos values
 * val of k is neg 0 or pos
 * val of k is larger than size of input
 * val of k is same as size of input
 *
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if( nums.length == 0) return new int[0];

        int[] list = new int[nums.length-k+1];

        Deque<Integer> deque = new ArrayDeque<>();
        int j=0;
        for(int i=0 ;i< nums.length; i++) {

            while(!deque.isEmpty() && deque.peekFirst() < i-k+1)
                deque.pollFirst();

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast();

            deque.offerLast(i);

            if( i >= k-1)
                list[j++] = nums[deque.peekFirst()];
        }

        return list;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if( nums.length == 0) return new int[0];

        int[] list = new int[nums.length-k+1];

        PriorityQueue<Integer> heap = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b-a;
            }
        });

        for(int i=0; i<k; i++)
            heap.offer(nums[i]);

        int j=0;
        for(int i=k; i< nums.length; i++) {
            list[j++] = heap.peek();
            heap.remove(nums[i-k]);
            heap.offer(nums[i]);
        }
        list[j] = heap.peek();

        return list;
    }

}
