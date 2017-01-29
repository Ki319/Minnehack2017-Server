package com.nebby.server;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class Main 
{
    public static void main(String argv[]) throws ClientProtocolException, IOException
    {
        String url = "http://ec2-54-172-226-18.compute-1.amazonaws.com:8888/test";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);


        HttpResponse response = client.execute(request);
        System.out.println("http response = "+response.toString());
    }
}
