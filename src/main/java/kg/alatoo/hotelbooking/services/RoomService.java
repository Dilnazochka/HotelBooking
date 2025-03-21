package kg.alatoo.hotelbooking.services;

import kg.alatoo.hotelbooking.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> findAll();
    RoomDTO findById(Long id);
    RoomDTO save(RoomDTO roomDTO);
    RoomDTO update(Long id, RoomDTO roomDTO);
    void delete(Long id);
}

