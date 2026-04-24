package ui.page;

import io.qameta.allure.Step;
import ui.data.UserShort;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTablePage {
    @Step("Добавление нового пользователя в таблицу")
    public WebTablePage fillWebTablePage(UserShort user) {
        $x("//button[@id='addNewRecordButton']").click();
        $x("//input[@id='firstName']").setValue(user.getFirstName());
        $x("//input[@id='lastName']").setValue(user.getLastName());
        $x("//input[@id='userEmail']").setValue(user.getEmail());
        $x("//input[@id='age']").setValue(String.valueOf(user.getAge()));
        $x("//input[@id='salary']").setValue(String.valueOf(user.getSalary()));
        $x("//input[@id='department']").setValue(String.valueOf(user.getDepartment()));

        return this;
    }

    @Step("Проверка результата")
    public WebTablePage checkData(UserShort expectedUser) {
        $x("//button[@id='submit']").click();

        // Проверка всех полей добавленной записи
        assertEquals(expectedUser.getFirstName(), $x("//tbody/tr[4]/td[1]").getText(),
                "Имя не совпадает");
        assertEquals(expectedUser.getLastName(), $x("//tbody/tr[4]/td[2]").getText(),
                "Фамилия не совпадает");
        assertEquals(expectedUser.getAge().toString(), $x("//tbody/tr[4]/td[3]").getText(),
                "Возраст не совпадает");
        assertEquals(expectedUser.getEmail(), $x("//tbody/tr[4]/td[4]").getText(),
                "Email не совпадает");
        assertEquals(expectedUser.getSalary().toString(), $x("//tbody/tr[4]/td[5]").getText(),
                "Зарплата не совпадает");
        assertEquals(expectedUser.getDepartment(), $x("//tbody/tr[4]/td[6]").getText(),
                "Отдел не совпадает");

        return this;
    }

    @Step("Удаление созданной записи из таблицы")
    public void deleteValue(UserShort expectedUser) {
        $x("//span[@id='delete-record-4']").click();

        // Проверка удаления записи
        $("tbody").shouldNotHave(text(expectedUser.getFirstName()));
    }
}