package kg.alatoo.hotelbooking.repositories;

import kg.alatoo.hotelbooking.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
