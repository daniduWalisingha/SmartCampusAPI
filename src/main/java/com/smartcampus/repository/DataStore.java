package com.smartcampus.repository;

import com.smartcampus.model.Room;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataStore {
    private static final Map<String, Room> roomsMap = new ConcurrentHashMap<>();
    private static final Map<String, Sensor> sensorsMap = new ConcurrentHashMap<>();
    private static final Map<String, List<SensorReading>> sensorReadingsMap = new ConcurrentHashMap<>();
    
    // Seed the DataStore with some initial data for demonstration and testing purposes
    static {
        // --- Seed Room 1: Library ---
        Room library = new Room("LIB-301", "Library Quiet Study", 120);
        roomsMap.put(library.getId(), library);

        // Seed Sensor for Library
        Sensor tempSensor = new Sensor("TEMP-001", "Temperature", "ACTIVE",22.5, library.getId());
        sensorsMap.put(tempSensor.getId(), tempSensor);
        library.getSensorIds().add(tempSensor.getId());

        // Seed Sensor Readings for TEMP-001 using a thread-safe CopyOnWriteArrayList
        List<SensorReading> tempReadings = new CopyOnWriteArrayList<>();
        tempReadings.add(new SensorReading("R-1001", System.currentTimeMillis() - 3600000, 22.0)); // 1 hour ago
        tempReadings.add(new SensorReading("R-1002", System.currentTimeMillis() - 1800000, 22.3)); // 30 mins ago
        tempReadings.add(new SensorReading("R-1003", System.currentTimeMillis(), 22.5));           // Current
        sensorReadingsMap.put(tempSensor.getId(), tempReadings);

        // --- Seed Room 2: Engineering Lab ---
        Room lab = new Room("ENG-101", "Engineering Lab", 40);
        roomsMap.put(lab.getId(), lab);
        
        // Seed Sensor for Engineering Lab
        Sensor co2Sensor = new Sensor("CO2-001", "CO2", "ACTIVE",450.0, lab.getId());
        sensorsMap.put(co2Sensor.getId(), co2Sensor);
        lab.getSensorIds().add(co2Sensor.getId());
        
        // Empty thread-safe readings list for CO2 sensor
        sensorReadingsMap.put(co2Sensor.getId(), new CopyOnWriteArrayList<>());
    }
    
    public static Map<String, Room> getRoomsMap() { return roomsMap; }
    
    public static Map<String, Sensor> getSensorsMap() { return sensorsMap; }
    
    public static Map<String, List<SensorReading>> getSensorReadingsMap() { return sensorReadingsMap; }
}