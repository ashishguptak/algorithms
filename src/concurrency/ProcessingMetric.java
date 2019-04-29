/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package concurrency;

import org.testng.Assert;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class ProcessingMetric {

    private AtomicLong processedCount;

    private AtomicLong successCount;

    private AtomicLong failureCount;

    public ProcessingMetric() {
        processedCount = new AtomicLong(0L);
        successCount = new AtomicLong(0L);
        failureCount = new AtomicLong(0L);
    }

    public void incrementProcessedCount() {
        long val = processedCount.incrementAndGet();
        System.out.println("processed val: "+ val + " thread id:" + Thread.currentThread().getName());
    }

    public void incrementSuccessCount() {
        long val = successCount.incrementAndGet();
        System.out.println("success val: "+ val + " thread id:" + Thread.currentThread().getName());
    }

    public void incrementFailureCount() {
        long val = failureCount.incrementAndGet();
        System.out.println("failure val: "+ val + " thread id:" + Thread.currentThread().getName());
    }

    public long getProcessedCount() {
        return processedCount.longValue();
    }

    public long getSuccessCount() {
        return successCount.longValue();
    }

    public long getFailureCount() {
        return failureCount.longValue();
    }

    public static void main(String[] args) throws InterruptedException {

        ProcessingMetric processingMetric = new ProcessingMetric();
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //for(int k = 0; k < 10; k ++) {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    for (int j=0; j< 10; j ++) {
                    processingMetric.incrementProcessedCount();
                    boolean flag = random.nextBoolean();
                    if (flag) processingMetric.incrementSuccessCount();
                    else processingMetric.incrementFailureCount();
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(50, TimeUnit.MILLISECONDS);

            System.out.println(processingMetric);
            Assert.assertEquals(processingMetric.getProcessedCount(),
                    processingMetric.getFailureCount() + processingMetric.getSuccessCount());
        //}
    }

    @Override
    public String toString() {
        return "processedCount =" + processedCount.longValue()
                 + " successCount =" + successCount.longValue()
                    + " failureCount =" + failureCount.longValue();
    }
}
