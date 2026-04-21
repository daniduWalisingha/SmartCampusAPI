package com.smartcampus.repository;

import com.smartcampus.model.Room;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DataStore {

    // Keep these names because your other classes are using them
    public static final Map<String, Room> rooms = new ConcurrentHashMap<>();
    public static final Map<String, Sensor> sensors = new ConcurrentHashMap<>();
    public static final Map<String, List<SensorReading>> sensorReadings = new ConcurrentHashMap<>();

    private DataStore() {
    }

    static {
        // --- Seed Room 1: Library ---
        Room library = new Room("LIB-301", "Library Quiet Study", 120);
        rooms.put(library.getId(), library);

        // Seed Sensor for Library
        Sensor tempSensor = new Sensor("TEMP-001", "Temperature", "ACTIVE", 22.5, library.getId());
        sensors.put(tempSensor.getId(), tempSensor);
        library.getSensorIds().add(tempSensor.getId());

        // Seed Sensor Readings for TEMP-001
        List<SensorReading> tempReadings = new CopyOnWriteArrayList<>();
        tempReadings.add(new SensorReading("R-1001", System.currentTimeMillis() - 3600000, 22.0));
        tempReadings.add(new SensorReading("R-1002", System.currentTimeMillis() - 1800000, 22.3));
        tempReadings.add(new SensorReading("R-1003", System.currentTimeMillis(), 22.5));
        sensorReadings.put(tempSensor.getId(), tempReadings);

        // --- Seed Room 2: Engineering Lab ---
        Room lab = new Room("ENG-101", "Engineering Lab", 40);
        rooms.put(lab.getId(), lab);

        // Seed Sensor for Engineering Lab
        Sensor co2Sensor = new Sensor("CO2-001", "CO2", "ACTIVE", 450.0, lab.getId());
        sensors.put(co2Sensor.getId(), co2Sensor);
        lab.getSensorIds().add(co2Sensor.getId());

        // Empty readings list for CO2 sensor
        sensorReadings.put(co2Sensor.getId(), new CopyOnWriteArrayList<>());
    }

    public static List<SensorReading> getReadingsForSensor(String sensorId) {
        return sensorReadings.computeIfAbsent(sensorId, k -> new CopyOnWriteArrayList<>());
    }

    public static Map<String, Room> getRoomsMap() {
        return rooms;
    }

    public static Map<String, Sensor> getSensorsMap() {
        return sensors;
    }

    public static Map<String, List<SensorReading>> getSensorReadingsMap() {
        return sensorReadings;
    }
}