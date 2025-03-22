package kg.alatoo.hotelbooking.mappers;

import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.entities.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomMapperTest {

    @Test
    public void testToDto() {
        Room room = new Room();
        room.setId(1L);
        room.setNumber("101");
        room.setType("Single");
        room.setStatus("Available");

        RoomDTO dto = RoomMapper.toDto(room);

        assertEquals(room.getId(), dto.getId());
        assertEquals(room.getNumber(), dto.getNumber());
        assertEquals(room.getType(), dto.getType());
        assertEquals(room.getStatus(), dto.getStatus());
    }

    @Test
    public void testToEntity() {
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