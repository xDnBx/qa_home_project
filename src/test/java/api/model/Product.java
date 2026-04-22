package api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    Integer id;
    String title;
    Double price;
    String description;
    String category;
    String image;
    Rating rating;
}