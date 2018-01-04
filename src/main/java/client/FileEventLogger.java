package client;

import event.Event;
import interfaces.IEventLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
public class FileEventLogger implements IEventLogger {

    private String filename;
    private String logfile;
    private File file;
    private File log;

    public FileEventLogger(String filename, String logfile) {
        this.filename = filename;
        this.logfile = logfile;

    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(filename);
        this.log = new File(logfile);

    }

    @PostConstruct
    public void ride() {
        System.out.println("Let's ride suckers");
    }

    @SuppressWarnings("deprecation")
    public void writeEventsFromCache(List<Event> cache) {

        for (Event event : cache) {
            try {
                FileUtils.writeStringToFile(file, event.toString() + "\n", true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void logEvent(Event event) {
        try {
            Files.write(Paths.get(logfile), (event.toString() + System.lineSeparator()).getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
