package kg.alatoo.hotelbooking.controllers;
import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private ReservationDTO reservationDTO;

    @BeforeEach
    void setUp() {
        reservationDTO = new ReservationDTO();
        reservationDTO.setId(1L);
        reservationDTO.setCustomerName("Test Customer");
        reservationDTO.setCheckInDate(LocalDate.now());
        reservationDTO.setCheckOutDate(LocalDate.now().plusDays(2));
        reservationDTO.setRoomId(1L);
    }

    @Test
    void getAllReservations_shouldReturnList() {
        when(reservationService.findAll()).thenReturn(Collections.singletonList(reservationDTO));

        List<ReservationDTO> result = reservationController.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Customer", result.get(0).getCustomerName());
    }

    @Test
    void createReservation_shouldReturnSavedReservation() {
        when(reservationService.save(reservationDTO)).thenReturn(reservationDTO);

        ResponseEntity<ReservationDTO> response = reservationController.create(reservationDTO);

        assertNotNull(response.getBody());
        assertEquals("Test Customer", response.getBody().getCustomerName());
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
