package com.nebby.server;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Main 
{
    public static void main(String argv[]) throws ClientProtocolException, IOException
    {
        String url = "http://127.0.0.1:8888/test";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);


        HttpResponse response = client.execute(request);
        
        String body = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println("http response = "+body);
    }
}
