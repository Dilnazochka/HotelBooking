package kg.alatoo.hotelbooking.repositories;

import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testSaveAndFindReservation() {
        Room room = new Room();
        room.setNumber("102");
        room.setType("Double");
        room.setStatus("Available");
        room = roomRepository.save(room);

        Reservation reservation = new Reservation();
        reservation.setCustomerName("Test Customer");
        reservation.setCheckInDate(LocalDate.of(2024, 8, 1));
        reservation.setCheckOutDate(LocalDate.of(2024, 8, 5));
        reservation.setRoom(room);

        Reservation savedReservation = reservationRepository.save(reservation);
        Optional<Reservation> foundReservation = reservationRepository.findById(savedReservation.getId());
        assertTrue(foundReservation.isPresent());
        assertEquals("Test Customer", foundReservation.get().getCustomerName());
        assertEquals(room.getId(), foundReservation.get().getRoom().getId());
    }
}