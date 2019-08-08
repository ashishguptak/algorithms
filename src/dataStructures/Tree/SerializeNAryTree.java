/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/discuss/190318/Serialize-and-Deserialize-Binary-and-N-ary-Tree-Summary
 *
 *  https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/discuss/190318/Serialize-and-Deserialize-Binary-and-N-ary-Tree-Summary
 * @author ashish gupta (akonda@expedia.com)
 */
public class SerializeNAryTree {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return "";

        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(Node root, StringBuilder sb) {
        if(root == null) return;

        sb.append(root.val+ " ");

        if(root.children.size() != 0) {
            sb.append("[ ");
            for(Node each: root.children)
                serialize(each, sb);
            sb.append("] ");
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) return null;

        String[] str = data.split(" ");
        Deque<Node> deque = new ArrayDeque<>();

        Node root = new Node(Integer.valueOf(str[0]), new ArrayList<>());
        int index = 1;
        Node curr = root;
        while(index < str.length) {
            if(str[index].equals("[")) {
                deque.push(curr);
            } else if (str[index].equals("]")) {
                deque.pop();
            } else {
                curr = new Node(Integer.valueOf(str[index]), new ArrayList<>());
                deque.peek().children.add(curr);
            }
            index++;
        }
        return root;
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};