package kg.alatoo.hotelbooking.bootstrap;

import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.repositories.ReservationRepository;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void run(String... args) {
        Room room1 = new Room();
        room1.setNumber("101");
        room1.setType("Single");
        room1.setStatus("Available");

        Room room2 = new Room();
        room2.setNumber("102");
        room2.setType("Double");
        room2.setStatus("Occupied");

        roomRepository.save(room1);
        roomRepository.save(room2);

        Reservation reservation1 = new Reservation();
        reservation1.setRoom(room1);
        reservation1.setCustomerName("John Doe");
        reservation1.setCheckInDate(LocalDate.of(2024, 5, 1));
        reservation1.setCheckOutDate(LocalDate.of(2024, 5, 5));

        Reservation reservation2 = new Reservation();
        reservation2.setRoom(room2);
        reservation2.setCustomerName("Alice Smith");
        reservation2.setCheckInDate(LocalDate.of(2024, 6, 10));
        reservation2.setCheckOutDate(LocalDate.of(2024, 6, 15));

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
    }
}