/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class InsertDeleteGetRandom {
    Random random;
    Map<Integer, Integer> map;
    List<Integer> list;

    public InsertDeleteGetRandom() {
        random = new Random();
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        return map.put(val, list.size()-1) == null;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        Integer position = map.get(val);
        Collections.swap(list, position, list.size() - 1);
        map.put(list.get(position),position);
        list.remove(list.size()-1);
        return map.remove(val) != null;
    }

    public int getRandom() {
        int i = random.nextInt(list.size());
        return list.get(i);
    }
}
