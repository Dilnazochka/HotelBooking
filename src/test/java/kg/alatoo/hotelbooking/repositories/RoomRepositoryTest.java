package kg.alatoo.hotelbooking.repositories;

import kg.alatoo.hotelbooking.entities.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testSaveAndFindRoom() {
        Room room = new Room();
        room.setNumber("101");
        room.setType("Single");
        room.setStatus("Available");

        Room savedRoom = roomRepository.save(room);
        Optional<Room> foundRoom = roomRepository.findById(savedRoom.getId());
        assertTrue(foundRoom.isPresent());
        assertEquals("101", foundRoom.get().getNumber());
    }
}