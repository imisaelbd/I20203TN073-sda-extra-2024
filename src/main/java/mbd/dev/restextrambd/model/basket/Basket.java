package mbd.dev.restextrambd.model.basket;

import jakarta.persistence.*;
import lombok.Data;
import mbd.dev.restextrambd.model.basketproduct.BasketProduct;
import mbd.dev.restextrambd.model.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
@Data

public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketProduct> products = new ArrayList<>();
}
