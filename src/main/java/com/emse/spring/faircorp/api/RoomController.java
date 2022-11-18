package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {

    private final RoomDao roomDao;

    private final WindowDao windowDao;

    private final HeaterDao heaterDao;

    public RoomController (RoomDao roomDao, WindowDao windowDao, HeaterDao heaterDao) {
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @PostMapping // (8)
    public RoomDto create(@RequestBody RoomDto dto) {
        // WindowDto must always contain the window room
        Room room = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getName(),dto.getFloor()));
        }
        else {
            room = roomDao.getReferenceById(dto.getId());
            room.setName(dto.getName());
            room.setFloor(dto.getFloor());
        }
        room.setCurrentTemperature(dto.getCurrentTemperature());
        room.setTargetTemperature(dto.getTargetTemperature());
        return new RoomDto(room);
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
        windowDao.deleteAll();
        heaterDao.deleteAll();
    }

    @PutMapping(path = "/{room_id}/switchWindows")
    public ArrayList<WindowDto> switchWindows(@PathVariable Long room_id) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        List<Window> windows = windowDao.findWindowbyRoomName(room.getName());
        ArrayList<WindowDto> retur = new ArrayList<WindowDto>() ;
        for (Window window : windows){
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
            retur.add(new WindowDto(window));
        }
        return retur;
    }

    @PutMapping(path = "/{room_id}/switchHeaters")
    public ArrayList<HeaterDto> switchHeaters(@PathVariable Long room_id) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        List<Heater> heaters = heaterDao.findHeaterbyRoomName(room.getName());
        ArrayList<HeaterDto> retur = new ArrayList<HeaterDto>() ;
        for (Heater heater : heaters){
            heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
            retur.add(new HeaterDto(heater));
        }
        return retur;
    }
}
