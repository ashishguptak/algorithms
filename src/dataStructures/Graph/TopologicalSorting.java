/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Topological Sort O(V+E)
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class TopologicalSorting<T> {

    private Deque<Long> sort(Graph<T> graph) {
        Deque<Long> deque = new ArrayDeque<>();
        if(graph == null) return deque;
        Set<Long> visited = new HashSet<>();

        List<Vertex<T>>vertices =new ArrayList<>(graph.getAllVertex());

        for( Vertex v: vertices) {
            dfsRecurse(deque, visited, v);
        }
        return deque;
    }

    private void dfsRecurse(Deque<Long> deque, Set<Long> visited, Vertex v) {

        if(visited.contains(v.getId())) return;
        visited.add(v.getId());

        List<Vertex> adj =  v.getAdjacentVertexes();
        for( Vertex k : adj) {
            dfsRecurse(deque, visited, k);
        }
        deque.addFirst(v.getId());
    }


    public static void main(String args[]) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
        graph.addEdge(3, 8);
        graph.addEdge(8, 11);

        TopologicalSorting<Integer> topologicalSorting = new TopologicalSorting<>();
        Deque<Long> deque = topologicalSorting.sort(graph);
        while(deque.isEmpty())
            System.out.print( deque.pollFirst()+ "->");

    }
}
