package com.quikbite.app.auth_users.model;

import java.time.LocalDateTime;
import java.util.List;

import com.quikbite.app.cart.model.Cart;
import com.quikbite.app.order.model.Order;
import com.quikbite.app.payment.model.Payment;
import com.quikbite.app.role.model.Role;
import com.quikbite.app.review.model.Review;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok annotation to generate getters, setters, and other utility methods
@AllArgsConstructor
@NoArgsConstructor
@Builder // Lombok annotation to implement the builder pattern
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String phoneNumber;
    
    private String profileUrl;

    private String address;
    
    private boolean isActive;

    /* Join table 'user_roles' stores links between User and Role.
    'user_id' points to this User; 'role_id' points to the Role.
     JPA inserts/deletes rows here when a user's roles change. */
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),// what this line does is to map the user_id column in the user_roles table to the id column in the users table.
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // A user can have multiple orders. cascade = CascadeType.ALL means that any operation (like delete) on User will delete all orders associated to that user.
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // A user can have multiple reviews. cascade = CascadeType.ALL means that any operation (like delete) on User will delete all reviews associated to that user.
    private List<Review> reviews;

    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // A user can have multiple payments. cascade = CascadeType.ALL means that any operation (like delete) on User will delete all payments associated to that user.
    private List<Payment> payments;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // A user has one cart. cascade = CascadeType.ALL means that any operation (like delete) on User will delete the cart associated to that user.
    private Cart cart;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
