package kg.alatoo.hotelbooking.services;

import kg.alatoo.hotelbooking.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> findAll();
    ReservationDTO findById(Long id);
    ReservationDTO save(ReservationDTO reservationDTO);
    ReservationDTO update(Long id, ReservationDTO reservationDTO);
    void delete(Long id);
}