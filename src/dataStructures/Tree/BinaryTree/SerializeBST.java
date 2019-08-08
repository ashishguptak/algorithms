/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 * very nature of BST is every node to left of root is smaller then root node and every node to right of root is larger than root node.
 *
 * and this principle applies recursively for every node.
 *
 * make a preOrder traversal of bst, since root of subtree is always encoutnered first and bst follows strict rule - the structure of bst is preserved.
 *
 * reconstruction is by parsing each element in the string and then placing at right point of root node.
 *
 * time O(n)
 * space O(n)
 *
 * Serialize means
 *  1) encode tree structure
 *  2) encode node values
 *  3) choose delimiters to separate the values in the encoded string
 *
 *
 *https://leetcode.com/articles/serialize-and-deserialize-bst/
 * @author ashish gupta (akonda@expedia.com)
 */
public class SerializeBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        if(root == null) return;

        sb.append(root.val + " ");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;
        String[] str = data.split(" ");

        TreeNode root = new TreeNode(Integer.valueOf(str[0]));
        TreeNode parent = null, prev = null;
        //System.out.println(Arrays.asList(str));
        for(int i=1; i< str.length; i++) {
            int val = Integer.valueOf(str[i]);
            parent = root;
            while(parent != null) {
                prev = parent;
                parent =  parent.val < val ? parent.right : parent.left;
            }
            if(prev.val < val)
                prev.right = new TreeNode(val);
            else
                prev.left = new TreeNode(val);
        }
        return root;
    }
}
