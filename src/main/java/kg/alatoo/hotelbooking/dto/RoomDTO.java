package kg.alatoo.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoomDTO {
    private Long id;

    @NotBlank
    private String number;

    @NotBlank
    private String type;

    @NotBlank
    private String status;
}

