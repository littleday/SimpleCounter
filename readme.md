# SimpleCounter
## There are three implementations:
1. My implementation is SimpleCounter, which keeps a counter for each thread, indexed by a concurrent hashmap
2. LongAdderCounter is based on LongAdder, which implements a similar method with mine
3. AtomicLongCounter is based on AtomicLong, which is a lock protected long

## Test Counters Result
The expected performance: LongAdderCounter is better than SimpleCounter, given its more efficient implementations.
And SimpleCounter is better the AtomicLongCounter.
As expected, an example on my machine gives the following results for 100,000 threads, each of which generates 10,000 counts:
```
Count number is 1000000000
Count number is 1000000000
Count number is 1000000000
SimpleCounter duration is: 14705
LongAdderCounter duration is: 8750
AtomicLongCounter duration is: 26153
```

The time unit is milliseconds
The test framework is Junit

## Test Web Service
In the command line, please run the following command to start the Springboot application:

> java -jar SimpleCounter-0.1.0.jar

Then visit in any browser:
> http://localhost:8080/hello
> http://localhost:8080/twilio
> http://localhost:8080/count

When you visit /count api, the page will shows the total number of counts




