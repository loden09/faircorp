package com.emse.spring.faircorp.model;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "HEATER")
public class Heater {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private HeaterStatus heaterStatus;

    @Column
    private Long power;

    @ManyToOne
    private Room room;

    public Heater() {
    }

    public Heater(String name, HeaterStatus status, Room room) {
        this.heaterStatus = status;
        this.name = name;
        this.room=room;
        Set<Heater> heaters = room.getHeaters();
        heaters.add(this);
        room.setHeaters(heaters);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus windowStatus) {
        this.heaterStatus = windowStatus;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
