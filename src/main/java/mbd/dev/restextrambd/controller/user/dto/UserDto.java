package mbd.dev.restextrambd.controller.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class UserDto {

    @NotBlank(message = "El nombre de usuario es requerido")
    private String name;
}
