package kg.alatoo.hotelbooking.mappers;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;

public class ReservationMapper {
    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setCustomerName(reservation.getCustomerName());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setRoomId(reservation.getRoom().getId());
        return dto;
    }

    public static Reservation toEntity(ReservationDTO dto, Room room) {
        return Reservation.builder()
                .id(dto.getId())
                .customerName(dto.getCustomerName())
                .checkInDate(dto.getCheckInDate())
                .checkOutDate(dto.getCheckOutDate())
                .room(room)
                .build();
    }
}
