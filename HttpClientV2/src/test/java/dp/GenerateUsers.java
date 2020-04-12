package dp;

import helpers.Helpers;
import org.testng.annotations.DataProvider;
import pojo.Users;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GenerateUsers {




    public Properties prop;
    public int STATUS_CODE_200 = 200;
    public int STATUS_CODE_201 = 201;



    public GenerateUsers()
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



    @DataProvider(name = "generateUsers")
    public static Object[][] generateUsers() throws IOException {
        Users user = Users.builder().name("Tom").job("Engineer").build();
        return new Object[][]
                {
                        {Helpers.objToJson(user)}
                };
    }
}

