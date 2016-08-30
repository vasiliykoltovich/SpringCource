package client;


import event.Event;
import interfaces.IConsoleLogger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CombinedEventLogger implements IConsoleLogger {

	private List<IConsoleLogger> loggers=new ArrayList<IConsoleLogger>();
	
	
	
	
	public CombinedEventLogger(List<IConsoleLogger> loggers) {
		
		this.loggers = loggers;

	}




	@Override
	public void logEvent(Event event) {
		for (IConsoleLogger logger:loggers){
			logger.logEvent(event);
		}

	}

}
