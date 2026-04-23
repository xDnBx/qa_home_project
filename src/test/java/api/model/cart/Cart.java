package api.model.cart;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    Integer id;
    Integer userId;
    LocalDateTime date;
    List<ProductShort> products;
    Integer __v;
}