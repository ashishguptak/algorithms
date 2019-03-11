/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * time Complexity ??
 *
 * https://leetcode.com/problems/reconstruct-itinerary/discuss/78799/Very-Straightforward-DFS-Solution-with-Detailed-Explanations
 * https://leetcode.com/problems/reconstruct-itinerary/discuss/78789/Java-14ms.-DFS-backtrack
 * @author ashish gupta (akonda@expedia.com)
 */

//The nice thing about DFS is it tries a path, and if that's wrong (i.e. path does not lead to solution),
// DFS goes one step back and tries another path. It continues to do so until we've found the correct path (which leads to the solution). You need to always bear this nice feature in mind when utilizing DFS to solve problems.
//
//        In this problem, the path we are going to find is an itinerary which:
//
//        1. uses all tickets to travel among airports
//        2. preferably in ascending lexical order of airport code
//        Keep in mind that requirement 1 must be satisfied before we consider 2.
//          If we always choose the airport with the smallest lexical order, this would lead to a perfectly lexical-ordered itinerary,
//          but pay attention that when doing so, there can be a "dead end" somewhere in the tickets such that we are not able visit all airports (or we can't use all our tickets), which is bad because it fails to satisfy requirement 1 of this problem. Thus we need to take a step back and try other possible airports, which might not give us a perfectly ordered solution, but will use all tickets and cover all airports.
//
//        Thus it's natural to think about the "backtracking" feature of DFS. We start by building a graph and then sorting vertices in the adjacency list so that when we traverse the graph later, we can guarantee the lexical order of the itinerary can be as good as possible. When we have generated an itinerary, we check if we have used all our airline tickets. If not, we revert the change and try another ticket. We keep trying until we have used all our tickets.

public class ReconstructItinerary {

    Map<String, List<String>> map;
    int total = 0;
    public List<String> findItinerary(String[][] tickets) {
        List<String> result = new ArrayList<>();
        map = new HashMap<>();
        if(tickets.length == 0) return result;

        total = tickets.length;

        for(String[] each: tickets) {
            if(!map.containsKey(each[0]))
                map.put(each[0], new ArrayList<>());
            map.get(each[0]).add(each[1]);
        }

        for(Map.Entry<String, List<String>> entry: map.entrySet())
            Collections.sort(entry.getValue());

        dfs("JFK", result);
        return result;
    }

    private boolean dfs(String start, List<String> result) {
        result.add(start);
        if(total + 1 == result.size()) return true;

        if(map.get(start) == null) return false;
        List<String> list = map.get(start);
        for(int i=0; i < list.size(); i++) {
            String each = list.remove(i);
            if(dfs(each, result)) return true;
            list.add(i, each);
            result.remove(result.size() -1);
        }

        return false;
    }
}
