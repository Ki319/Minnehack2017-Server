package com.nebby.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.util.EntityUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server 
{
	
	public static List<Medication> medsList = new ArrayList<Medication>();

	public static void main(String[] args) throws Exception 
	{
		HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
		server.createContext("/addPill", new AddPill());
		//server.setExecutor(null); // creates a default executor
		//server.start();
		
		server.createContext("/takePill", new TakePill());
		//server.setExecutor(null); // creates a default executor
		//server.start();
		
		server.createContext("/pillsTaken", new PillsTaken());
		//server.setExecutor(null); // creates a default executor
		//server.start();
		
		server.createContext("/clear", new Clear());
		//server.setExecutor(null); // creates a default executor
		//server.start();
		
		server.createContext("/checkup", new Checkup());
		//server.setExecutor(null);
		//server.start();
		
		server.createContext("/bluetooth", new Bluetooth());
		server.setExecutor(null);
		server.start();
	}

	static class AddPill implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException 
		{
			System.out.println("Adding a new pill to the list");
			System.out.println(IOUtils.toString(t.getRequestBody()));
			Medication med = new Medication();
			med.timer = System.currentTimeMillis() + 60000;
			medsList.add(med);
			
			System.out.println(med.toString()+" added\n");
			t.sendResponseHeaders(200, 0);
			OutputStream os = t.getResponseBody();
			os.close();
		}
	}
	
	static class TakePill implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException {
			System.out.println("The user reports today's pills were taken");
			String earliest = "";
			for(Medication med: medsList){
				if(med.checked || med.timer < System.currentTimeMillis()){
					med.checked = false;
					earliest = med.getTime();
				}
			}
			
			
			System.out.println("All pills marked as taken\n");
			t.sendResponseHeaders(200, earliest.length());
			OutputStream os = t.getResponseBody();
			os.write(earliest.getBytes());
			os.close();
		}
	}
	
	static class PillsTaken implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException {
			System.out.println("The user is asking if it has taken today's pills");
			String toreturn = "";
			for(Medication med: medsList){
				if(med.checked || med.timer < System.currentTimeMillis()){
					toreturn+=med.getName()+",";
				}
			}

			System.out.println("The user has not taken "+toreturn+"\n");
			t.sendResponseHeaders(200, toreturn.length());
			OutputStream os = t.getResponseBody();
			os.write(toreturn.getBytes());
			os.close();
		}
	}
	
	static class Clear implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException {
			System.out.println("The user has cleared the list of medication\n");
			
			medsList.clear();
			t.sendResponseHeaders(200, 0);
			OutputStream os = t.getResponseBody();
			os.close();
		}
	}
	
	static class Checkup implements HttpHandler 
	{
		public void handle(HttpExchange t) throws IOException {
			System.out.println("Grandma has taken today's medicine!\n");
			t.sendResponseHeaders(200, 0);
			OutputStream os = t.getResponseBody();
			os.close();
		}
	}
	
	static class Bluetooth implements HttpHandler
	{

		@Override
		public void handle(HttpExchange t) throws IOException
		{
			boolean found = false;
			for(Medication med : medsList)
			{
				if(med.timer < System.currentTimeMillis())
				{
					found = true;
					med.checked = true;
					med.timer = System.currentTimeMillis() + 60000;
				}
			}
			System.out.println("Notifying the user that they need to take their medicine\n");
			String response = "" + found;
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
		
	}

	
}
