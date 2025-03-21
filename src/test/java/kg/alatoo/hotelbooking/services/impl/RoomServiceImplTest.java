package kg.alatoo.hotelbooking.services.impl;

import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.mappers.RoomMapper;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room room;
    private RoomDTO roomDTO;

    @BeforeEach
    void setUp() {
        room = Room.builder()
                .id(1L)
                .number("101")
                .type("Single")
                .status("Available")
                .build();

        roomDTO = RoomMapper.toDTO(room);
    }

    @Test
    void findAll_shouldReturnListOfRooms() {
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room));

        List<RoomDTO> result = roomService.findAll();

        assertEquals(1, result.size());
        assertEquals("101", result.get(0).getNumber());
    }

    @Test
    void findById_shouldReturnRoom() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        RoomDTO result = roomService.findById(1L);

        assertNotNull(result);
        assertEquals("101", result.getNumber());
    }

    @Test
    void save_shouldReturnSavedRoom() {
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        RoomDTO result = roomService.save(roomDTO);

        assertNotNull(result);
        assertEquals("101", result.getNumber());
    }

    @Test
    void update_shouldReturnUpdatedRoom() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        roomDTO.setStatus("Occupied");
        RoomDTO result = roomService.update(1L, roomDTO);

        assertNotNull(result);
        assertEquals("Occupied", result.getStatus());
    }

    @Test
    void delete_shouldInvokeRepositoryDelete() {
        roomService.delete(1L);
        verify(roomRepository, times(1)).deleteById(1L);
    }
}
