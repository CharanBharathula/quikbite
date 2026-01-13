package com.quikbite.app.menu.dtos;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quikbite.app.review.dtos.ReviewDto;
import com.stripe.model.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDto {
    private Long id;
    
    @NotBlank(message = "Menu name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Menu price is required")
    @Positive(message = "Menu price must be positive")
    private String price;
    
    private String imageUrl;

    @NotNull(message = "Category ID is required")
    private Long categoryId;// needed when adding new menu

    private MultipartFile imageFile;

    private List<ReviewDto> reviews;
}
