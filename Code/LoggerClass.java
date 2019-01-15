import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerClass {
	public Logger loggerMethod() {
	Logger logger = Logger.getLogger("MyLog");  
    FileHandler fileHandler;  

    try {  
    	fileHandler = new FileHandler("D:\\temp.log",true);  
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();  
        fileHandler.setFormatter(formatter); 
          
    } catch (SecurityException e) {  
        e.printStackTrace();  
    } catch (IOException e) {  
        e.printStackTrace();  
       
    }
	return logger;  
	}
}
