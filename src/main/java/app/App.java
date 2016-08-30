package app;

import client.Client;
import event.Event;
import event.EventType;
import interfaces.IConsoleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


public class App {

    @Autowired
    private Client client;
    private IConsoleLogger eventlogger;
    private Event event;
    private Map<EventType, IConsoleLogger> loggers;


    public App(Client client, Event event, Map<EventType, IConsoleLogger>loggers) {
        this.client = client;

        this.event = event;
        this.loggers = loggers;
    }



    private void logEvent(EventType type, String message) {

        event.setMsg(message.replaceAll(client.getId(), client.getFullName())+" "+client.getGreeting());
        eventlogger = loggers.get(type);
        eventlogger.logEvent(event);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");


        App app = (App) ctx.getBean("app");


        app.logEvent(EventType.ERROR, "Some message for 1");
        app.logEvent(EventType.INFO, "Some message for 2");
        app.logEvent(EventType.ERROR, "Some message for 3");
        app.logEvent(EventType.INFO, "Some message for 4");

        app.logEvent(EventType.ERROR, "Some mesage for 5");

        ctx.close();

    }

}
