package Java8; /**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * @author ashish gupta (akonda@expedia.com)
 */
class MyRunnableThreadClass extends Thread {
    static String msg[] = {"Java", "is", "hot,", "aromatic,", "and", "invigorating."};

    public MyRunnableThreadClass(String str) {
        super(str);
    }

    @Override
    public void run() {
        /*
        for (String s: msg) {
            randomWait();
            System.out.println(name + " :" + s);
        }
        */
        ConsoleLogger.print(getName(), msg);
    }

    protected void randomWait() {
        try {
            long dur = (long) (Math.random() * 1000);
            Thread.sleep(dur);
            System.out.println(dur);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConsoleLogger {
    public static synchronized void print(String name,String[] msg) {

        for(int i=0; i<msg.length; i++) {
            MyRunnableThreadClass t = (MyRunnableThreadClass) Thread.currentThread();
            t.randomWait();
            System.out.println(name+": "+msg[i]);
        }
    }
}

class ThreadTest {

    public static void main(String[] args) {
        MyRunnableThreadClass t1 = new MyRunnableThreadClass("Thread1");
        MyRunnableThreadClass t2 = new MyRunnableThreadClass("Thread2");
        t1.start();
        t2.start();
    }
}
