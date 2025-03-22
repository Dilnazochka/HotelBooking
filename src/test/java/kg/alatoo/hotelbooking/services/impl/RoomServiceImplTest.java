package kg.alatoo.hotelbooking.services.impl;

import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import kg.alatoo.hotelbooking.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRooms() {
        Room room1 = new Room(1L, "101", "Single", "Available");
        Room room2 = new Room(2L, "102", "Double", "Occupied");

        when(roomRepository.findAll()).thenReturn(Arrays.asList(room1, room2));

        List<RoomDTO> rooms = roomService.getAllRooms();
        assertEquals(2, rooms.size());
        assertEquals("101", rooms.get(0).getNumber());
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void testGetRoomById() {
        Room room = new Room(1L, "101", "Single", "Available");
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        RoomDTO roomDTO = roomService.getRoomById(1L);
        assertNotNull(roomDTO);
        assertEquals("101", roomDTO.getNumber());
        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateRoom() {
        RoomDTO roomDTO = new RoomDTO(null, "103", "Suite", "Available");
        Room room = new Room(null, "103", "Suite", "Available");
        Room savedRoom = new Room(3L, "103", "Suite", "Available");

        when(roomRepository.save(any(Room.class))).thenReturn(savedRoom);

        RoomDTO result = roomService.createRoom(roomDTO);
        assertNotNull(result);
        assertEquals(3L, result.getId());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void testUpdateRoom() {
        Room existingRoom = new Room(1L, "101", "Single", "Available");
        RoomDTO roomDTO = new RoomDTO(null, "101", "Deluxe", "Available");

        when(roomRepository.findById(1L)).thenReturn(Optional.of(existingRoom));
        when(roomRepository.save(existingRoom)).thenReturn(existingRoom);

        RoomDTO updatedRoom = roomService.updateRoom(1L, roomDTO);
        assertNotNull(updatedRoom);
        assertEquals("Deluxe", updatedRoom.getType());
        verify(roomRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).save(existingRoom);
    }

    @Test
    public void testDeleteRoom() {
        doNothing().when(roomRepository).deleteById(1L);
        roomService.deleteRoom(1L);
        verify(roomRepository, times(1)).deleteById(1L);
    }
}