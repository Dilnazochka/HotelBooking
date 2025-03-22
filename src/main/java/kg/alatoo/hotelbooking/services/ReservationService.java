package kg.alatoo.hotelbooking.services;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();
    ReservationDTO getReservationById(Long id);
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);
    void deleteReservation(Long id);
}