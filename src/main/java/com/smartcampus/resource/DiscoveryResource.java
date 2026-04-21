package com.smartcampus.resource;

import java.util.HashMap;
import java.util.Map;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    @GET
    public Map<String, Object> discover() {
        Map<String, Object> response = new HashMap<>();

        response.put("apiName", "Smart Campus Sensor & Room Management API");
        response.put("version", "v1");
        response.put("adminContact", "admin@smartcampus.local");

        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");
        resources.put("createRoom", "POST /api/v1/rooms");
        resources.put("createSensor", "POST /api/v1/sensors");

        response.put("resources", resources);

        return response;
    }
}