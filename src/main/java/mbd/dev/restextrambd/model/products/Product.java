package mbd.dev.restextrambd.model.products;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private String code;

    private String description;
}
