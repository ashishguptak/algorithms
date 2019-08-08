/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/description/
 *
 * https://leetcode.com/problems/flatten-nested-list-iterator/discuss/80147/Simple-Java-solution-using-a-stack-with-explanation
 * //other impl using Stack by iterating through NL in reverse order
 * how to implement remove() method
 *
 * this is not the best solution becuase we cpoy the entire data in but should just iterate over the original data structure
 * In the constructor, we will push all the nestedList into the stack from back to front, so when we pop the stack, it returns the first element.
 * Second, in hasNext() func, we peek the first element in stack currently, and if it is an integer, we will return the true and pop the element.
 * Else, if it is a list, we will further flatten it. This is iterative version of flattening the nestedlist.
 *
 *  // Time Complexity : next - O(1), hasNext - O(m) : m is average size of nested list, Constructor : O(n) - size of input list
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class FlattenNestedListIterator implements Iterator<Integer> {

    Deque<NestedInteger> deque;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        deque = new LinkedList<>();
        addElements(nestedList);
    }

    @Override
    public boolean hasNext() {
        while(!deque.isEmpty() && !deque.peek().isInteger())
            addElements(deque.poll().getList());

        return !deque.isEmpty();
    }

    private void addElements(List<NestedInteger> each) {
        for(int i= each.size()-1; i>=0; i--)
            deque.push(each.get(i));
    }

    @Override
    public Integer next() {
        return deque.pop().getInteger();
    }

    /*
            private List<Integer> list;
            private Iterator<Integer> it;
            public FlattenNestedListIterator(List<NestedInteger> nestedList) {
                list = new ArrayList<>();
                //System.out.println(list.toArray());
                buildList(nestedList);
                it = list.iterator();
            }

            private void buildList(List<NestedInteger> nestedList) {
                for(NestedInteger nested : nestedList) {
                    if(nested.isInteger())
                        list.add(nested.getInteger());
                    else
                        buildList(nested.getList());
                }
            }

            @Override
            public Integer next() {
                return it.next();
            }

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }
    */
        public interface NestedInteger {

            // @return true if this NestedInteger holds a single integer, rather than a nested list.
                 public boolean isInteger();

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
          public Integer getInteger();

            // @return the nested list that this NestedInteger holds, if it holds a nested list
           // Return null if this NestedInteger holds a single integer
               public List<NestedInteger> getList();
 }


}
