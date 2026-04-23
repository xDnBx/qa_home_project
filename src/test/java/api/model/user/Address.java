package api.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    Geolocation geolocation;
    String city;
    String street;
    Integer number;
    String zipcode;
}