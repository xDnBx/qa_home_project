package ui.data;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    String firstName;
    String lastName;
    String email;
    String gender;
    String mobilePhone;
    String dayOfBirth;
    List<String> subjects;
    List<String> hobbies;
    String currentAddress;
    String state;
    String city;
}