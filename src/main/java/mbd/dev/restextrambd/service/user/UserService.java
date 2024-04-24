package mbd.dev.restextrambd.service.user;

import lombok.RequiredArgsConstructor;
import mbd.dev.restextrambd.controller.user.dto.UserDto;
import mbd.dev.restextrambd.model.user.User;
import mbd.dev.restextrambd.model.user.UserRepository;
import mbd.dev.restextrambd.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse<User>> create (UserDto user) {
        boolean existByName = userRepository.existsByName(user.getName());
        if (existByName) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 400, true, "El nombre ingresado ya existe"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }

        User newUser = new User();
        newUser.setName(user.getName());
        User saveUser = userRepository.save(newUser);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        saveUser, 20, true, "Usuario registrado correctamente"
                ),
                HttpStatus.OK
        );
    }
}
