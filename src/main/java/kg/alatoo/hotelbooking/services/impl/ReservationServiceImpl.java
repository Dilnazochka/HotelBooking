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
    public List<ReservationDTO> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(Long id) {
        return reservationRepository.findById(id)
                .map(ReservationMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ReservationDTO save(ReservationDTO dto) {
        Room room = roomRepository.findById(dto.getRoomId()).orElse(null);
        if (room == null) return null;
        Reservation reservation = ReservationMapper.toEntity(dto, room);
        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDTO update(Long id, ReservationDTO dto) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        Room room = roomRepository.findById(dto.getRoomId()).orElse(null);
        if (reservation == null || room == null) return null;
        reservation.setCustomerName(dto.getCustomerName());
        reservation.setCheckInDate(dto.getCheckInDate());
        reservation.setCheckOutDate(dto.getCheckOutDate());
        reservation.setRoom(room);
        return ReservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}
