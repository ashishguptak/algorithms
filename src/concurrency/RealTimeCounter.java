/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package concurrency;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/multithreaded/RealTimeCounter.java
 *
 *
 *  http://blog.gainlo.co/index.php/2016/09/12/dropbox-interview-design-hit-counter/
 *
 * Develop a software to count number of events in last 5 mins. You have to support two apis
 * 1) addEvent() -> It means increment event by 1
 * 2) getTotalEvents() -> Return total number of events in last 5 mins
 *
 * Program should support millions of events every minute and should also provide multi-threading support
 *
 * This class might not have 100% accuracy as far as events in last 5 mins are concerned.
 * Since we are using circular queue last second information may not be very accurate.
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class RealTimeCounter {
    private AtomicLong counter;
    private AtomicReference<Timestamp> currTimeStamp;

    private static final long TIMER_TO_CHECK_IN_MS = 50;

    public RealTimeCounter() {
        counter = new AtomicLong(0L);
        currTimeStamp = new AtomicReference<Timestamp>(new Timestamp(System.currentTimeMillis()));
    }

    public void addEvent() {
        if(currTimeStamp.get().getTime() + TIMER_TO_CHECK_IN_MS < System.currentTimeMillis()) {
            System.out.println("yo bitch "+ Thread.currentThread().getName());
            if(currTimeStamp.compareAndSet(currTimeStamp.get(), new Timestamp(System.currentTimeMillis())))
                System.out.println("yo bitch 2 "+ Thread.currentThread().getName());
        }
        counter.incrementAndGet();
    }

    public Long getTotalEvents() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        RealTimeCounter realTimeCounter = new RealTimeCounter();
        ExecutorService exCaller = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            exCaller.execute(() -> {
                for (int k = 0; k < 100000; k++)
                    realTimeCounter.addEvent();
                realTimeCounter.getTotalEvents();

            });
        }
/*
        ExecutorService exReader = Executors.newFixedThreadPool(4);
        exReader.execute(() -> {
            System.out.println("total count is "+ realTimeCounter.getTotalEvents());
        });
*/
        exCaller.shutdown();
        exCaller.awaitTermination(5, TimeUnit.MILLISECONDS);

 //       exReader.shutdown()
        //     exReader.awaitTermination(5, TimeUnit.MILLISECONDS);
    }

}
