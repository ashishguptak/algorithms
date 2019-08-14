/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 *
 *  https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/discuss/231425/Java-Solution-using-Only-PriorityQueue
 * @author ashish gupta (akonda@expedia.com)
 */
public class VerticalOrderBinaryTree {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        List<Node> list = new ArrayList<>();
        inorder(root, list, 0, 0);

        Collections.sort(list, new Comparator<Node> () {
            @Override
            public int compare(Node a, Node b) {
                if(a.col == b.col && a.row == b.row)
                    return a.col - b.col;
                else if(a.col == b.col)
                    return a.row - b.row;
                return a.col-b.col;
            }
        });

        int prev=list.get(0).col, curr=0;
        List<Integer> temp = new ArrayList<>();
        for(Node each: list) {
            curr = each.col;
            if(prev != curr) {
                result.add(temp);
                temp = new ArrayList<>();
            }
            temp.add(each.val);
            prev = curr;
        }
        result.add(temp);

        return result;
    }

    private void inorder(TreeNode root, List<Node> list, int row, int col) {
        if(root == null) return;

        list.add(new Node(root.val, row, col));
        inorder(root.left, list, row+1, col-1);
        inorder(root.right, list, row+1, col+1);
    }
}

class Node {
    int val;
    int row; int col;
    public Node(int val, int row, int col) {
        this.val = val;
        this.row = row;
        this.col = col;
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        PriorityQueue<Point> heap = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x != p2.x)
                    return p1.x - p2.x;
                else if (p1.y != p2.y)
                    return p2.y - p1.y;
                else
                    return p1.val - p2.val;
            }
        });

        dfs(root, 0, 0, heap);
        int prev_x = Integer.MIN_VALUE;
        while(!heap.isEmpty()) {
            Point p = heap.poll();
            if(p.x > prev_x) {
                List<Integer> list = new ArrayList();
                list.add(p.val);
                result.add(list);
            } else {
                List<Integer> list = result.get(result.size() -1);
                list.add(p.val);
            }
            prev_x = p.x;
        }
        return result;
    }

    private void dfs(TreeNode root, int x, int y, PriorityQueue<Point> heap) {
        if(root == null) return;

        heap.add(new Point(x, y, root.val));
        dfs(root.left, x-1, y-1, heap);
        dfs(root.right, x+1, y-1, heap);
    }

}

class Point implements Comparable<Point>{
    int x;
    int y;
    int val;

    public Point(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Point p) {
        if (this.x != p.x)
            return this.x - p.x;
        else if (this.y != p.y)
            return this.y - p.y;
        else
            return this.val - p.val;
    }
}