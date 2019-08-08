/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *  https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)
 * @author ashish gupta (akonda@expedia.com)
 */
public class CopyListRandomPointer {

    public Node copyRandomList(Node head) {
        if(head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0, null, null);
        Node temp = dummy;
        Node curr = head, prev = dummy;
        while(curr != null) {
            temp.next = new Node(curr.val, null, null);
            map.put(curr, temp.next);
            curr = curr.next;
            temp = temp.next;
        }

        curr = head; temp = dummy.next;
        while(curr != null) {
            temp.random = map.get(curr.random);
            curr = curr.next;
            temp = temp.next;
        }
        return dummy.next;
    }

    public Node copyRandomList2(Node head) {
        if(head == null) return null;

        Node curr = head;
        while(curr != null) {
            Node clone = new Node(curr.val, null, null);
            clone.next = curr.next;
            curr.next = clone;
            curr = curr.next.next;
        }
        //System.out.println("sdg");
        curr = head;
        while(curr!= null && curr.next != null) {
            Node random = curr.random;
            if(random != null)
                curr.next.random = random.next;
            curr = curr.next.next;
        }
        //System.out.println("f");
        Node dummy = new Node(0, null, null);
        Node temp = dummy;
        while(curr!= null && curr.next != null) {
            temp.next = curr.next;
            curr.next = curr.next.next;
            temp = temp.next;
        }
        return dummy.next;
    }
}
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

/**
 deep copy a LL

 create a new node and then copy the contents from main list to new list and then move to the next ptr

 are all the element values unique?
 even if not, create a mapping of original node object to cloned node object.

 for a random ptr -
 1) null
 2) self ptr - self reference
 3) at any other node in the list - in this case, we cannot predict the object reference of an item which could be created much later. One way is to store all the nodes in map.

 if unique, we can create a hashmap of (val, cloned Node object) while creating the new list and then copy the object loc to random ptr;

 Create a mapping of input node key to cloned node,
 now for every input node -
 find the random node reference in the map and then map the cloned node random ptr to this random mapped key.

 time O(n)
 space O(n)

 **/