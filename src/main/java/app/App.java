package app;

import client.Client;
import event.Event;
import event.EventType;
import interfaces.IEventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Map;

public class App {

    private Client client;
    private IEventLogger eventlogger;
    private Event event;
    private Map<EventType, IEventLogger> loggers;

    public App(Client client, Event event, Map<EventType, IEventLogger> loggers) {
        this.client = client;
        this.event = event;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent(EventType.ERROR, "Initial error message for 1");
        app.logEvent(EventType.INFO, "Initial info message for 2");
        app.logEvent(EventType.ERROR, "Initial error message for 3");
        app.logEvent(EventType.INFO, "Initial info  message for 4");
        app.logEvent(EventType.ERROR, "Initial error message for 5");
        app.logEvent(EventType.INFO, "Initial info message for 6");

        ctx.close();

    }

    public void logEvent(EventType type, String message) {

        event.setMsg(message.replaceAll(client.getId(), client.getFullName()) + " " + client.getGreeting());
        System.out.println("Размер множества: "+ client.getValueSet().size());
        System.out.println("Elements: ");
//        new ArrayList(client.getValueSet()).stream().forEach(System.out::println);
        eventlogger = loggers.get(type);
        eventlogger.logEvent(event);
    }

}
