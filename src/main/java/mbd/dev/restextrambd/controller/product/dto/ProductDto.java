package mbd.dev.restextrambd.controller.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class ProductDto {

    @NotBlank(message = "El nombre del producto es requerido")
    @Size(max = 30, message = "El numero maximo de digitos son 30 para el nombre del producto")
    private String name;

    @NotNull(message = "El precio del producto es requerido")
    private Long price;

    @NotBlank(message = "El codigo del producto es requerido")
    @Size(min = 4, message = "El numero minimo para el codigo del producto son 4")
    private String code;

    @NotBlank(message = "La descripcion del producto es requerida")
    @Size(max = 300, message = "El numero maximo de caracteres para la descripcion del producto son 300")
    private String description;
}
