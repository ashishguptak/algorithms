/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class SerializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";

        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null ");
            return;
        }

        sb.append(root.val+ " ");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;

        String[] str = data.split(" ");
        Queue<String> queue = new LinkedList(Arrays.asList(str));
        return deserialize(queue);
    }

    private TreeNode deserialize(Queue<String> queue) {
        String val = queue.poll();
        if(val.equals("null")) return null;

        TreeNode temp = new TreeNode(Integer.valueOf(val));
        temp.left = deserialize(queue);
        temp.right = deserialize(queue);

        return temp;
    }
}
