package kg.alatoo.hotelbooking.services.impl;

import kg.alatoo.hotelbooking.dto.ReservationDTO;
import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.mappers.ReservationMapper;
import kg.alatoo.hotelbooking.repositories.ReservationRepository;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import kg.alatoo.hotelbooking.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(ReservationMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return ReservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        Reservation reservation = ReservationMapper.toEntity(reservationDTO, room);
        reservation = reservationRepository.save(reservation);
        return ReservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        existingReservation.setCustomerName(reservationDTO.getCustomerName());
        existingReservation.setCheckInDate(reservationDTO.getCheckInDate());
        existingReservation.setCheckOutDate(reservationDTO.getCheckOutDate());

        if (reservationDTO.getRoomId() != null &&
                !reservationDTO.getRoomId().equals(existingReservation.getRoom().getId())) {
            Room newRoom = roomRepository.findById(reservationDTO.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
            existingReservation.setRoom(newRoom);
        }

        existingReservation = reservationRepository.save(existingReservation);
        return ReservationMapper.toDto(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}