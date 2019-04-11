package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

//java spring will handle the conversion of objects back to JSON.

@Configuration
@EnableAsync
@Controller
@RestController //Shorthand for @RESPONSEBODY. Indicates that domain object will be returned.
public class BookingsController {

    // Which is auto-generated by Spring, we will use it to handle the data
    private final BookingsRepository bookingsRepository;

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    public BookingsController(BookingsRepository xbookingsRepository) {
        this.bookingsRepository = xbookingsRepository;
    } //Used to be @Autowired but this is bad practice. Replace with constructor for interface.
    // This means to get the bean called userRepository

    @RequestMapping(path="/bookings", method=GET)
    public @ResponseBody Iterable<Booking> getAllBookings() throws ExecutionException, InterruptedException {
        Future<Iterable<Booking>> future = asyncBooking();
        while (true) {
            if (future.isDone()) {
                return future.get();
            }
            Thread.sleep(1000);
        }
    }

    @Async("threadPoolTaskExecutor")
    public Future<Iterable<Booking>> asyncBooking(){
        try {
            Thread.sleep(1000);
            // This returns a JSON or XML with the users
            return new AsyncResult<>(bookingsRepository.findAll());
        }
        catch (InterruptedException e){
            return new AsyncResult<>(null);
        }
    }

    @Async("threadPoolTaskExecutor")
    @RequestMapping(path="/bookings", method=POST)
    public @ResponseBody String addBooking(@RequestBody Booking bk) {
        for (Booking b : bookingsRepository.findAll()){
            if (bk.getRoom_name().equals(b.getRoom_name()))
                if(bk.getDay() == b.getDay())
                    if (bk.getTime().equals(b.getTime()))
                        return "Error, booking already exists";
        }
        this.bookingsRepository.save(bk);
        return "Success booking made";
    }

    @Async("threadPoolTaskExecutor")
    @RequestMapping(path="/bookings", method=DELETE)
    public @ResponseBody String deleteAllBookings() {
        try{
            this.bookingsRepository.deleteAll();
            return "All booking objects have been deleted.";
        }
        catch(Exception e){
            return "Fail: " + e.getMessage();
        }
    }

    @Async("threadPoolTaskExecutor")
    @RequestMapping(path="/timetableWeek/rooms/{name}/startDay/{dayS}/endDay/{dayE}", method=GET)
    public @ResponseBody List<Booking> getBookingsForRoomForWeek(@PathVariable String name, @PathVariable Integer dayS, @PathVariable Integer dayE) {
        List<Booking> resp = new ArrayList<>();
        for (Booking b : bookingsRepository.findAll()){
            if (b.getRoom_name().equals(name) && b.getDay() >= dayS && b.getDay() <= dayE)
                resp.add(b);
        }
        return resp;
    }
    @Async("threadPoolTaskExecutor")
    @RequestMapping(path="/checkRoom/rooms/{name}/day/{day}/time/{time}", method=GET)
    public @ResponseBody List<Booking> getBookingsForRoomForDayAndTime(@PathVariable String name, @PathVariable Integer day, @PathVariable String time) {
        // This returns a JSON or XML with the users
        List<Booking> resp = new ArrayList<>();
        for (Booking b : bookingsRepository.findAll()){
            if (b.getRoom_name().equals(name) && b.getDay() == day && b.getTime().equals(time))
                resp.add(b);
        }
        return resp;
    }

}