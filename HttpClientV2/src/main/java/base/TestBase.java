package base;

import com.sun.deploy.ref.Helpers;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Requirement and steps :-
 * Import following dependencies in POM.XML
 * 1. httpClint
 * 2. httpCore
 * 3. json
 * 4. testNG
 *
 *
 * Steps:-
 * 1. Create base and config packages
 * 2. Put URL and serviceURL in config.properties class present in config package
 * 3. Now, create constructor of BaseTest class present in base package and write code to read config.properties file
 *
 *

 */

public class TestBase {

    public Properties prop;
    public int STATUS_CODE_200 = 200;
    public int STATUS_CODE_201 = 201;


    public TestBase()
    {
        try
        {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
            prop.load(ip);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException i){
            i.printStackTrace();
        }

    }
}
