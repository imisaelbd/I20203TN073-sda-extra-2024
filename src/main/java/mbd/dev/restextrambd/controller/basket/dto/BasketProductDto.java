package mbd.dev.restextrambd.controller.basket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class BasketProductDto {

    @NotBlank(message = "El codigo del producto es requerido")
    @Size(min = 4, message = "El codigo del producto es de al menos 4 digitos")
    private String code;

    @NotNull(message = "La cantidad es requerida")
    private Long quantity;
}
