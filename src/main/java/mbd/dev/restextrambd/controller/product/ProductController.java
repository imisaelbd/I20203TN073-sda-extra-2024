package mbd.dev.restextrambd.controller.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mbd.dev.restextrambd.controller.product.dto.ProductDto;
import mbd.dev.restextrambd.model.products.Product;
import mbd.dev.restextrambd.service.product.ProductService;
import mbd.dev.restextrambd.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    Logger logger = Logger.getLogger(ProductController.class.getName());

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Product>> create (@Valid @RequestBody ProductDto productDto) {
        try {
            return productService.create(productDto);
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
