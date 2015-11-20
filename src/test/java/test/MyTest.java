package test;

import counter.AtomicLongCounter;
import counter.Counter;
import counter.LongAdderCounter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import counter.SimpleCounter;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yuwen on 11/19/15.
 */
public class MyTest {
    private LongAdderCounter longAdderCounter = new LongAdderCounter();
    private SimpleCounter simpleCounter = new SimpleCounter();
    private AtomicLongCounter atomicLongCounter = new AtomicLongCounter();
    // Three test threads are started every second
    private int numThread = 100000;
    // Number of counts per thread
    private int wordLoad = 10000;

    @Test
    public void Compare(){
        long simpleDuration = setUp(simpleCounter);
        long longAdderDuration = setUp(longAdderCounter);
        long atomicLongDuration = setUp(atomicLongCounter);
        System.out.println("SimpleCounter duration is: " + simpleDuration);
        System.out.println("LongAdderCounter duration is: " + longAdderDuration);
        System.out.println("AtomicLongCounter duration is: " + atomicLongDuration);

    }

    public long setUp(Counter counter){
        long begin = new Date().getTime();
        List<Thread> threadList = new ArrayList<Thread>();
        for(int i = 0; i < numThread; i++){
            Thread t = new Thread(new Increment(counter, wordLoad));
            threadList.add(t);
            t.start();
        }
        try {
            for (int i = 0; i < threadList.size(); i++) {
                threadList.get(i).join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(new GetValue(counter)).run();
        assertEquals(numThread * wordLoad, counter.get());
        long end = new Date().getTime();
        return end - begin;
    }

    private class Increment implements Runnable{
        private int workLoad;
        private Counter counter;

        public Increment(Counter c, int init){
            workLoad = init;
            counter = c;
        }
        public void run(){
            for(int i = 0; i < workLoad; i++) {
                counter.increment();
            }
        }
    }

    private class GetValue implements Runnable{
        private Counter counter;
        public GetValue(Counter counter){
            this.counter = counter;
        }
        public void run(){
            try {
                Thread.sleep(10);
                System.out.println("Count number is " + counter.get());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
