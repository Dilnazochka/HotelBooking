package kg.alatoo.hotelbooking.mappers;


import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.entities.Room;

public class RoomMapper {
    public static RoomDTO toDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setNumber(room.getNumber());
        dto.setType(room.getType());
        dto.setStatus(room.getStatus());
        return dto;
    }

    public static Room toEntity(RoomDTO dto) {
        return Room.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .type(dto.getType())
                .status(dto.getStatus())
                .build();
    }
}