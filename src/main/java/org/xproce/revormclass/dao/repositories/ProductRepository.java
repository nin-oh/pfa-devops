package org.xproce.revormclass.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.xproce.revormclass.dao.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByDescriptionContaining(String keyword, Pageable pageable);

    Page<Product> findByVendorContaining(String keyword, Pageable pageable);

    Page<Product> findByCategoryContaining(String keyword, Pageable pageable);
    List<Product> findByPrice(Double keyword);

    @Query("SELECT p FROM Product p WHERE   ( ?1 IS NULL OR p.description LIKE %?1% ) AND   ( ?2 IS NULL OR p.price >= ?2 ) AND  ( ?3 IS NULL OR p.price <= ?3 ) AND   (?4 IS NULL OR p.category.Title LIKE %?4%) AND   (?5 IS NULL OR p.vendor.title LIKE %?5%) ")
//    @Query("SELECT p FROM Product p WHERE p.description LIKE %:description%  AND p.price >= :minPrice AND p.price <= :maxPrice AND p.category.Title LIKE %:category% AND   p.vendor.title = :vendor")
    Page<Product> searchProducts( String description,
                                  Double minPrice,
                                  Double maxPrice,
                                  String category,
                                  String vendor,Pageable pageable);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.Title = :categoryTitle")
    long countByCategory(@Param("categoryTitle") String categoryTitle);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.vendor.title = :vendorTitle")
    long countByVendor(@Param("vendorTitle") String vendorTitle);
}
