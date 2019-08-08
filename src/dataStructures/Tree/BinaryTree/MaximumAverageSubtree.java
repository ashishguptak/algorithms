/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 * https://leetcode.com/problems/maximum-average-subtree/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MaximumAverageSubtree {
    private double max = (double) Integer.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        if(root == null) return 0.0;

        findAvg(root);
        return max;
    }

    private Avg findAvg(TreeNode root) {
        if(root == null) return null;

        Avg left = findAvg(root.left);
        Avg right = findAvg(root.right);

        double total = (double) root.val; int nodes = 1;
        if(left != null) {
            total += left.sum;
            nodes += left.nodes;
        }
        if(right != null) {
            total += right.sum;
            nodes += right.nodes;
        }

        max = Math.max( max, total/nodes);
        return new Avg(total, nodes);
    }
}

class Avg {
    int nodes;
    double sum;
    public Avg(double sum, int node) {
        this.sum = sum;
        this.nodes = node;
    }
}