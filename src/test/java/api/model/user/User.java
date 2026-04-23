package api.model.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Address address;
    Integer id;
    String email;
    String username;
    String password;
    Name name;
    String phone;
    Integer __v;
}