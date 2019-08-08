/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/graph-valid-tree/
 *
 * I check one of these tree characterizations:
 *
 * Has n-1 edges and is acyclic.
 * Has n-1 edges and is connected.
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if( n < 0 || edges == null || edges.length != n-1) return false;
        if( n == 1) return true;

        List<Integer>[] nodes = new ArrayList[n];

        for(int i=0; i<n; i++)
            nodes[i] = new ArrayList<>();

        for(int[] each: edges) {
            nodes[each[0]].add(each[1]);
            nodes[each[1]].add(each[0]);
        }

        boolean[] visited = new boolean[n];
        boolean[] cycle = new boolean[n];

        if(!checkCycle(nodes, 0, 0, visited, cycle))
            return false;

        for(int i=0; i<n ;i++)
            if(nodes[i].isEmpty() || !visited[i])
                return false;
        return true;
    }

    private boolean checkCycle(List<Integer>[] nodes, int parent, int curr, boolean[] visited, boolean[] cycle) {
        if(cycle[curr]) return false;
        if(visited[curr]) return true;

        visited[curr] = true;
        cycle[curr] = true;

        for(int i=0; i< nodes[curr].size(); i++)
            if( nodes[curr].get(i) != parent &&
                    !checkCycle(nodes, curr, nodes[curr].get(i), visited, cycle))
                return false;

        cycle[curr] = false;
        return true;
    }

    // check if an undirected graph has cycle started from vertex u
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
        visited[u] = true;

        for (int i = 0; i < adjList.get(u).size(); i++) {
            int v = adjList.get(u).get(i);

            if ((visited[v] && parent != v) || (!visited[v] && hasCycle(adjList, v, visited, u)))
                return true;
        }
        return false;
    }
}

/**
 detect cycles in a undirected graph
 Build the graph
 in adj List rep

 DFS traversal to check for cycles in the graph - keep the parent node in recursion calls and do not call dfs for the edge connecting curr node to parent node

 For a tree, all nodes must be connected -must be a edge in the graph

 time O(V + E)
 space O(V+E)

 **/