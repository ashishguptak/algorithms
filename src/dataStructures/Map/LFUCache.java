/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * https://leetcode.com/problems/lfu-cache/description/
 * @author ashish gupta (akonda@expedia.com)
 */
class LFUCache {

    private Map<Integer, Integer> countKey;
    private Map<Integer, Map<Integer, Integer>> freqMap;
    private int currCount, capacity;
    private int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        countKey = new HashMap<>();
        freqMap = new HashMap<>();
        currCount =0;
    }

    public int get(int key) {
        if(!countKey.containsKey(key))
            return -1;
        int count = countKey.get(key);
        return updateFreqMap(count, key);
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        if(countKey.containsKey(key)) {
            freqMap.get(countKey.get(key)).put(key, value);
            get(key);
            return;
        }

        if(currCount >= capacity)
            removeLFU();

        currCount++;
        countKey.put(key, 1);
        insertFreqMap(1, key, value);
        min = 1;
    }

    public int updateFreqMap(int count, int key) {
        int val = freqMap.get(count).get(key);
        freqMap.get(count).remove(key);

        if(count == min && freqMap.get(count).size() == 0)
            min++;

        insertFreqMap(count+1, key, val);
        countKey.put(key, count+1);
        return val;
    }

    public void insertFreqMap(int freqKey, int key, int val) {
        if(!freqMap.containsKey(freqKey))
            freqMap.put(freqKey, new LinkedHashMap<>(capacity, 0.75f, true));

        freqMap.get(freqKey).put(key, val);
    }

    public void removeLFU() {
        currCount--;
        Iterator<Map.Entry<Integer, Integer>> it = freqMap.get(min).entrySet().iterator();
        Map.Entry<Integer, Integer> entry  = it.next();
        it.remove();
        countKey.remove(entry.getKey());
    }
}