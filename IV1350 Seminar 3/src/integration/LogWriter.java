package integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * This class is responsible for the logging of the program.
 */
public class LogWriter {
    
    private PrintWriter logFile;
    
    /**
     * Creates a new instance of LogHandler.
     * @throws IOException 
     */
    public LogWriter() throws IOException {
        logFile = new PrintWriter(new FileWriter("errorLog.txt"), true);
    }
    
    /**
     * Writes to the log describing an exception that was thrown.
     * 
     * @param exception The exception to be logged.
     */
    public void logException(Exception exception) {
        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append(java.time.LocalDateTime.now());
        logMessageBuilder.append(", Exception thrown: ");
        logMessageBuilder.append(exception.getMessage());
        logFile.println(logMessageBuilder);
        exception.printStackTrace(logFile);
    }
    
    
}