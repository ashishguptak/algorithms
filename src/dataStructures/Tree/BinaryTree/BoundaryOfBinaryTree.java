/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/boundary-of-binary-tree/discuss/101280/Java(12ms)-left-boundary-left-leaves-right-leaves-right-boundary
 *
 * https://leetcode.com/problems/boundary-of-binary-tree/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class BoundaryOfBinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;

        list.add(root.val);
        traverseLeft(root.left, list);
        leaves(root.left, list);
        leaves(root.right, list);
        traverseRight(root.right, list);
        return list;
    }

    private void traverseLeft(TreeNode root, List<Integer> list) {
        if(root == null || (root.left == null && root.right == null)) return;

        list.add(root.val);
        if(root.left == null)
            traverseLeft(root.right, list);
        else
            traverseLeft(root.left, list);
    }

    private void traverseRight(TreeNode root, List<Integer> list) {
        if(root == null || (root.left == null && root.right == null)) return;

        if(root.right == null)
            traverseRight(root.left, list);
        else
            traverseRight(root.right, list);
        list.add(root.val);
    }

    private void leaves(TreeNode root, List<Integer> list) {
        if(root == null) return;

        if(root.left == null && root.right == null)
            list.add(root.val);
        leaves(root.left, list);
        leaves(root.right, list);
    }
}
