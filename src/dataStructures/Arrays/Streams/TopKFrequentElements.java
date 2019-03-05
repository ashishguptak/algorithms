/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays.Streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 *Two Approaches
 * 1) Map + Heap
 * 2) Bucket Sort
 *
 * https://leetcode.com/problems/top-k-frequent-words/description/
 *
 * https://zpjiang.me/2017/11/13/top-k-elementes-system-design/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class TopKFrequentElements {


    // How not to code
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if (k == 0 || nums.length == 0) return list;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int val = map.get(nums[i]);
                map.put(nums[i], val + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        Queue<int[]> heap = new PriorityQueue<>(k, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        int count = k;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] item = new int[2];
            item[0] = entry.getKey();
            item[1] = entry.getValue();

            if (count > 0) {
                heap.add(item);
                count--;
            } else {
                int[] heapele = heap.peek();
                if (heapele[1] < item[1]) {
                    heap.poll();
                    heap.add(item);
                }
            }
        }

        for (int i = 0; i < k; i++) {
            int[] element = heap.poll();
            list.add(element[0]);
        }

        Collections.reverse(list);
        return list;
    }


    public List<Integer> topKFrequentBetter(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if (k == 0 || nums.length == 0) return list;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        Queue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                return a.getValue() - b.getValue();
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.add(entry);
            if (heap.size() > k)
                heap.poll();
        }

        for (Map.Entry<Integer, Integer> element : heap) {
            list.add(element.getKey());
        }

        return list;
    }

    //use treeMap. Use freqncy as the key so we can get all freqencies in order
    public List<String> topKFrequent3(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        TreeMap<Integer, List<String>> tree = new TreeMap<>();

        for(String str: words)
            map.put(str, map.getOrDefault(str, 0) + 1);

        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            if(!tree.containsKey(entry.getValue()))
                tree.put(entry.getValue(), new ArrayList<>());
            tree.get(entry.getValue()).add(entry.getKey());
        }

        int max = tree.lastKey();
        while(k - result.size() > 0) {
            List<String> ls = tree.floorEntry(max).getValue();
            max = tree.floorEntry(max).getKey() - 1;
            Collections.sort(ls);
            result.addAll(ls.subList(0, Math.min(k - result.size(), ls.size())));
        }

        return result;
    }


    //use Bucket Sorting tech
    public List<Integer> topKFrequent4(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);

        List<Integer>[] buckets = new List[nums.length + 1];

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (buckets[entry.getValue()] == null)
                buckets[entry.getValue()] = new ArrayList<>();

            buckets[entry.getValue()].add(entry.getKey());
        }

        List<Integer> result = new ArrayList<>();
        for (int i = nums.length; i >= 0; i--) {
            if (buckets[i] == null) continue;

            for (int j = 0; j < buckets[i].size() && k > 0; j++) {
                k--;
                result.add(buckets[i].get(j));
            }
        }
        return result;
    }

    //use Bucket Sorting tech for Stream soln,
    // use TreeSet for sorted output of strings in each bucket
    public List<Integer> topKFrequent5(int[] nums, int k) {
        int val=0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);

        Set<Integer>[] buckets = new Set[nums.length + 1];

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (buckets[entry.getValue()] == null)
                buckets[entry.getValue()] = new HashSet<>();

            buckets[entry.getValue()].add(entry.getKey());
        }

        //update the buckets everytime new element is added retrieve top k
        if(map.containsKey(val)) {
            buckets[map.get(val)].remove(val);
            map.put(val, map.getOrDefault(val, 0) + 1);
            buckets[map.get(val)].add(val);
        }

        List<Integer> result = new ArrayList<>();
        //wrong logic
        for(int j = buckets.length -1 ; j >=0 && result.size() < k ; j--) {
            if(buckets[j] == null) continue;

            result.addAll(buckets[j]);
        }
        return result;
    }


    public List<String> topKFrequent(String[] words, int k) {
        if(words.length == 0) return new ArrayList<>();
        Map<String, Integer> wordMap = new HashMap<>();
        for(String word : words)
            wordMap.put(word, wordMap.getOrDefault(word, 0) +1);

        /*
        List<String>[] count = new List[words.length+1];

        for(Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if(count[entry.getValue()] == null)
                count[entry.getValue()] = new ArrayList<>();
            count[entry.getValue()].add(entry.getKey());
        }


        for(int i=count.length-1; i >=0 && k > 0; i--) {
            if(count[i] == null) continue;
            Collections.sort(count[i]);
            List<String> temp = count[i].subList(0, Math.min(count[i].size(), k));
            result.addAll(temp);
            k = k - temp.size();
        }
        */
        List<String> result = new LinkedList<>();
        /*
        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue();
            }
        });
        */
        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
                (a,b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() -b.getValue());


        for(Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            heap.offer(entry);
            if(heap.size() > k)
                heap.poll();
        }

        while(!heap.isEmpty())
            result.add(heap.poll().getKey());

        Collections.reverse(result);

        return result;
    }

}