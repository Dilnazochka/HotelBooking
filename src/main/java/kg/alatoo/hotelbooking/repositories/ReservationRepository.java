package kg.alatoo.hotelbooking.repositories;

import kg.alatoo.hotelbooking.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}