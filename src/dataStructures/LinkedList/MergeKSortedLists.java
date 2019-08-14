/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.LinkedList;


import java.util.PriorityQueue;

/**
 * https://leetcode.com/articles/merge-k-sorted-list/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MergeKSortedLists {

    //time O(N*log K) and space O(N)
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        if(lists.length == 0) return null;

        PriorityQueue<ListNode> heap = new PriorityQueue<>( (a, b) -> a.val - b.val);

        for(ListNode ls: lists)
            if(ls != null) heap.offer(ls);

        ListNode temp = head;
        while(!heap.isEmpty()) {
            ListNode ls = heap.poll();
            temp.next = new ListNode(ls.val);
            temp = temp.next;
            if(ls.next != null)
                heap.offer(ls.next);
        }

        return head.next;
    }

    public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    public ListNode divideAndConquer(ListNode[] lists, int start, int end) {
        if(start > end) return null;
        else if(start == end) return lists[start];
        else if( start +1 == end)
            return mergeTwoLists(lists[start], lists[end]);

        int mid = start + (end-start)/2;
        ListNode left = divideAndConquer(lists, start, mid);
        ListNode right = divideAndConquer(lists, mid+1, end);

        return mergeTwoLists(left, right);
    }


    private ListNode mergeTwoLists(ListNode first, ListNode second) {
        if (first == null) return second;
        if (second == null) return first;

        ListNode head = null, curr = null;
        while (first != null && second != null) {
            ListNode temp;
            if (first.val <= second.val) {
                temp = first;
                first = first.next;
            } else {
                temp = second;
                second = second.next;
            }
            if (head == null) {
                head = temp;
                curr = temp;
            } else {
                curr.next = temp;
                curr = temp;
            }
        }

        if (first != null) curr.next = first;
        if (second != null) curr.next = second;

        return head;
    }
}
