package tests;

import base.TestBase;
import client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.TraditionalUsersPojo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UsingTraditionalPojo extends TestBase {


    private TestBase testBase;
    private String apiUrl;
    private String url;
    private String uri;
    private RestClient restClient;
    CloseableHttpResponse httpResponse;


    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        url = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        uri = url + apiUrl;

    }

    @Test(description = "validate post call by creating a new entity")
    public void postTest() throws IOException {

        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        //converting pojo(java object) to json - Marshalling
        ObjectMapper mapper = new ObjectMapper();
        TraditionalUsersPojo users = new TraditionalUsersPojo("Tina", "Singer");
        mapper.writeValue(new File("Users/Venkatesh/HttpClientV2/src/test/java/files/Users.json"), users);

        //object to json in string for payload request
        String usersJsonString = mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        //status code from response
        httpResponse =  restClient.post(uri, usersJsonString, headerMap);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201);

        // string body from response
        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);

        //string to json from response
        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println("The json response from API is ==>"+responseJSON);

        //un-marshalling : for converting json to java object
        TraditionalUsersPojo usersRespObj = mapper.readValue(responseString, TraditionalUsersPojo.class);
        System.out.println("json to obj conversion data is===>"+usersRespObj);



    }

}
