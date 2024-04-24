package mbd.dev.restextrambd.controller.basket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class BasketDto {

    @NotNull(message = "El el id del usuario es requerido")
    private Long userId;
}
