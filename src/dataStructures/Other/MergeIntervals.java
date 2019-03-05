/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * check after Accepted leetcode suggestion questions
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() < 1) return new ArrayList<>();

        List<Interval> result = new ArrayList<>();

        Collections.sort(intervals, (a, b) -> a.start - b.start);
        result.add(intervals.get(0));

        boolean flag = false;
        for(int i = 1; i < intervals.size(); i++) {
            flag = false;
            //for (Interval ls: result) {
            for(int j = result.size() -1; j>=0; j--) {
                Interval ls = result.get(j);
                System.out.println("int start " +  intervals.get(i).start + " end "+ intervals.get(i).end);
                System.out.println("res start " +  ls.start + " end "+ ls.end);
                if(intervals.get(i).start >= ls.start && intervals.get(i).end <= ls.end) break;
                else if( intervals.get(i).start <= ls.end && intervals.get(i).end >= ls.end) {

                    ls.end = intervals.get(i).end;
                    System.out.println("changed res start " +  ls.start + " end "+ ls.end);
                    break;
                } else {
                    flag = true;
                    System.out.println("addign int start " +  intervals.get(i).start + " end "+ intervals.get(i).end);
                }
            }
            if(flag) result.add(intervals.get(i));
        }

        return result;
    }

    //best soln
    public List<Interval> merge2(List<Interval> intervals) {
        if(intervals.size() < 1) return new ArrayList<>();

        List<Interval> result = new ArrayList<>();

        Collections.sort(intervals, (a,b) -> a.start - b.start);
        result.add(intervals.get(0));

        for(int i = 1; i < intervals.size(); i++) {
            Interval ls = result.get(result.size()-1);
            if(intervals.get(i).start >= ls.start && intervals.get(i).end <= ls.end) continue;
            else if( intervals.get(i).start <= ls.end && intervals.get(i).end >= ls.end)
                ls.end = intervals.get(i).end;
            else
                result.add(intervals.get(i));
        }

        return result;
    }



    public List<Interval> merge3(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }


     // Definition for an interval.
    public class Interval {
      int start;
      int end;
        Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
   }
}
