package kg.alatoo.hotelbooking.mappers;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTest {

    @Test
    void toDTO_shouldMapEntityToDTO() {
        Room room = Room.builder().id(1L).build();
        Reservation reservation = Reservation.builder()
                .id(2L)
                .customerName("John")
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(1))
                .room(room)
                .build();

        ReservationDTO dto = ReservationMapper.toDTO(reservation);

        assertEquals(reservation.getId(), dto.getId());
        assertEquals(reservation.getCustomerName(), dto.getCustomerName());
        assertEquals(reservation.getCheckInDate(), dto.getCheckInDate());
        assertEquals(reservation.getCheckOutDate(), dto.getCheckOutDate());
        assertEquals(reservation.getRoom().getId(), dto.getRoomId());
    }

    @Test
    void toEntity_shouldMapDTOToEntity() {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(2L);
        dto.setCustomerName("John");
        dto.setCheckInDate(LocalDate.now());
        dto.setCheckOutDate(LocalDate.now().plusDays(1));
        dto.setRoomId(1L);

        Room room = Room.builder().id(1L).build();
        Reservation reservation = ReservationMapper.toEntity(dto, room);

        assertEquals(dto.getId(), reservation.getId());
        assertEquals(dto.getCustomerName(), reservation.getCustomerName());
        assertEquals(dto.getCheckInDate(), reservation.getCheckInDate());
        assertEquals(dto.getCheckOutDate(), reservation.getCheckOutDate());
        assertEquals(dto.getRoomId(), reservation.getRoom().getId());
    }
}
