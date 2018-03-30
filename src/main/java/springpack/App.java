package springpack;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springpack.event.Event;
import springpack.loggers.EventLogger;
import springpack.event.EventType;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public App() {
    }

    public App(Client client, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.loggers = loggers;
    }

    public void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = defaultLogger;
        }
        String message = event.getMsg().replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");
        App app = context.getBean(App.class);

        addEvents(app, context);

        context.close();

    }

    private static void addEvents(App app, ConfigurableApplicationContext context) {
        Event event = context.getBean(Event.class);
        event.setMsg("Event 1 user 1");
        app.logEvent(EventType.ERROR, event);

        event = context.getBean(Event.class);
        event.setMsg("Event 2 user 1");
        app.logEvent(EventType.INFO, event);

        event = context.getBean(Event.class);
        event.setMsg("Event 3 user 1");
        app.logEvent(EventType.ERROR, event);

        event = context.getBean(Event.class);
        event.setMsg("Event 4 user 1");
        app.logEvent(null, event);
    }
}
