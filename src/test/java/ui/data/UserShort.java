package ui.data;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserShort {
    String firstName;
    String lastName;
    String email;
    Integer age;
    Integer salary;
    String department;
}