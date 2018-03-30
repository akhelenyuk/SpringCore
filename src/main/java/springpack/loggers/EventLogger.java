package springpack.loggers;

import springpack.event.Event;

public interface EventLogger {
    void logEvent(Event event);
}
