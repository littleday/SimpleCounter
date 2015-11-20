package counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Yuwen on 11/19/15.
 */
public class AtomicLongCounter implements Counter {
    private AtomicLong count = new AtomicLong();

    public AtomicLongCounter(){
    }

    public void increment(){
        count.incrementAndGet();
    }

    public long get(){
        return count.get();
    }
}
