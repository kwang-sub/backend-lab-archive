package study.java.collection.map;

import java.io.FileReader;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        String path = PropertiesExample.class.getResource("database.properties").getPath();
        path = URLDecoder.decode(path,"UTF-8");
        properties.load(new FileReader(path));

        String driver = properties.getProperty("driver");

        System.out.println(driver);
    }
}
