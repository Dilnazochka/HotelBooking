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
    public List<RoomDTO> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO findById(Long id) {
        return roomRepository.findById(id)
                .map(RoomMapper::toDTO)
                .orElse(null);
    }

    @Override
    public RoomDTO save(RoomDTO roomDTO) {
        Room room = RoomMapper.toEntity(roomDTO);
        return RoomMapper.toDTO(roomRepository.save(room));
    }

    @Override
    public RoomDTO update(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room == null) return null;
        room.setNumber(roomDTO.getNumber());
        room.setType(roomDTO.getType());
        room.setStatus(roomDTO.getStatus());
        return RoomMapper.toDTO(roomRepository.save(room));
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
