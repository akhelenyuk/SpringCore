package springpack.loggers;

import springpack.event.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event){
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        System.out.println("Writing events from cache");
        cache.forEach(super::logEvent);
//        for (Event e: cache
//             ) {
//            super.logEvent(e);
//        }
    }

    public void destroy() {
        System.out.println("Destroy");

        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
