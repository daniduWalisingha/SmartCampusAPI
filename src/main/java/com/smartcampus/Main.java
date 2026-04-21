package com.smartcampus;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import com.smartcampus.config.SmartCampusApplication;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {
        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                new SmartCampusApplication()
        );
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        System.out.println("Smart Campus API server started.");
        System.out.println("Base URL: " + BASE_URI + "api/v1");
        System.out.println("Press ENTER to stop the server...");
        System.in.read();
        server.shutdownNow();
    }
}