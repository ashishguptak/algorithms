/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package SystemDesign;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/design-hit-counter/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class HitCounter {

//    TreeMap<Integer, Integer> map;
//    /** Initialize your data structure here. */
//    public HitCounter() {
//        map = new TreeMap<>();
//        map.put(0,0);
//    }
//
//    /** Record a hit.
//     @param timestamp - The current timestamp (in seconds granularity). */
//    public void hit(int timestamp) {
//        map.put(timestamp, map.lastEntry().getValue()+1);
//    }
//
//    /** Return the number of hits in the past 5 minutes.
//     @param timestamp - The current timestamp (in seconds granularity). */
//    public int getHits(int timestamp) {
//        int lastHit = map.floorEntry(timestamp).getValue();
//        int firstHit = map.floorEntry(timestamp <= 300? 0: timestamp - 300).getValue();
//
//        return lastHit - firstHit;
//    }

/*
    LinkedList<Integer> queue;
    public HitCounter() {
        queue = new LinkedList<>();
    }

    public void hit(int timestamp) {
        queue.add(timestamp);
    }

    public int getHits(int timestamp) {
        while(!queue.isEmpty() && (timestamp - queue.getFirst() >= 300))
            queue.poll();

        return queue.size();
    }
*/

    List<Integer> set;
    /** Initialize your data structure here. */
    public HitCounter() {
        set = new ArrayList<>();
    }
    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        set.add(timestamp);
    }
    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int left = 0;
        int right = set.size()-1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (set.get(mid) >= (timestamp-299)) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return set.size()-left;
    }
}
