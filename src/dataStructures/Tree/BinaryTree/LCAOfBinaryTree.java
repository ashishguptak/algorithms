/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class LCAOfBinaryTree {

    private TreeNode root;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        /*
        if( root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        else if ( root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);

        return root;
        */

        while(root != null) {
            if(root.val > p.val && root.val > q.val)
                root = root.left;
            else if ( root.val < p.val && root.val < q.val)
                root = root.right;
            else
                return root;
        }
        return root;
    }

    public TreeNode lowestCommonAncestorinBT(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;

        if(root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null)
            return root;

        return left != null? left : right;
    }


    //repeated operation to find LCA/ancestor multiple times
    public TreeNode lowestCommonAncestorinBT2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        map.put(root, null);
        TreeNode temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.left != null) {
                map.put(temp.left, temp);
                queue.add(temp.left);
            }
            if (temp.right != null) {
                map.put(temp.right, temp);
                queue.add(temp.right);
            }
        }

        Set<TreeNode> set = new HashSet<>();
        temp = p;
        while (temp != null) {
            set.add(temp);
            temp = map.get(temp);
        }

        temp = q;
        while (!set.contains(temp)) {
            temp = map.get(temp);
        }

        return temp;
    }


    public static void main(String[] args) {
        LCAOfBinaryTree tree = new LCAOfBinaryTree();

        // Let us create trees shown in above diagram
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.left.right.left = new TreeNode(7);
        tree.root.left.right.right = new TreeNode(8);
    }

}
