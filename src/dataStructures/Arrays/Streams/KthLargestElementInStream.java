/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays.Streams;

import java.util.PriorityQueue;

/**
 *
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class KthLargestElementInStream {

    private PriorityQueue<Integer> heap;

    public void KthSmallestElementInStream(int k, int[] nums) {
        heap = new PriorityQueue<>((a,b) -> b-a);

        for (int num : nums) {
            if (heap.size() < k)
                heap.offer(num);
            else if (num < heap.peek()) {
                heap.offer(num);
                heap.poll();
            }
        }
    }

    public int add2(int val) {
        if( val < heap.peek()) {
            heap.offer(val);
            heap.poll();
        }
        return heap.peek();
    }

    private int count =0;
    public void KthLargest(int k, int[] nums) {
        heap = new PriorityQueue<>();
        count =k;
        for(int num : nums) {
            heap.offer(num);
            if(heap.size() > k)
                heap.poll();
        }
    }

    public int add(int val) {
        if( heap.size() < count)
            heap.offer(val);
        else if( val > heap.peek()) {
            heap.offer(val);
            heap.poll();
        }
        return heap.peek();
    }

}
