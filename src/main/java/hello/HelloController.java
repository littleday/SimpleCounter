package hello;

import counter.LongAdderCounter;
import counter.SimpleCounter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Web Service provides three APIs:
 * 1. /hello
 * 2. /twilio
 * 3. /count
 * 3 will return the total number of http get requests
 * Created by Yuwen on 11/19/15.
 */
@RestController
public class HelloController {
    private SimpleCounter simpleCounter = new SimpleCounter();
    @RequestMapping("/hello")
    public String hello(){
        simpleCounter.increment();
        return new String("Welcome!");
    }

    @RequestMapping("/twilio")
    public String twilio(){
        simpleCounter.increment();
        return new String("Hello Twilio!");
    }

    @RequestMapping("/count")
    public String totalCount(){
        simpleCounter.increment();
        return new String("Total number of requests is: " + simpleCounter.get());
    }
}
