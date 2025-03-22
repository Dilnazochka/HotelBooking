package kg.alatoo.hotelbooking.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.services.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllRooms() throws Exception {
        RoomDTO room1 = new RoomDTO();
        room1.setId(1L);
        room1.setNumber("101");
        room1.setType("Single");
        room1.setStatus("Available");

        RoomDTO room2 = new RoomDTO();
        room2.setId(2L);
        room2.setNumber("102");
        room2.setType("Double");
        room2.setStatus("Occupied");

        Mockito.when(roomService.getAllRooms()).thenReturn(Arrays.asList(room1, room2));

        mockMvc.perform(get("/api/rooms/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].number").value("101"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].number").value("102"));
    }

    @Test
    public void testCreateRoom() throws Exception {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setNumber("103");
        roomDTO.setType("Suite");
        roomDTO.setStatus("Available");

        RoomDTO savedRoom = new RoomDTO();
        savedRoom.setId(3L);
        savedRoom.setNumber("103");
        savedRoom.setType("Suite");
        savedRoom.setStatus("Available");

        Mockito.when(roomService.createRoom(any(RoomDTO.class))).thenReturn(savedRoom);

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.number").value("103"));
    }
}