package kg.alatoo.hotelbooking.services.impl;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.repositories.ReservationRepository;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import kg.alatoo.hotelbooking.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReservations() {
        Room room = new Room(1L, "101", "Single", "Available");
        Reservation res1 = new Reservation(10L, "John Doe", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5), room);
        Reservation res2 = new Reservation(11L, "Alice Smith", LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 15), room);

        when(reservationRepository.findAll()).thenReturn(Arrays.asList(res1, res2));

        List<ReservationDTO> reservations = reservationService.getAllReservations();
        assertEquals(2, reservations.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    public void testGetReservationById() {
        Room room = new Room(1L, "101", "Single", "Available");
        Reservation reservation = new Reservation(10L, "John Doe", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5), room);
        when(reservationRepository.findById(10L)).thenReturn(Optional.of(reservation));

        ReservationDTO dto = reservationService.getReservationById(10L);
        assertNotNull(dto);
        assertEquals("John Doe", dto.getCustomerName());
        verify(reservationRepository, times(1)).findById(10L);
    }

    @Test
    public void testCreateReservation() {
        Room room = new Room(1L, "101", "Single", "Available");
        ReservationDTO reservationDTO = new ReservationDTO(null, 1L, "John Doe", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5));
        Reservation reservation = new Reservation(null, "John Doe", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5), room);
        Reservation savedReservation = new Reservation(10L, "John Doe", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5), room);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        ReservationDTO result = reservationService.createReservation(reservationDTO);
        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(roomRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    public void testUpdateReservation() {
        Room room = new Room(1L, "101", "Single", "Available");
        Reservation existingReservation = new Reservation(10L, "John Doe", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 5), room);
        ReservationDTO reservationDTO = new ReservationDTO(null, 1L, "Jane Doe", LocalDate.of(2024, 5, 2), LocalDate.of(2024, 5, 6));

        when(reservationRepository.findById(10L)).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.save(existingReservation)).thenReturn(existingReservation);

        ReservationDTO updated = reservationService.updateReservation(10L, reservationDTO);
        assertNotNull(updated);
        assertEquals("Jane Doe", updated.getCustomerName());
        verify(reservationRepository, times(1)).findById(10L);
        verify(reservationRepository, times(1)).save(existingReservation);
    }

    @Test
    public void testDeleteReservation() {
        doNothing().when(reservationRepository).deleteById(10L);
        reservationService.deleteReservation(10L);
        verify(reservationRepository, times(1)).deleteById(10L);
    }
}