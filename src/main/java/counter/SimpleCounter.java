package counter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A concurrent counter which allow multi threads to access at the same time
 * Created by Yuwen on 11/19/15.
 */
public class SimpleCounter implements Counter {
    // As long is immutable, using Long[] guarantee that each thread can update its own counter without updating the hashmap.
    private ConcurrentHashMap<Long, Long[]> counters;

    public SimpleCounter() {
        counters = new ConcurrentHashMap<Long, Long[]>();
    }

    public void increment() {
        Long tid = Thread.currentThread().getId();
        Long[] counter = counters.get(tid);
        if (counter == null) {
            counters.put(tid, new Long[] {1L});
        } else {
            counter[0]++;
        }
    }

    //get() does not lock the value
    public long get(){
        Long sum = 0L;
        for (Long[] counter : counters.values()) {
            sum += counter[0];
        }
        return sum;
    }
}
