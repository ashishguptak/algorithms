/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/articles/course-schedule-ii/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class CourseScheduleII {
    List<Integer>[] nodes;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        nodes = new List[numCourses];

        for(int[] each : prerequisites) {
            if(nodes[each[1]] == null)
                nodes[each[1]] = new ArrayList<>();
            nodes[each[1]].add(each[0]);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] cycle = new boolean[numCourses];

        for(int i=0; i< numCourses; i++)
            if(!topologicalSort(i, deque, visited, cycle))
                return new int[0];

        int[] result = new int[numCourses];
        Iterator it = deque.iterator();

        int i=0;
        while(it.hasNext())
            result[i++] = (int) it.next();
        return result;
    }

    private boolean topologicalSort(int course, Deque<Integer> deque, boolean[] visited, boolean[] cycle) {
        if(cycle[course]) return false;
        if(visited[course]) return true;

        visited[course] = true;
        cycle[course] = true;

        if(nodes[course] != null)
            for(int i : nodes[course])
                if(!topologicalSort(i, deque, visited, cycle))
                    return false;

        cycle[course] = false;
        deque.addFirst(course);
        return true;
    }
}

/**
 topological sorting of nodes in a graph

 only if it is DAG
 order of insertion is deepest node to root node.

 topological sort is ordering of elements such that every element u that occurs before v in the sorted order, u -> v

 **/
