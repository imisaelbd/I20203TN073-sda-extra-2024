package mbd.dev.restextrambd.service.product;

import lombok.RequiredArgsConstructor;
import mbd.dev.restextrambd.controller.product.dto.ProductDto;
import mbd.dev.restextrambd.model.products.Product;
import mbd.dev.restextrambd.model.products.ProductRepository;
import mbd.dev.restextrambd.utils.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse<Product>> create (ProductDto product) {
        boolean existByName = productRepository.existsByName(product.getName());
        boolean existByCode = productRepository.existsByCode(product.getCode());

        if (existByCode) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 400, true, "El codigo ingresado ya existe"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (existByName) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 400, true, "El nombre ingresado ya existe"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }

        Product newProduct = new Product();
        BeanUtils.copyProperties(product, newProduct);
        Product saveProduct = productRepository.save(newProduct);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        saveProduct, 200, true, "Producto registrado correctamente"
                ),
                HttpStatus.OK
        );
    }
}
