package kg.alatoo.hotelbooking.integration;

import kg.alatoo.hotelbooking.dto.RoomDTO;
import kg.alatoo.hotelbooking.dto.ReservationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetRooms() {
        ResponseEntity<RoomDTO[]> response = restTemplate.getForEntity("/api/rooms/", RoomDTO[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        RoomDTO[] rooms = response.getBody();
        assertNotNull(rooms);
        assertTrue(rooms.length >= 2, "Expected at least 2 rooms from DataLoader");
    }

    @Test
    public void testGetReservations() {
        ResponseEntity<ReservationDTO[]> response = restTemplate.getForEntity("/api/reservations/", ReservationDTO[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ReservationDTO[] reservations = response.getBody();
        assertNotNull(reservations);
        assertTrue(reservations.length >= 2, "Expected at least 2 reservations from DataLoader");
    }
}