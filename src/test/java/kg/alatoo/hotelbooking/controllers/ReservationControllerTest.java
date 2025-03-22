package kg.alatoo.hotelbooking.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllReservations() throws Exception {
        ReservationDTO res1 = new ReservationDTO();
        res1.setId(1L);
        res1.setRoomId(1L);
        res1.setCustomerName("John Doe");
        res1.setCheckInDate(LocalDate.of(2024, 5, 1));
        res1.setCheckOutDate(LocalDate.of(2024, 5, 5));

        ReservationDTO res2 = new ReservationDTO();
        res2.setId(2L);
        res2.setRoomId(2L);
        res2.setCustomerName("Alice Smith");
        res2.setCheckInDate(LocalDate.of(2024, 6, 10));
        res2.setCheckOutDate(LocalDate.of(2024, 6, 15));

        Mockito.when(reservationService.getAllReservations()).thenReturn(Arrays.asList(res1, res2));

        mockMvc.perform(get("/api/reservations/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].customerName").value("Alice Smith"));
    }

    @Test
    public void testCreateReservation() throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setRoomId(1L);
        reservationDTO.setCustomerName("Bob Marley");
        reservationDTO.setCheckInDate(LocalDate.of(2024, 7, 1));
        reservationDTO.setCheckOutDate(LocalDate.of(2024, 7, 5));

        ReservationDTO savedReservation = new ReservationDTO();
        savedReservation.setId(3L);
        savedReservation.setRoomId(1L);
        savedReservation.setCustomerName("Bob Marley");
        savedReservation.setCheckInDate(LocalDate.of(2024, 7, 1));
        savedReservation.setCheckOutDate(LocalDate.of(2024, 7, 5));

        Mockito.when(reservationService.createReservation(any(ReservationDTO.class))).thenReturn(savedReservation);

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.customerName").value("Bob Marley"));
    }
}