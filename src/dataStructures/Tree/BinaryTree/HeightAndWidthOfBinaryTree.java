/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class HeightAndWidthOfBinaryTree {

    private TreeNode root;

    private int findHeightIterative(TreeNode root, boolean flag) {
        if(root == null) return 0;

        int height = 0, width =1;
        int cur_width =1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while( !queue.isEmpty()) {
            cur_width = queue.size();
            height++;

            if(cur_width > width)
                width = cur_width;

            for(int i=0; i< cur_width; i++) {
                TreeNode temp = queue.poll();
                if(temp.left != null) queue.add(temp.left);
                if(temp.right != null) queue.add(temp.right);
            }
        }

        return flag ? height : width;
    }

    private int findHeightRecursive(TreeNode root) {
        if(root == null) return 0;

        int left = findHeightRecursive(root.left);
        int right = findHeightRecursive(root.right);

        return 1 + Math.max(left, right);
    }

    private int findWidthRecursive(TreeNode root) {
        if(root == null) return 0;

        int height = findHeightRecursive(root);

        int cur_width =0, width=0;

        for (int i =0; i< height;i++) {
            cur_width = getWidth(root, i);
            if( cur_width > width)
                width = cur_width;
        }

        return width;
    }

    private int getWidth(TreeNode root, int level) {
        if(root == null) return 0;
        if(level == 1) return 1;

        return getWidth(root.right, level-1) +
                        getWidth(root.left, level-1);
    }

    private int getWidthUsingPreOrder(TreeNode root) {
        if(root == null) return 0;

        int height = findHeightRecursive(root);
        int[] width = new int[height];

        traverseInOrder(root, 0, width);

        int max =0;
        for(int i=0; i<height; i++)
            if(width[i] > max)
                max = width[i];

        return max;
    }

    private void traversePreOrder(TreeNode root, int i, int[] width) {
        if(root == null) return;

        width[i] += 1;
        traversePreOrder(root.left, i+1, width);
        traversePreOrder(root.right, i+1, width);
    }

    private void traverseInOrder(TreeNode root, int i, int[] width) {
        if(root == null) return;

        traversePreOrder(root.left, i+1, width);
        width[i] += 1;
        traversePreOrder(root.right, i+1, width);
    }


    public static void main(String[] args) {

        HeightAndWidthOfBinaryTree tree = new HeightAndWidthOfBinaryTree();

        // Let us create trees shown in above diagram
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right =  new TreeNode(5);
        tree.root.right.left =  new TreeNode(6);
        tree.root.right.right =  new TreeNode(8);
        tree.root.left.right.left = new TreeNode(7);
        tree.root.left.right.right = new TreeNode(8);

        System.out.println("height: " + tree.findHeightIterative(tree.root, true));
        System.out.println("widht: " + tree.findHeightIterative(tree.root, false));

        System.out.println("heightRecurse: " + tree.findHeightRecursive(tree.root));
        System.out.println("WidthRecurse: " + tree.findWidthRecursive(tree.root));
        System.out.println("WidthRecurse using PreOrder: " + tree.getWidthUsingPreOrder(tree.root));
    }

}
