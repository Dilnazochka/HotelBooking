package kg.alatoo.hotelbooking.services.impl;

import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.mappers.RoomMapper;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import kg.alatoo.hotelbooking.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(RoomMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        return RoomMapper.toDto(room);
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = RoomMapper.toEntity(roomDTO);
        room = roomRepository.save(room);
        return RoomMapper.toDto(room);
    }

    @Override
    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setNumber(roomDTO.getNumber());
        room.setType(roomDTO.getType());
        room.setStatus(roomDTO.getStatus());
        room = roomRepository.save(room);
        return RoomMapper.toDto(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}