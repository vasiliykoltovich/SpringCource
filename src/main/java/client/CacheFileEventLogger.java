package client;



import event.Event;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


public class CacheFileEventLogger extends FileEventLogger {

	private int cachesize;
	private List<Event> cache;
	
	

	public CacheFileEventLogger(String filename) {
		super(filename);

		// TODO Auto-generated constructor stub
	}
	
	public CacheFileEventLogger(String filename, int cachesize) {
		super(filename);
		this.cachesize = cachesize;
		this.cache = new ArrayList<Event>(cachesize);
		
	}

    @PreDestroy
	public void destroy(){
		if(!cache.isEmpty()){
			writeEventsFromCache(cache);
		}
	}

	@PreDestroy
	public void coco(){
		System.out.println("Goodbye loosers!!!");
	}

	public void logEvent(Event event) {
		cache.add(event);
		System.out.println("event "+cache.get(0).toString());
		if(cache.size()==cachesize){
			
			writeEventsFromCache(cache);
			cache.clear();
		}

	}

}
