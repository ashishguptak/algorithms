/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *  https://medium.com/smyte/rate-limiter-df3408325846
 *  https://medium.com/@saisandeepmopuri/system-design-rate-limiter-and-data-modelling-9304b0d18250
 *  https://blog.figma.com/an-alternative-approach-to-rate-limiting-f8a06cf7c94c
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class RateLimiter {

    Map<Integer, RateLimiterMapValue> fixedWindowLimiter = new HashMap<>();
    Map<Integer, SortedSet<Long>> slidingWindowLimiter = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> slidingWindowCounterLimiter = new HashMap<>();

    Map<Integer, ClientLimitMapValue> clientLimits = new HashMap<>();

    private void setClientThresholds() {
        //populate the client Limits data structure
    }

    private synchronized boolean fixedWindowAlgorithm(HttpClient httpClient) throws IOException {
        RateLimiterMapValue value;
        if(fixedWindowLimiter.containsKey(httpClient.getClientId())) {
            value = fixedWindowLimiter.get(httpClient.getClientId());
            if(httpClient.getTimeStamp() - value.getLastTimeStamp() > clientLimits.get(httpClient.getClientId()).getDuration()) {
                value = new RateLimiterMapValue(1, httpClient.getTimeStamp());
                fixedWindowLimiter.put(httpClient.getClientId(), value);
            } else {
                if (value.getCount() > clientLimits.get(httpClient.getClientId()).getCount())
                    return false;
                value.setCount(value.getCount() + 1);
            }
        } else{
            value = new RateLimiterMapValue(1, httpClient.getTimeStamp());
            fixedWindowLimiter.put(httpClient.getClientId(), value);
        }
        return true;
    }

    // can also be done using Deque instead of SortedSet
    private boolean slidingWindowAlgorithm(HttpClient httpClient) {
        if(slidingWindowLimiter.containsKey(httpClient.getClientId())) {
            SortedSet<Long> currStamps = slidingWindowLimiter.get(httpClient.getClientId());
            removeOlderTimeStamps(currStamps, clientLimits.get(httpClient.getClientId()).getDuration());
            if(currStamps.size() > clientLimits.get(httpClient.getClientId()).getCount())
                return false;
            currStamps.add(httpClient.getTimeStamp());
            slidingWindowLimiter.put(httpClient.getClientId(), currStamps);
        } else {
            SortedSet<Long> timeStamps = new TreeSet<>();
            timeStamps.add(httpClient.getTimeStamp());
            slidingWindowLimiter.put(httpClient.getClientId(), timeStamps);
        }
        return true;
    }

    private void removeOlderTimeStamps(SortedSet<Long> currStamps, long duration) {
    }

    private boolean slidingWindowCounter(HttpClient httpClient) {
        if(slidingWindowCounterLimiter.containsKey(httpClient.getClientId())) {
            Map<Integer, Integer> buckets = slidingWindowCounterLimiter.get(httpClient.getClientId());
            removeOlderTimeStamps2(buckets, clientLimits.get(httpClient.getClientId()).getDuration());
            if(findTotalCount(buckets, clientLimits.get(httpClient).getCount()))
                return false;
            buckets.put((int) (httpClient.getTimeStamp() % 60),
                        buckets.getOrDefault(httpClient.getTimeStamp(), 0) +1);
        } else {
            Map<Integer, Integer> buckets = new HashMap<>();
            buckets.put(0, 1);
            slidingWindowCounterLimiter.put(httpClient.getClientId(), buckets);
        }
        return true;
    }

    private void removeOlderTimeStamps2(Map<Integer, Integer> buckets, long duration) {
    }

    private boolean findTotalCount(Map<Integer, Integer> buckets, int count) {
        int sum =0;
        for(int val : buckets.values()) {
            sum += val;
        }
        return sum > count;
    }

    private boolean tokenBucket(HttpClient httpClient) {
        return true;
    }

    public static void main(String[] args) throws IOException {
        RateLimiter rateLimiter = new RateLimiter();
        rateLimiter.setClientThresholds();

        HttpClient client = new RateLimiter().new HttpClient();
        rateLimiter.fixedWindowAlgorithm(client);

        rateLimiter.slidingWindowAlgorithm(client);

        rateLimiter.slidingWindowCounter(client);
    }

    class HttpClient {
        int clientId;
        long timeStamp;

        public int getClientId() {
            return clientId;
        }

        public long getTimeStamp() {
            return timeStamp;
        }
    }

    class ClientLimitMapValue {
        int count;
        long duration;
        public int getCount() {
            return count;
        }

        public ClientLimitMapValue setCount(int count) {
            this.count = count;
            return this;
        }

        public long getDuration() {
            return duration;
        }

        public ClientLimitMapValue setDuration(long duration) {
            this.duration = duration;
            return this;
        }
    }

    class RateLimiterMapValue{
        int count;
        long lastTimeStamp;

        public int getCount() {
            return count;
        }

        public RateLimiterMapValue setCount(int count) {
            this.count = count;
            return this;
        }

        public long getLastTimeStamp() {
            return lastTimeStamp;
        }

        public RateLimiterMapValue setLastTimeStamp(long lastTimeStamp) {
            this.lastTimeStamp = lastTimeStamp;
            return this;
        }

        RateLimiterMapValue(int count, long lastTimeStamp) {
            this.count = count;
            this.lastTimeStamp = lastTimeStamp;
        }
    }

    /*
    boolean isAllowed(int clientid){
        Map<Integer, Queue<Integer>> mTimestamp;
        Queue<Integer> qTimestamp = mTimestamp[clientid];
        if(qTimestamp.size() < 100){
            qTimestamp.push(time(NULL));
            return true;
        }

        int lastTimestamp = qTimestamp.front();
        qTimestamp.pop();
        int curTimestamp = time(NULL);
        qTimestamp.push(curTimestamp);

        if(curTimestamp - lastTimestamp < 1) return false;

        return true;
    }
    */
}
