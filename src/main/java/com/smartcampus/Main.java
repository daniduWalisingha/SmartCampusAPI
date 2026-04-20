/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus;
import java.net.URI;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import com.smartcampus.config.SmartCampusApplication;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;


/**
 *
 * @author User
 */
public class Main {
    public static final String BASE_URI = "http://localhost:8080/";
    public static HttpServer startServer(){
        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),new SmartCampusApplication());
    }
    
    public static void main (String[] args)throws IOException{
        HttpServer server = startServer();
        System.out.println("Smart Campus API server started.");
        System.out.println("Base URL: " + BASE_URI + "api/v1");
        System.out.println("Press ENTER to stop the server...");
        System.in.reset();
        server.shutdownNow();
        
    }
    
    
    
}
