package tests;

import base.TestBase;
import client.RestClient;
import dp.GenerateUsers;
import helpers.Helpers;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;


/**
 * This class includes test having TestBase code and data provider code together in order to get it extend from class
 */


public class PostTest extends GenerateUsers {


    private TestBase testBase;
    private String apiUrl;
    private String url;
    private String uri;
    private RestClient restClient;
    CloseableHttpResponse httpResponse;


    @BeforeMethod
    public void setUp() {
        url = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        uri = url + apiUrl;

    }

    @Test(dataProvider = "generateUsers", description = "validate post call by creating a new entity")
    public void postTest(String payloadData) throws IOException {


        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        System.out.println(payloadData);
        httpResponse = restClient.post(uri, payloadData, headerMap);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, STATUS_CODE_201);

        httpResponse.getEntity();

    }


}
