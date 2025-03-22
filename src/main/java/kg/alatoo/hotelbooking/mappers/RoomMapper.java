package kg.alatoo.hotelbooking.mappers;

import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.entities.Room;

public class RoomMapper {

    public static RoomDTO toDto(Room room) {
        if (room == null) {
            return null;
        }
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setNumber(room.getNumber());
        dto.setType(room.getType());
        dto.setStatus(room.getStatus());
        return dto;
    }

    public static Room toEntity(RoomDTO dto) {
        if (dto == null) {
            return null;
        }
        Room room = new Room();
        room.setId(dto.getId());
        room.setNumber(dto.getNumber());
        room.setType(dto.getType());
        room.setStatus(dto.getStatus());
        return room;
    }
}