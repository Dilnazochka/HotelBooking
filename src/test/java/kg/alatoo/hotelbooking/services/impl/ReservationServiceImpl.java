package kg.alatoo.hotelbooking.services.impl;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.mappers.ReservationMapper;
import kg.alatoo.hotelbooking.repositories.ReservationRepository;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Room room;
    private Reservation reservation;
    private ReservationDTO reservationDTO;

    @BeforeEach
    void setUp() {
        room = Room.builder()
                .id(1L)
                .number("101")
                .type("Single")
                .status("Available")
                .build();

        reservation = Reservation.builder()
                .id(1L)
                .customerName("Alice")
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(2))
                .room(room)
                .build();

        reservationDTO = ReservationMapper.toDTO(reservation);
    }

    @Test
    void findAll_shouldReturnList() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));

        List<ReservationDTO> result = reservationService.findAll();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getCustomerName());
    }

    @Test
    void findById_shouldReturnReservation() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        ReservationDTO result = reservationService.findById(1L);

        assertNotNull(result);
        assertEquals("Alice", result.getCustomerName());
    }

    @Test
    void save_shouldReturnSavedReservation() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationDTO result = reservationService.save(reservationDTO);

        assertNotNull(result);
        assertEquals("Alice", result.getCustomerName());
    }

    @Test
    void update_shouldReturnUpdatedReservation() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        reservationDTO.setCustomerName("Bob");
        ReservationDTO result = reservationService.update(1L, reservationDTO);

        assertNotNull(result);
        assertEquals("Bob", result.getCustomerName());
    }

    @Test
    void delete_shouldInvokeRepositoryDelete() {
        reservationService.delete(1L);
        verify(reservationRepository, times(1)).deleteById(1L);
    }
}
