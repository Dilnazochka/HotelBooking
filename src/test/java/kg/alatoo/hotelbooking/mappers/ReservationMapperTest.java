package kg.alatoo.hotelbooking.mappers;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationMapperTest {

    @Test
    public void testToDto() {
        Room room = new Room();
        room.setId(1L);
        room.setNumber("101");
        room.setType("Single");
        room.setStatus("Available");

        Reservation reservation = new Reservation();
        reservation.setId(10L);
        reservation.setRoom(room);
        reservation.setCustomerName("John Doe");
        reservation.setCheckInDate(LocalDate.of(2024, 5, 1));
        reservation.setCheckOutDate(LocalDate.of(2024, 5, 5));

        ReservationDTO dto = ReservationMapper.toDto(reservation);

        assertEquals(reservation.getId(), dto.getId());
        assertEquals(reservation.getCustomerName(), dto.getCustomerName());
        assertEquals(reservation.getCheckInDate(), dto.getCheckInDate());
        assertEquals(reservation.getCheckOutDate(), dto.getCheckOutDate());
        assertEquals(reservation.getRoom().getId(), dto.getRoomId());
    }

    @Test
    public void testToEntity() {
        Room room = new Room();
        room.setId(1L);
        room.setNumber("101");
        room.setType("Single");
        room.setStatus("Available");

        ReservationDTO dto = new ReservationDTO();
        dto.setId(10L);
        dto.setRoomId(1L);
        dto.setCustomerName("John Doe");
        dto.setCheckInDate(LocalDate.of(2024, 5, 1));
        dto.setCheckOutDate(LocalDate.of(2024, 5, 5));

        Reservation reservation = ReservationMapper.toEntity(dto, room);

        assertEquals(dto.getId(), reservation.getId());
        assertEquals(dto.getCustomerName(), reservation.getCustomerName());
        assertEquals(dto.getCheckInDate(), reservation.getCheckInDate());
        assertEquals(dto.getCheckOutDate(), reservation.getCheckOutDate());
        assertEquals(room, reservation.getRoom());
    }
}