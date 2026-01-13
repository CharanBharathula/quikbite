package com.quikbite.app.menu.model;

import java.math.BigDecimal;
import java.util.List;
import com.quikbite.app.category.model.Category;

import com.quikbite.app.order.model.OrderItem;
import com.quikbite.app.review.model.Review;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Menu name is required")
    private String name;
    
    private String description;

    private BigDecimal price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id") // this is the foreign key column in the menus table. @JoinColumn is used to specify the column name for the foreign key.
    private Category category;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL) // here orphanRemoval is not needed because order items should not be deleted when a menu is deleted
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval = true is used to automatically delete reviews when a menu is deleted
    private List<Review> reviews;
}
