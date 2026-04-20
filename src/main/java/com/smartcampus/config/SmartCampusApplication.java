/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.config;
import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.ApplicationPath;

/**
 *
 * @author User
 */
@ApplicationPath("/api/v1")
public class SmartCampusApplication extends ResourceConfig{
    public SmartCampusApplication(){
        packages("com.smartcampus.resource",
                 "com.smartcampus.mapper",
                 "com.smartcampus.filter");
    }
    
}
