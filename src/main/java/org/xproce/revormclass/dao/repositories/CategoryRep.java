package org.xproce.revormclass.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;
import java.util.Locale;

public interface CategoryRep extends JpaRepository<Category, Integer> {

}
