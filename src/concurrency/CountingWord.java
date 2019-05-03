/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package concurrency;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * http://tutorials.jenkov.com/java-concurrency/non-blocking-algorithms.html
 * https://www.baeldung.com/java-concurrent-map
 *
 * http://www.cs.umd.edu/class/fall2013/cmsc433/examples/wordcount/WordCountParallel.java
 *
 * Write a program to count words. This program should be threadsafe.
 * Implement two apis
 * 1) void addWord(String word) -> increment count of this word
 * 2) long getCount(String word) -> get count of this word
 *
 *
 * Test cases
 * One word updated by many threads
 * Many words updated by many threads
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class CountingWord {

    /**
     *  Hashtable is thread-safe but it is not very efficient, even Collections.synchronizedMap. In order to achieve,
     *  thread-safety with high-throughput and high-concurrency we use ConcurrentHashMap.
     *  Memory-consistent atomic operations, mainly uses Compare-And-Swap(CAS) before updating an entry.
     *
     *  Read operations are not blocked.
     *  Retrieval operations generally do not block in ConcurrentHashMap and could overlap with update operations. So for better performance, they only reflect the results of the most recently completed update operations, as stated in the official Javadoc.
     *
     *  The following APIs are also overridden to support atomicity, without a default interface implementation:

         putIfAbsent
         remove
         replace(key, oldValue, newValue)
         replace(key, value)
     */
    private ConcurrentHashMap<String, AtomicLong> map;
    private HashMap<String, AtomicLong> hashMap;

    public CountingWord(){
        map = new ConcurrentHashMap<>();
        hashMap = new HashMap<>();
    }

    /**
     * Implementing using non-blocking synchronizer when multiple threads try to access the map and
     * update the word count, only one of them will atomically increment the counter
     * @param word
     *
     * Non-blocking algorithm to
     */
    public void addWord(String word, Map<String, AtomicLong> map) {
        AtomicLong val = map.get(word);
       if(val == null) {
           val = map.putIfAbsent(word, new AtomicLong(1));
           if(val != null) map.get(word).incrementAndGet();
       } else {
           val.incrementAndGet();
       }

       //System.out.println(Thread.currentThread().getName());
    }

    //get count of the word
    public long getCount(String word, Map<String, AtomicLong> map) {
        if(map.containsKey(word)) return map.get(word).longValue();
        return 0L;
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService ex1 = Executors.newFixedThreadPool(6);
        CountingWord countingWord = new CountingWord();
        int total = 1000;

        for(int i=0; i< total/10; i++) {
            //ex1.execute(()-> countingWord.addWord("afaf", countingWord.map));
            ex1.execute(()-> {
                for(int j=0; j<10; j++)
                countingWord.addWord("afaf", countingWord.hashMap);

            });
        }

        ex1.shutdown();
        ex1.awaitTermination(5, TimeUnit.MILLISECONDS);
        System.out.println("over");
        Assert.assertEquals(countingWord.getCount("afaf", countingWord.hashMap), total);

    }

    @Test
    public void givenHashMapWhenSumParallelThenError() throws Exception {
        Map<String,Integer> map = new HashMap<>();
        List<Integer> sumList = parallelSum(map, 100);

        assertNotEquals(1, sumList
                .stream()
                .distinct()
                .count());
        System.out.println(sumList);
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertTrue(wrongResultCount > 0);
    }


    @Test
    public void testConcurrentHashMapParallelSum() throws InterruptedException {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<Integer> sumList = parallelSum(map, 100);

        assertEquals(1, sumList
                .stream()
                .distinct()
                .count());
        System.out.println(sumList);
        long wrongResultCount = sumList
                .stream()
                .filter(num -> num != 100)
                .count();

        assertTrue(wrongResultCount == 0);
    }

    private List<Integer> parallelSum(Map<String, Integer> map, int execTimes) throws InterruptedException {
        List<Integer> sumList = new ArrayList<>(1000);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0; i< execTimes; i++) {
            map.put("test", 0);

            for(int j=0; j < 10; j++) {
                executorService.execute(() -> {
                    for(int k=0; k<10; k++)
                        //this op is parallel
                        map.computeIfPresent("test", (key, v)-> v+1);
                });
            }
            //executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.MILLISECONDS);
            sumList.add(map.get("test"));
        }
        executorService.shutdown();
        return sumList;
    }
}
