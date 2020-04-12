package tests;

import base.TestBase;
import client.RestClient;
import dp.GenerateUsers;
import helpers.Helpers;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.Users;
import util.TestUtils;

import java.io.IOException;
import java.util.HashMap;

public class TestClass extends TestBase {

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

    @Test
    public void getTest() throws IOException {
        restClient = new RestClient();
        httpResponse = restClient.get(uri);
        //status code
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("status code is :===>" + statusCode);
        Assert.assertEquals(statusCode, STATUS_CODE_200, "status code is not 200");

        //json string
        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8"); //converts httpResponse to pure String
        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("response json from api is ===>" + jsonObject);
        String perPage = TestUtils.getValueByJPath(jsonObject, "per_page");
        System.out.println("per page is "+perPage);


        //all headers from response
        Header[] headersArray = httpResponse.getAllHeaders();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        for (Header header : headersArray) {
            headerMap.put(header.getName(), header.getValue());
        }
        System.out.println("headers are ====> " + headerMap);
    }

    @Test
    public void getTestUsingHeader() throws IOException {
        restClient = new RestClient();
        HashMap<String,String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
//        headerMap.put("username", "venkatesh@Automation");
//        headerMap.put("password", "venkatesh");
//        headerMap.put("Auth Token", "1234567");
        httpResponse = restClient.get(uri, headerMap);
        //status code
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("status code is :===>" + statusCode);
        Assert.assertEquals(statusCode, STATUS_CODE_200, "status code is not 200");

        //json string
        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8"); //converts httpResponse to pure String
        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("response json from api is ===>" + jsonObject);
        String perPage = TestUtils.getValueByJPath(jsonObject, "per_page");
        System.out.println("per page is "+perPage);


        //all headers from response
        Header[] headersArray = httpResponse.getAllHeaders();
        HashMap<String, String> headerMap1 = new HashMap<String, String>();
        for (Header header : headersArray) {
            headerMap1.put(header.getName(), header.getValue());
        }
        System.out.println("headers are ====> " + headerMap1);
    }


    /**
     *This test is having data provider implementation using same code in helper instead of data provider. Marshalling is working fine but
     * un-marshalling is throwing some error hence gonna create new class where I will be using traditional way of creating pojo instead of
     * lombok pojo builder
     */

    @Test(description = "validate post call by creating a new entity")
    public void postTest() throws IOException {


        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");


        String usersPojo = "pojo.Users";
        Users users = Helpers.generateUsers();
        String payloadData = Helpers.objToJson(users);
        System.out.println(payloadData);
        httpResponse = restClient.post(uri, payloadData, headerMap);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, STATUS_CODE_201, "Status is not 201");

        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("response json from api is ===>" + jsonObject);

        Users usersResponseObj = (Users) Helpers.jsonToObj(responseString, "pojo.Users");
        System.out.println("===========>"+usersResponseObj);



    }


}
