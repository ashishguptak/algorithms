/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 * https://www.geeksforgeeks.org/tree-isomorphism-problem/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class IsomorphicTrees {

    private TreeNode root1;
    private TreeNode root2;

    private boolean isIsomorphic( TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null)
            return true;

        if(root1 == null || root2 == null)
            return false;

        return root1.val == root2.val && (isIsomorphic(root1.left, root2.left) &&
                    isIsomorphic(root1.right, root2.right)) || (isIsomorphic(root1.left, root2.right) &&
                isIsomorphic(root1.right, root2.left)) ;
    }

    public static void main(String[] args) {
        IsomorphicTrees tree = new IsomorphicTrees();

        // Let us create trees shown in above diagram
        tree.root1 = new TreeNode(1);
        tree.root1.left = new TreeNode(2);
        tree.root1.right = new TreeNode(3);
        tree.root1.left.left = new TreeNode(4);
        tree.root1.left.right = new TreeNode(5);
        tree.root1.right.left = new TreeNode(6);
        tree.root1.left.right.left = new TreeNode(7);
        tree.root1.left.right.right = new TreeNode(8);

        tree.root2 = new TreeNode(1);
        tree.root2.left = new TreeNode(3);
        tree.root2.right = new TreeNode(2);
        tree.root2.right.left = new TreeNode(4);
        tree.root2.right.right = new TreeNode(5);
        tree.root2.left.right = new TreeNode(6);
        tree.root2.right.right.left = new TreeNode(8);
        tree.root2.right.right.right = new TreeNode(7);

        System.out.println(tree.isIsomorphic(tree.root1, tree.root2));
    }
}



