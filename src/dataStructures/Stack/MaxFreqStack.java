/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/articles/maximum-frequency-stack/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MaxFreqStack {
    Map<Integer, Integer> map;
    List<Deque<Integer>> list;

    public MaxFreqStack() {
        map = new HashMap<>();
        list = new ArrayList<>();
        list.add(new ArrayDeque<>());
    }

    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);

        int count = map.get(x);

        if(list.size() == count)
            list.add(new ArrayDeque<>());

        list.get(count).push(x);
    }

    public int pop() {
        int val = list.get(list.size()-1).pop();

        if(list.get(list.size()-1).isEmpty())
            list.remove(list.size()-1);

        map.put(val, map.get(val) -1);
        return val;
    }

    public static void main(String[] args) {
        MaxFreqStack maxFreqStack = new MaxFreqStack();
        /*
        maxFreqStack.push(5);
        maxFreqStack.push(7);
        maxFreqStack.push(5);
        maxFreqStack.push(7);
        maxFreqStack.push(4);
        maxFreqStack.push(5);

        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
        */


        maxFreqStack.push(4);
        maxFreqStack.push(0);
        maxFreqStack.push(9);
        maxFreqStack.push(3);
        maxFreqStack.push(4);
        maxFreqStack.push(2);

        System.out.println(maxFreqStack.pop());
        maxFreqStack.push(6);
        System.out.println(maxFreqStack.pop());
        maxFreqStack.push(1);
        System.out.println(maxFreqStack.pop());
        maxFreqStack.push(4);
        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
        System.out.println(maxFreqStack.pop());
    }
}
