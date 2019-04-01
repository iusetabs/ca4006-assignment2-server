package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//java spring will handle the conversion of objects back to JSON.

@RestController //Shorthand for @RESPONSEBODY. Indicates that domain object will be returned.
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting") //ensures that HTTP requests to /greeting are mapped to the greeting() method.
    //@RequestMapping(method=GET) to narrow this mapping - if we want to map it to a specific REST call
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        //@RequestParam binds value of query string parameter to name. Default value.
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
