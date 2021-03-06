/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }

    public static void printInorder(TreeNode root) {
        if(root == null) return;

        printInorder(root.left);
        System.out.print(root.val+ " ");
        printInorder(root.right);
    }

    public static void printPreorder(TreeNode root) {
        if(root == null) return;

        System.out.print(root.val+ " ");
        printInorder(root.left);
        printInorder(root.right);
    }

    public static void printPostorder(TreeNode root) {
        if(root == null) return;

        printInorder(root.left);
        printInorder(root.right);
        System.out.print(root.val+ " ");
    }
}
