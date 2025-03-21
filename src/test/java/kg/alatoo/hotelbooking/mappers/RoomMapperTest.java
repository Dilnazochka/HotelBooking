package kg.alatoo.hotelbooking.mappers;

import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.entities.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomMapperTest {

    @Test
    void toDTO_shouldMapEntityToDTO() {
        Room room = Room.builder()
                .id(1L)
                .number("101")
                .type("Single")
                .status("Available")
                .build();

        RoomDTO dto = RoomMapper.toDTO(room);

        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getNumber(), dto.getNumber());
        assertEquals(room.getType(), dto.getType());
        assertEquals(room.getStatus(), dto.getStatus());
    }

    @Test
    void toEntity_shouldMapDTOToEntity() {
        RoomDTO dto = new RoomDTO();
        dto.setId(1L);
        dto.setNumber("101");
        dto.setType("Single");
        dto.setStatus("Available");

        Room room = RoomMapper.toEntity(dto);

        assertEquals(dto.getId(), room.getId());
        assertEquals(dto.getNumber(), room.getNumber());
        assertEquals(dto.getType(), room.getType());
        assertEquals(dto.getStatus(), room.getStatus());
    }
}
