package client;


import event.Event;
import interfaces.IConsoleLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class FileEventLogger implements IConsoleLogger {

	private String filename;
	private File file;
	
	 public FileEventLogger(String filename) {
		this.filename=filename;
		
	}
	
	 @PostConstruct
	 public void init() throws IOException{
		 
		 this.file=new File(filename);
		
	 }

	 @PostConstruct
	 public void ride(){
         System.out.println("Let's ride suckers");
     }
	 
	 
@SuppressWarnings("deprecation")
public void writeEventsFromCache(List<Event> cache){
	
	for(Event event:cache){
	try {
		FileUtils.writeStringToFile(file, event.toString()+"\n", true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	 }
	 
	@SuppressWarnings("deprecation")
	public void logEvent(Event event) {
		
		try {
			
			FileUtils.writeStringToFile(file, event.toString()+"\n", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
