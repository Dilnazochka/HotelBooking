package kg.alatoo.hotelbooking.mappers;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;

public class ReservationMapper {

    public static ReservationDTO toDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setCustomerName(reservation.getCustomerName());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setRoomId(reservation.getRoom() != null ? reservation.getRoom().getId() : null);
        return dto;
    }

    public static Reservation toEntity(ReservationDTO dto, Room room) {
        if (dto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setCustomerName(dto.getCustomerName());
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setRoom(room);
        return reservation;
    }
}