package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {


    private static Properties properties;

    static {
        properties = new Properties();
        FileInputStream fls = null;
        try {
            fls = new FileInputStream("testEnvis.properties");
            properties.load(fls);
            fls.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getTheProperties(String key){
        return properties.getProperty(key);
    }



}
