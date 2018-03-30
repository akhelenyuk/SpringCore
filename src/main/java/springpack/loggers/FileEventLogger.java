package springpack.loggers;

import org.apache.commons.io.FileUtils;
import springpack.event.Event;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(this.file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        System.out.println("Init");

        this.file = new File(filename);
        if(!file.canWrite()){
            throw new IOException("Cannot write to file " + filename);
        }
    }
}
