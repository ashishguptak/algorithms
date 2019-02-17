/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * https://leetcode.com/problems/my-calendar-i/description/
 * @author ashish gupta (akonda@expedia.com)
 */
public class MyCalendar {

    private List<int[]> booked;
    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        booked = new ArrayList<>();
        calendar = new TreeMap<>();
    }

    /*
    public boolean book(int start, int end) {
        for(int[] event: booked) {
            if( !(end <= event[0]) && !(event[1] <= start))
                return false;
        }
        booked.add(new int[]{start, end});
        return true;
    }
    */

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> floor = calendar.floorEntry(start);
        Map.Entry<Integer, Integer> ceil = calendar.ceilingEntry(start);

        if( (floor == null || floor.getValue() <= start) &&
                (ceil == null ||  end <= ceil.getKey()) ) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
