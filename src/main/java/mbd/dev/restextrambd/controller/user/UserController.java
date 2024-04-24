package mbd.dev.restextrambd.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mbd.dev.restextrambd.controller.product.ProductController;
import mbd.dev.restextrambd.controller.user.dto.UserDto;
import mbd.dev.restextrambd.model.user.User;
import mbd.dev.restextrambd.service.user.UserService;
import mbd.dev.restextrambd.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    Logger logger = Logger.getLogger(ProductController.class.getName());

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> create (@Valid @RequestBody UserDto userDto) {
        try {
            return userService.create(userDto);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 400, true, "Internal server error"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
