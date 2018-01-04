package client;

import event.Event;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cachesize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, String logfile) {
        super(filename,logfile);

    }

    public CacheFileEventLogger(String filename,String logfile, int cachesize) {
        super(filename,logfile);
        this.cachesize = cachesize;
        this.cache = new ArrayList<Event>(cachesize);

    }

    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache(cache);
        }
    }

    @PreDestroy
    public void lastGoodbye() {
        System.out.println("Goodbye loosers!!!");
    }

    public void logEvent(Event event) {
        cache.add(event);
        System.out.println("event " + cache.get(0).toString());
        if (cache.size() == cachesize) {

            writeEventsFromCache(cache);
            cache.clear();
        }

    }

}
