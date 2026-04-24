package ui.test;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.data.User;
import ui.page.MainPage;

import java.io.File;
import java.util.Arrays;

@Feature("Form Test")
public class FormTest extends BaseTest {

    @Test
    @DisplayName("Заполнение формы и проверка результата")
    public void testFillForm() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .gender("Male")
                .mobilePhone("79991234567")
                .dayOfBirth("15 Jan 1990")
                .subjects(Arrays.asList("Maths", "Physics", "Biology"))
                .hobbies(Arrays.asList("Sports", "Music"))
                .currentAddress("г. Москва, ул. Пушкина, д. 5, кв. 105")
                .state("Rajasthan")
                .city("Jaiselmer")
                .build();
        File file = new File("src/test/resources/test-files/test-image.jpg");

        new MainPage()
                .openFormPage()
                .openFormPracticePage()
                .fillPracticePage(user, file)
                .checkData(user.getFirstName() + " " + user.getLastName());
    }
}