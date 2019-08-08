/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 *
 * https://leetcode.com/problems/delete-node-in-a-bst/discuss/93298/Iterative-solution-in-Java-O(h)-time-and-O(1)-space
 *
 * https://leetcode.com/articles/delete-node-in-a-bst/
 * @author ashish gupta (akonda@expedia.com)
 */
public class DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.val < key)
            root.right = deleteNode(root.right, key);
        else if(root.val > key)
            root.left = deleteNode(root.left, key);
        else {
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while(node.left != null)
            node = node.left;
        return node;
    }
}

/**
 * Inorder traversal of BST is an array sorted in the ascending order.
 *
 *  Soln steps -
 *  Recursively find the node to be deleted
 *  Once the node is found -
 *   1) node does not have left or right child, return null
 *   2) node noly has left subtree - return the left subtree
 *   3) node only has right subtree - return the right subtree
 *   4) node has both left and right - find the min val in right subtree, set that value to the currently found node, then recursively delete the  min val in right subtree
 *
 *   Changing the reference is a better approach for non-simple objects like social graph
 *
 **/