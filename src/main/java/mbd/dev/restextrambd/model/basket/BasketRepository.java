package mbd.dev.restextrambd.model.basket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    boolean existsByUserId (Long userId);
}
