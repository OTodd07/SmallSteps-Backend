package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SmallStepsController {

    private final AtomicLong idGenerator = new AtomicLong();
    private final ConcurrentSkipListSet<Long> walkers = new ConcurrentSkipListSet<>();

    @RequestMapping("/startingWalk")
    public OnlineData startingWalk(@RequestParam(value="name", defaultValue="Unnamed") String name) {
        int numberOfWalkers = walkers.size();
        long id = idGenerator.getAndIncrement();

        walkers.add(id);
        return new OnlineData(id, numberOfWalkers);
    }

    @RequestMapping("/stoppingWalk")
    public OnlineData stoppingWalk(@RequestParam(value="id", defaultValue="0") String id) {
        walkers.remove(Long.parseLong(id));
        return new OnlineData(Long.parseLong(id), walkers.size());
    }

    class OnlineData {
        private final long id;
        private final int numberOfWalkers;

        public OnlineData(long id, int numberOfWalkers) {
            this.id = id;
            this.numberOfWalkers = numberOfWalkers;
        }

        public long getId() {
            return id;
        }

        public int getNumberOfWalkers() {
            return numberOfWalkers;
        }
    }

}
