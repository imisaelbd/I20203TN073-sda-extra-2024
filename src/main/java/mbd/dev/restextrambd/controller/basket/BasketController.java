package mbd.dev.restextrambd.controller.basket;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mbd.dev.restextrambd.controller.basket.dto.BasketDto;
import mbd.dev.restextrambd.controller.basket.dto.BasketProductDto;
import mbd.dev.restextrambd.model.basket.Basket;
import mbd.dev.restextrambd.service.basket.BasketService;
import mbd.dev.restextrambd.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/basket")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor

public class BasketController {

    private final BasketService basketService;

    Logger logger = Logger.getLogger(BasketController.class.getName());

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Basket>> create (@Valid @RequestBody BasketDto basketDto) {
        try {
            return basketService.create(basketDto);
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

    @PostMapping("/saveProducts/{id}")
    public ResponseEntity<ApiResponse<Basket>> saveProduct (@Valid @RequestBody BasketProductDto basketProductDto, @PathVariable Long id) {
        try {
            return basketService.saveProducts(id, basketProductDto);
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


    @GetMapping("/getTotal/{id}")
    public ResponseEntity<ApiResponse<Basket>> getTotal (@PathVariable Long id) {
        try {
            return basketService.getTotal(id);
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
