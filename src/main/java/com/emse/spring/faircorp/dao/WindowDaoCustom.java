package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface WindowDaoCustom {
    List<Window> findWindowbyRoomName(String name);

    List<Window> findRoomOpenWindows(Long id);


}
