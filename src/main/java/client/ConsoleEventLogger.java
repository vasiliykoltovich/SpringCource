package client;


import event.Event;
import interfaces.IConsoleLogger;

public class ConsoleEventLogger implements IConsoleLogger {

	public void logEvent(Event event){
		System.out.println(event.toString());
	}

	
}
