package mbd.dev.restextrambd.model.basketproduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mbd.dev.restextrambd.model.basket.Basket;
import mbd.dev.restextrambd.model.products.Product;

@Entity
@Table(name = "products_basket")
@Data
@RequiredArgsConstructor

public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    private Long quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "basket_id")
    private Basket basket;


}
