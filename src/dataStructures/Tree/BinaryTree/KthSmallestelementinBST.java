/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63659/What-if-you-could-modify-the-BST-node's-structure
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class KthSmallestelementinBST {

    int stop =0, kthVal=0, count=0;
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return Integer.MIN_VALUE;
        List<Integer> list = new ArrayList<>();
        stop = k;
        inOrder(root, list);
        //System.out.println(list);
        //return list.get(list.size() -1);
        return kthVal;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if(root == null) return;

        inOrder(root.left, list);
        if(count == stop) return;
        kthVal = root.val;
        count++;
        inOrder(root.right, list);
    }




    public int kthSmallest2(TreeNode root, int k) {
        TreeNodeWithCount rootWithCount = buildTreeWithCount(root);
        return kthSmallest(rootWithCount, k);
    }

    private TreeNodeWithCount buildTreeWithCount(TreeNode root) {
        if (root == null) return null;
        TreeNodeWithCount rootWithCount = new TreeNodeWithCount(root.val);
        rootWithCount.left = buildTreeWithCount(root.left);
        rootWithCount.right = buildTreeWithCount(root.right);
        if (rootWithCount.left != null) rootWithCount.count += rootWithCount.left.count;
        if (rootWithCount.right != null) rootWithCount.count += rootWithCount.right.count;
        return rootWithCount;
    }

    private int kthSmallest(TreeNodeWithCount rootWithCount, int k) {
        if (k <= 0 || k > rootWithCount.count) return -1;
        if (rootWithCount.left != null) {
            if (rootWithCount.left.count >= k) return kthSmallest(rootWithCount.left, k);
            if (rootWithCount.left.count == k-1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k-1-rootWithCount.left.count);
        } else {
            if (k == 1) return rootWithCount.val;
            return kthSmallest(rootWithCount.right, k-1);
        }
    }

    class TreeNodeWithCount {
        int val;
        int count;
        TreeNodeWithCount left;
        TreeNodeWithCount right;
        TreeNodeWithCount(int x) {val = x; count = 1;};
    }

}
