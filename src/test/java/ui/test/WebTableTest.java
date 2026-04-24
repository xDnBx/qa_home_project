package ui.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.data.UserShort;
import ui.page.MainPage;

public class WebTableTest extends BaseTest {

    @Test
    @DisplayName("Заполнение таблицы и проверка результата")
    public void testFillTable() {
        UserShort user = UserShort.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .age(30)
                .salary(200000)
                .department("Main")
                .build();

        UserShort user1 = UserShort.builder()
                .firstName("Sammy")
                .lastName("Pumpkins")
                .email("sammy@example.com")
                .age(30)
                .salary(5000)
                .department("Reception")
                .build();

        new MainPage()
                .openElementPage()
                .openWebTablePage()
                .fillWebTablePage(user)
                .checkData(user)
                .deleteValue(user)
                .editValue(user1);
    }
}