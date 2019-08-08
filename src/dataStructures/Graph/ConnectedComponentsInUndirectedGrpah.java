/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 *  https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class ConnectedComponentsInUndirectedGrpah {
    public int countComponents(int n, int[][] edges) {
        if(n == 0 || edges.length == 0) return n;

        List<Integer>[] nodes = new ArrayList[n];

        for(int i=0; i<n; i++)
            nodes[i] = new ArrayList<>();

        for(int[] pair : edges) {
            nodes[pair[0]].add(pair[1]);
            nodes[pair[1]].add(pair[0]);
        }

        int count =0;
        boolean[] visited = new boolean[n];

        for(int i=0; i< n; i++) {
            if(!visited[i]) {
                count++;
                dfs(nodes, visited, i, i);
            }
        }
        return count;
    }

    private void dfs( List<Integer>[] nodes, boolean[] visited, int node, int parent) {
        if(visited[node]) return;
        visited[node] = true;

        for(Integer each: nodes[node])
            if(each != parent)
                dfs(nodes, visited, each, node);
    }
}

/**
 1) build a graph with ajdList notation
 2) Make a DFS traversal from any starting node - everytime a dfs call is initiated from the main function tells the #connected comopnents in the grpah

 Are there cycles to be handled in any component?
 yes - dfs recurisve loop needs to account for this use case

 time O(V+E)
 space O(V+E)

 how to handle cycle?
 we will have a visited ds, every time we encounter a visited node - stop the recursion for that node and then proceed to next elemnt in adjList.

 **/
