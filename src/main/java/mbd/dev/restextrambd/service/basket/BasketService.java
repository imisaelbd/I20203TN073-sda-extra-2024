package mbd.dev.restextrambd.service.basket;


import lombok.RequiredArgsConstructor;
import mbd.dev.restextrambd.controller.basket.dto.BasketDto;
import mbd.dev.restextrambd.controller.basket.dto.BasketProductDto;
import mbd.dev.restextrambd.model.basket.Basket;
import mbd.dev.restextrambd.model.basket.BasketRepository;
import mbd.dev.restextrambd.model.basketproduct.BasketProduct;
import mbd.dev.restextrambd.model.basketproduct.BasketProductRepository;
import mbd.dev.restextrambd.model.products.Product;
import mbd.dev.restextrambd.model.products.ProductRepository;
import mbd.dev.restextrambd.model.user.User;
import mbd.dev.restextrambd.model.user.UserRepository;
import mbd.dev.restextrambd.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor

public class BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BasketProductRepository basketProductRepository;

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse<Basket>> create (BasketDto basket) {
        boolean existsByUser = basketRepository.existsByUserId((basket.getUserId()));
        if (existsByUser) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 400, true, "Ya existe una canasta para el usuario ingresado"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
        User user = userRepository.findById(basket.getUserId()).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 400, true, "El usuario ingresado no existe"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
        Basket newBasket = new Basket();
        newBasket.setUser(user);
        Basket saveBasket = basketRepository.save(newBasket);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        saveBasket, 20, true, "Canasta guardada exitosamente"
                ),
                HttpStatus.OK
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse<Basket>> saveProducts (Long id, BasketProductDto basketProductDto) {
        Basket basket = basketRepository.findById(id).orElse(null);
        if (basket == null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 20, true, "El id de canasta ingresado no existe"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }

        Product product = productRepository.findByCode(basketProductDto.getCode()).orElse(null);

        if (product == null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 20, true, "El codigo de producto ingresado no existe"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
        boolean existsByProduct = basketProductRepository.existsByProductCode(product.getCode());
        if (existsByProduct) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 20, true, "El producto ingresado ya existe en la canasta"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setBasket(basket);
        basketProduct.setProduct(product);
        basketProduct.setQuantity(basketProductDto.getQuantity());
        List<BasketProduct> products = basket.getProducts();
        products.add(basketProduct);
        basket.setProducts(products);
        Basket saveBasket = basketRepository.save(basket);
        return new ResponseEntity<>(
                new ApiResponse<>(
                        saveBasket, 20, true, "Producto agregado exitosamente"
                ),
                HttpStatus.OK
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse<Basket>> getTotal (Long id) {
        Basket basket = basketRepository.findById(id).orElse(null);
        if (basket == null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(
                            null, 20, true, "El id de canasta ingresado no existe"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
        List<BasketProduct> products = basket.getProducts();
        long total = 0;
        for (BasketProduct basketProduct : products) {
            Long price = basketProduct.getProduct().getPrice();
            Long quantity = basketProduct.getQuantity();
            total = total + (price * quantity);
        }
        return new ResponseEntity<>(
                new ApiResponse<>(
                        null, 20, true, "El total de su canasta es: " + total
                ),
                HttpStatus.OK
        );
    }



}
