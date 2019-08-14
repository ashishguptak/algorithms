/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/clone-graph/submissions/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class CloneGraph {

    public Node cloneGraph(Node node) {
        return cloneGraph(node, new HashMap<>());
    }

    private Node cloneGraph(Node root, Map<Node, Node> map) {
        if(root == null) return null;

        if(map.containsKey(root)) return map.get(root);

        Node curr = new Node(root.val, new ArrayList<>());
        map.put(root, curr);
        for(Node each: root.neighbors)
            curr.neighbors.add(cloneGraph(each, map));

        return curr;
    }
}
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}