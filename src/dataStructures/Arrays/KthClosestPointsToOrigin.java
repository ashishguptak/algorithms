/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class KthClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        int[][] close = new int[K][2];

        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (p1, p2) ->  p2[0]*p2[0] +  p2[1]*p2[1] - p1[0]*p1[0] -  p1[1]*p1[1]);
        for(int[] p: points) {
            heap.offer(p);
            if(heap.size() > K)
                heap.poll();
        }

        int i=0;
        while(!heap.isEmpty()) {
            int[] point = heap.poll();
            close[i][0] = point[0];
            close[i++][1] = point[1];
        }
        return close;
    }

    public int[][] kClosest2(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[0]*p1[0] + p1[1]*p1[1] - p2[0]*p2[0] - p2[1]*p2[1];
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }
}
