/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if( numRows == 0) return result;

        List<Integer> list = new ArrayList<>();
        List<Integer> prev = null;
        list.add(1);
        result.add(list);
        for(int i=1; i< numRows; i++) {
            list = new ArrayList<>();
            list.add(1);
            if(i > 1)
                for(int j=0; j < prev.size()-1; j++)
                    list.add(prev.get(j) + prev.get(j+1));
            list.add(1);
            result.add(list);
            prev = list;
        }

        return result;
    }
}
