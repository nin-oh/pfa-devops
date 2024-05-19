package org.xproce.revormclass.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.xproce.revormclass.dao.entities.Product;

public interface ProductRepositoryCustom {
    Page<Product> advancedSearch(Pageable pageable, String description, Integer category, Double minPrice, Double maxPrice, String vendor);
}
