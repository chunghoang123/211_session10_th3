package org.example.th3.repository;

import org.example.th3.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    List<Product> findByQuantityLessThan(Long quantity);

    @Modifying
    @Query("""
        update Product p
        set p.quantity = p.quantity - :quantity
        where p.sku = :sku
        """)
    int exportProduct(
            @Param("sku") String sku,
            @Param("quantity") Long quantity
    );

    @Modifying
    @Query("""
        update Product p
        set p.quantity = p.quantity + :quantity
        where p.sku = :sku
        """)
    int importProduct(
            @Param("sku") String sku,
            @Param("quantity") Long quantity
    );
}