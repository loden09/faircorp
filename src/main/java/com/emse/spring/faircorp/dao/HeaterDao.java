package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeaterDao extends JpaRepository<Heater,Long> {
    @Query("select h from Heater h where h.room.name = :name")
    public List<Heater> findHeaterbyRoomName(String name);

}
