package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomDaoCustomImpl implements RoomDaoCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findRoomByName(String name) {
        return em.createQuery("select * from Room where Room.name like :name",Room.class)
                .setParameter(":name", name)
                .getResultList();
    }
}
