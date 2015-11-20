package counter;

import java.util.concurrent.atomic.LongAdder;

/**
 * Counter.java: based on LongAdder
 * Created by Yuwen on 11/19/15.
 */

public class LongAdderCounter implements Counter{
    private LongAdder count = new LongAdder();

    public LongAdderCounter(){

    }

    public void increment(){
        count.increment();
    }

    public long get(){
        return count.longValue();
    }
}
