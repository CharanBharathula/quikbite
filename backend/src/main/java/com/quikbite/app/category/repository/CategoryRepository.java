package com.quikbite.app.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quikbite.app.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
