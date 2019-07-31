package com.test.elk.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticApi {

    //@Value("${elasticsearch.host}")
    private String host = "192.168.99.100";

    //@Value("${elasticsearch.port}")
    private int port = 9200;
    
    /**
     * @param method
     * @param url
     * @param obj
     * @param jsonData
     * @return
     */
    public Map<String, Object> callElasticApi(String method, String url, Object obj, String jsonData) {
        Map<String, Object> result = new HashMap<>();
        String jsonString;
        if (jsonData == null) {
            Gson gson = new Gson();
            jsonString = gson.toJson(obj);
        } else {
            jsonString = jsonData;
        }
        try(RestClient restClient = RestClient.builder(new HttpHost(host, port)).build()) {
            Map<String, String> params =  Collections.singletonMap("pretty", "true");
            Response response = null;
            //
            System.out.println("method:"+method+"\n url:"+url);
            Request request = new Request(method, url);
            if (method.equals("GET") || method.equals("DELETE")) {
            	
            	request.addParameter("pretty", "true"); //params
            	
            	response = restClient.performRequest(request);
//              response = restClient.performRequest(method, url, params);
            } else {
                HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
                
                request.addParameter("pretty", "true"); //params
                request.setEntity(entity);
                
                response = restClient.performRequest(request);
//              response = restClient.performRequest(method, url, params, entity);
                
                restClient.close();
            }
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            result.put("resultCode", statusCode);
            result.put("resultBody", responseBody);
        } catch (Exception e) {
            result.put("resultCode", -1);
            result.put("resultBody", e.toString()+"\n");
            e.printStackTrace();
        }
        return result;
    }
}