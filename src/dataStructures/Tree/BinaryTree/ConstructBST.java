/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 * https://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 * https://leetcode.com/problems/serialize-and-deserialize-bst/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class ConstructBST {

    private TreeNode PreOrderArrayConstructTree(int[] pre) {
        if(pre.length == 0) return null;

        return recurse(pre, 0, pre.length-1);
    }

    private TreeNode recurse(int[] pre, int start, int end) {
        if( start > end) return null;

        TreeNode root = new TreeNode(pre[start]);

        int k = start+1;
        while( k <= end && pre[k] <= pre[start]) {
            k++;
        }

        root.left = recurse(pre, start+1, k-1);
        root.right = recurse(pre, k, end);

        return root;
    }

    private TreeNode PreOrderArrayConstructTree2(int[] pre) {
        if(pre.length == 0) return null;

        TreeNode root = new TreeNode(pre[0]);
        for(int i=1; i< pre.length; i++) {
            insertIntoBST(root, pre[i]);
        }

        return root;
    }

    private TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode temp = root;
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }

        while(true) {
            if(temp.val < val) {
                if(temp.right == null) {
                    temp.right = new TreeNode(val);
                    break;
                }
                else
                    temp = temp.right;
            }
            else{
                if(temp.left == null) {
                    temp.left = new TreeNode(val);
                    break;
                }
                else
                    temp = temp.left;
            }
        }

        return root;
    }


    private TreeNode insertIntoBSTRecursive(TreeNode root, int val) {
        return null;
    }


    private TreeNode PreOrderArrayConstructTreeUsingStack(int[] pre) {
        return null;
    }

    public static void main(String[] args) {
        ConstructBST tree = new ConstructBST();
        int pre[] = new int[]{10, 5, 1, 7, 40, 50};
        TreeNode root = tree.PreOrderArrayConstructTree(pre);

        System.out.println("Inorder traversal of the constructed tree is ");
        TreeNode.printInorder(root);
        System.out.println();

        root = tree.PreOrderArrayConstructTree2(pre);
        System.out.println("Inorder traversal of the constructed tree 2 is ");
        TreeNode.printInorder(root);
        System.out.println();

        root = tree.PreOrderArrayConstructTree2(pre);
        System.out.println("Inorder traversal of the constructed tree using Stack is ");
        TreeNode.printInorder(root);
    }


    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            recurse(root, sb);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data.isEmpty()) return null;

            String[] vals = data.split(" ");
            return buildBST(vals, 0, vals.length-1);
        /*
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        for(int i = 1; i < vals.length; i++)
            insert(root, Integer.parseInt(vals[i]));
        return root;
        */
        }

        private TreeNode buildBST(String[] vals, int i, int j) {
            if(j < i ) return null;

            TreeNode root = new TreeNode(Integer.parseInt(vals[i]));
            int cut = i+1;
            while( cut <= j && Integer.parseInt(vals[cut]) <= Integer.parseInt(vals[i]))
                cut++;

            root.left = buildBST(vals, i+1, cut-1);
            root.right = buildBST(vals, cut, j);

            return root;
        }

        private void recurse(TreeNode root, StringBuilder sb) {
            if(root == null) return;

            sb.append(root.val + " ");
            recurse(root.left, sb);
            recurse(root.right, sb);
        }

        private void insert(TreeNode root, int val) {
            TreeNode curr = root, parent = null;

            while( curr != null) {
                parent = curr;
                curr = curr.val < val ? curr.right : curr.left;
            }

            if(parent.val < val)
                parent.right = new TreeNode(val);
            else
                parent.left = new TreeNode(val);
        }
    }

}
