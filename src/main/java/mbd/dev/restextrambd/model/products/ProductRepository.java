package mbd.dev.restextrambd.model.products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    boolean existsByCode(String code);

    Optional<Product> findByCode(String code);
}
