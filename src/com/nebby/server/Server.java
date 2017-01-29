package com.nebby.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server 
{

	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
		server.createContext("/addPill", new AddPill());
		server.setExecutor(null); // creates a default executor
		server.start();
		
		server.createContext("/takePill", new TakePill());
		server.setExecutor(null); // creates a default executor
		server.start();
		
		server.createContext("/pillsTaken", new PillsTaken());
		server.setExecutor(null); // creates a default executor
		server.start();
		
		server.createContext("/clear", new Clear());
		server.setExecutor(null); // creates a default executor
		server.start();
	}

	static class AddPill implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException 
		{
			String response = "Welcome Real's HowTo test page";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
	
	static class TakePill implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException {
			String response = "Welcome Real's HowTo test page";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
	
	static class PillsTaken implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException {
			String response = "Welcome Real's HowTo test page";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
	
	static class Clear implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException {
			String response = "Welcome Real's HowTo test page";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

}
