package kg.alatoo.hotelbooking.bootstrap;

import kg.alatoo.hotelbooking.entities.Reservation;
import kg.alatoo.hotelbooking.entities.Room;
import kg.alatoo.hotelbooking.repositories.ReservationRepository;
import kg.alatoo.hotelbooking.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void run(String... args) {
        Room room1 = Room.builder()
                .number("101")
                .type("Single")
                .status("Available")
                .build();

        Room room2 = Room.builder()
                .number("202")
                .type("Double")
                .status("Available")
                .build();

        roomRepository.save(room1);
        roomRepository.save(room2);

        Reservation res1 = Reservation.builder()
                .customerName("John Doe")
                .checkInDate(LocalDate.of(2025, 4, 1))
                .checkOutDate(LocalDate.of(2025, 4, 5))
                .room(room1)
                .build();

        Reservation res2 = Reservation.builder()
                .customerName("Alice Smith")
                .checkInDate(LocalDate.of(2025, 4, 10))
                .checkOutDate(LocalDate.of(2025, 4, 12))
                .room(room2)
                .build();

        reservationRepository.save(res1);
        reservationRepository.save(res2);
    }
}
