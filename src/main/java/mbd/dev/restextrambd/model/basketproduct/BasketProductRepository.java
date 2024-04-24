package mbd.dev.restextrambd.model.basketproduct;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    boolean existsByProductCode(String id);
}
