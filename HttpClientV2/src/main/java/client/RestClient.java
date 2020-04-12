package client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    /**
     * GET Method
     */
    //GET method without headers
    public CloseableHttpResponse get(String uri) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri); //http get request
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        return httpResponse;
    }

    //GET method with headers
    public CloseableHttpResponse get(String uri, HashMap<String, String> header) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri); //http get request

        //for headers
        for (Map.Entry<String, String> entry : header.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        return httpResponse;
    }

    /**
     * POST Method
     * @return
     */
    public CloseableHttpResponse post(String uri, String payload, HashMap<String, String> header) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri); // http post request
        httpPost.setEntity(new StringEntity(payload));// set payload body

        //for headers
        for (Map.Entry<String, String> entry : header.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        return httpResponse;
    }


}
