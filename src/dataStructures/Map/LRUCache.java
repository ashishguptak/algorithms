/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An LRU cache should support the operations: lookup, insert and delete. Apparently, in order to achieve fast lookup, we need to use hash. By the same token, if we want to make insert/delete fast, something like linked list should come to your mind. Since we need to locate the least recently used item efficiently, we need something in order like queue, stack or sorted array.
 *
 * To combine all these analyses, we can use queue implemented by a doubly linked list to store all the resources. Also, a hash table with resource identifier as key and address of the corresponding queue node as value is needed.
 *
 * Here’s how it works. when resource A is requested, we check the hash table to see if A exists in the cache. If exists, we can immediately locate the corresponding queue node and return the resource. If not, we’ll add A into the cache. If there are enough space, we just add a to the end of the queue and update the hash table. Otherwise, we need to delete the least recently used entry. To do that, we can easily remove the head of the queue and the corresponding entry from the hash table.
 *
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class LRUCache {

    private LinkedHashMap<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedHashMap(capacity, 0.75F, true) {
            @Override
            public boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
