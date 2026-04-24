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

        new MainPage()
                .openElementPage()
                .openWebTablePage()
                .fillWebTablePage(user)
                .checkData(user)
                .deleteValue(user);
    }
}