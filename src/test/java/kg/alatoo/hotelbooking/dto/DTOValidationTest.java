package kg.alatoo.hotelbooking.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DTOValidationTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testRoomDTOValidation() {
        RoomDTO roomDTO = new RoomDTO();
        // Поля не заполнены, должны возникнуть ошибки валидации
        Set<ConstraintViolation<RoomDTO>> violations = validator.validate(roomDTO);
        assertFalse(violations.isEmpty());

        roomDTO.setNumber("101");
        roomDTO.setType("Single");
        roomDTO.setStatus("Available");
        violations = validator.validate(roomDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testReservationDTOValidation() {
        ReservationDTO reservationDTO = new ReservationDTO();
        // Отсутствуют обязательные поля
        Set<ConstraintViolation<ReservationDTO>> violations = validator.validate(reservationDTO);
        assertFalse(violations.isEmpty());

        reservationDTO.setRoomId(1L);
        reservationDTO.setCustomerName("Test Customer");
        reservationDTO.setCheckInDate(LocalDate.of(2024, 7, 1));
        reservationDTO.setCheckOutDate(LocalDate.of(2024, 7, 5));
        violations = validator.validate(reservationDTO);
        assertTrue(violations.isEmpty());
    }
}