/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class MaxDepthOfNAryTree {

    public int maxDepth(Node root) {

        return recurseDFS(root);
    }

    public int recurseDFS(Node root) {
        if(root == null) return 0;

        int max=0;

        if( root.children == null)
            return 1;

        for(Node each: root.children) {
            int count = recurseDFS(each);
            max = Math.max(max, count);
        }

        return 1+max;
    }

    public int traverseBFS(Node root) {
        if(root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int count =0, size=0;

        while(queue.size() > 0) {
            size = queue.size();

            for( int i=0; i< size; i++) {
                Node temp = queue.poll();

                if(temp.children != null)
                    for( Node each : temp.children)
                        queue.add(each);
            }
            count++;
        }

        return count;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
