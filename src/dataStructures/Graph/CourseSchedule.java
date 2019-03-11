/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/course-schedule/description/
 *
 * https://leetcode.com/problems/course-schedule/discuss/58524/Java-DFS-and-BFS-solution
 * @author ashish gupta (akonda@expedia.com)
 */
public class CourseSchedule {

    List<Integer>[] edges;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new List[numCourses];

        for(int[] each : prerequisites) {
            if(edges[each[1]] == null)
                edges[each[1]] = new ArrayList<>();
            edges[each[1]].add(each[0]);
        }

        return dfs();
    }

    private boolean dfs() {
        boolean[] visited = new boolean[edges.length];
        boolean[] cycle = new boolean[edges.length];

        for(int i=0; i < edges.length; i++)
            if(recurse(i, visited, cycle))
                return false;

        return true;
    }

    private boolean recurse(int i, boolean[] visited, boolean[] cycle) {
        if(cycle[i]) return true;
        if(visited[i]) return false;

        visited[i] = true;
        cycle[i] = true;

        if(edges[i] != null)
            for(Integer j: edges[i])
                if(recurse(j, visited, cycle))
                    return true;

        cycle[i] = false;
        return false;
    }

}
