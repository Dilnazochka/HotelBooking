package kg.alatoo.hotelbooking.controllers;
import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    private RoomDTO roomDTO;

    @BeforeEach
    void setUp() {
        roomDTO = new RoomDTO();
        roomDTO.setId(1L);
        roomDTO.setNumber("101");
        roomDTO.setType("Single");
        roomDTO.setStatus("Available");
    }

    @Test
    void getAllRooms_shouldReturnRoomList() {
        when(roomService.findAll()).thenReturn(Collections.singletonList(roomDTO));

        List<RoomDTO> result = roomController.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("101", result.get(0).getNumber());
    }

    @Test
    void createRoom_shouldReturnSavedRoom() {
        when(roomService.save(roomDTO)).thenReturn(roomDTO);

        var response = roomController.create(roomDTO);

        assertNotNull(response.getBody());
        assertEquals("101", response.getBody().getNumber());
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
