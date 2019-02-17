/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays.Streams;

/**
 * https://www.geeksforgeeks.org/average-of-a-stream-of-numbers/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class AverageFromStream {

    private void streamAvg(float[] arr, int n) {
        double prevAvg =0.0, currAvg = 0.0;
        int prevTotal =0;
        for(int i=0; i <arr.length; i++) {
            currAvg = (prevAvg*prevTotal + arr[i]) / (prevTotal+1);

            System.out.println("avg till i elements: "+(i +1) +" is " + currAvg);
            prevAvg = currAvg;
            prevTotal = prevTotal+1;
        }
    }

    public static void main(String[] args)
    {
        AverageFromStream averageFromStream = new AverageFromStream();
        float arr[] = { 10, 20, 30, 40, 50, 60 };
        int n = arr.length;
        averageFromStream.streamAvg(arr, n);
    }
}
