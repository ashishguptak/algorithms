/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * https://leetcode.com/problems/zigzag-iterator/discuss/71779/Simple-Java-solution-for-K-vector
 *
 * https://leetcode.com/problems/zigzag-iterator/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class ZigZagIterator {

    //O(1) soln
    Queue<Iterator<Integer>> indexes;

    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        indexes = new LinkedList<>();
        if(!v1.isEmpty()) indexes.offer(v1.iterator());
        if(!v2.isEmpty()) indexes.offer(v2.iterator());
    }

    public int next() {
        Iterator<Integer> temp = indexes.poll();
        int val = temp.next();
        if(temp.hasNext()) indexes.offer(temp);

        return val;
    }

    public boolean hasNext() {
        return !indexes.isEmpty();
    }

    private int fIdx;
    private int sIdx;
    List<Integer> first;
    List<Integer> second;
    private boolean flag;

    /*
    v1 = [1,2]
    v2 = [3,4,5,6]
    */
    public int next2() {
        flag = !flag;
        if(fIdx > first.size() - 1) return second.get(sIdx++);
        else if (sIdx > second.size() - 1) return first.get(fIdx++);
        else
            return flag ?  first.get(fIdx++) : second.get(sIdx++);
    }

    public boolean hasNext2() {
        return fIdx < first.size() || sIdx < second.size();
    }


    /**
     *
     * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
     *
     * Clarification for the follow up question:
     * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:
     *
     * Input:
     * [1,2,3]
     * [4,5,6,7]
     * [8,9]
     *
     * Output: [1,4,8,2,5,9,3,6,7].
     *
     */


    /*
    For every list in the input, keep a counter to track the index. Sequentially, keep a variable to know the last incremented index and then increment the next possible one in the list. Once the index to increment reaches end of list, update the


    keep an array of indexes for the list
    hasNext()
    - increment the arr ptr and check if the index increment is less than size of that list
    yes return true
    no increment the array ptr+1 and check till a valid one exists, potentially scan O(k).
    wrap around at k-1 to 0.

    next()
    arr ptr, index++ return
     */

    /*
    private int arrPtr, prevPtr;
    private List<Integer> indexes;
    private List<List<Integer>> list;

    public ZigZagIterator(List<List<Integer>> input) {
        indexes = new ArrayList<>();
        for(int i=0; i< input.size(); i++)
            indexes.add(0);
        arrPtr  = 0;
        prevPtr = -1;
        list = input;
    }

    // O(K) time
    public boolean hasNext() {
        arrPtr = prevPtr+1;
        if(arrPtr == list.size()) arrPtr = 0;

        while(list.get(arrPtr).size() == indexes.get(arrPtr)) {
            arrPtr += 1;
            if(arrPtr == list.size()) arrPtr = 0;
            if(prevPtr + 1 == arrPtr) break;
        }
        prevPtr = arrPtr;
        return list.get(arrPtr).size() > indexes.get(arrPtr);
    }

        //O(1) time
    public int next() {
        int pos = indexes.get(arrPtr);
        indexes.set(arrPtr, pos+1);
        return list.get(arrPtr).get(pos);
    }
     */
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6, 7));
        //List<Integer> list3 = new ArrayList<>(Arrays.asList(8 ,9));

        List<List<Integer>> ip = new ArrayList<>();
        ip.add(list1);
        //ip.add(list2);
        //ip.add(list3);

        ZigZagIterator it = new ZigZagIterator(list1, list2);
        while(it.hasNext())
            System.out.print(it.next() + "  ");
    }

}
