package client;

import event.Event;
import interfaces.IEventLogger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CombinedEventLogger implements IEventLogger {

    private List<IEventLogger> loggers;

    public CombinedEventLogger(List<IEventLogger> loggers) {

        this.loggers = loggers;

    }

    @Override
    public void logEvent(Event event) {
        for (IEventLogger logger : loggers) {
            logger.logEvent(event);
        }

    }

}
