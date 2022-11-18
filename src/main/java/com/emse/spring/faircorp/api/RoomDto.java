package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class RoomDto {

    private Long id;
    private int floor;
    private String name;
    private Double currentTemperature;
    private Double targetTemperature;
    private Set<HeaterDto> heaters = new HashSet<>();
    private Set<WindowDto> windows = new HashSet<>();

    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.name = room.getName();
        this.floor = room.getFloor();
        this.id=room.getId();
        this.currentTemperature=room.getCurrentTemperature();
        this.targetTemperature=room.getTargetTemperature();
        this.heaters=heaterToDto(room.getHeaters());
        this.windows=windowToDto(room.getWindows());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double currentTemperature) {
        this.targetTemperature = currentTemperature;
    }


    public Set<HeaterDto> getHeaters() {
        return heaters;
    }

    public void setHeaters(Set<HeaterDto> heaters) {
        this.heaters = heaters;
    }

    public Set<WindowDto> getWindows() {
        return windows;
    }

    public void setWindows(Set<WindowDto> windows) {
        this.windows = windows;
    }

    public Set<HeaterDto> heaterToDto(Set<Heater> heaters) {
        Set<HeaterDto> result = new HashSet<>();
        if (heaters != null) {
            for (Heater heater : heaters) {
                result.add(new HeaterDto(heater));
            }
        }
        return result;
    }

    public Set<WindowDto> windowToDto(Set<Window> windows) {
        Set<WindowDto> result = new HashSet<>();
        if (windows != null) {
            for (Window window : windows) {
                result.add(new WindowDto(window));
            }
        }
        return result;
    }
}
