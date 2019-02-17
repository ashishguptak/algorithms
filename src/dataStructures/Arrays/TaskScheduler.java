/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * https://leetcode.com/problems/task-scheduler/discuss/104501/Java-PriorityQueue-solution-Similar-problem-Rearrange-string-K-distance-apart
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        if(tasks.length < 1 || n == 0)
            return tasks.length;

        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<tasks.length; i++)
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(
                new Comparator<Map.Entry<Character, Integer>>(){
                    @Override
                    public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
                        if(a.getValue() != b.getValue())
                            return b.getValue() - a.getValue();
                        return a.getKey() - b.getKey();
                    }
                });

        heap.addAll(map.entrySet());

        int count =0, k=0;
        List<Map.Entry> tempList = new ArrayList<>();
        Map.Entry<Character, Integer> temp;
        while(!heap.isEmpty()) {
            count +=k;
            k = n+1;
            while(!heap.isEmpty() && k >0) {
                temp = heap.poll();
                count++;
                k--;
                temp.setValue(temp.getValue() -1);
                if(temp.getValue() > 0)
                    tempList.add(temp);
            }

            for(Map.Entry entry :tempList)
                heap.add(entry);

            tempList.clear();
        }

        return count;
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

}
