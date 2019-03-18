/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/flatten-nested-list-iterator/description/
 *
 * https://leetcode.com/problems/flatten-nested-list-iterator/discuss/80147/Simple-Java-solution-using-a-stack-with-explanation
 * //other impl using Stack by iterating through NL in reverse order
 * how to implement remove() method
 * @author ashish gupta (akonda@expedia.com)
 */
public class FlattenNestedListIterator implements Iterator<Integer> {

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
