package ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.data.User;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormPage {

    @Step("Заполнение формы")
    public PracticeFormPage fillPracticePage(User user, File file) {
        $x("//input[@id='firstName']").setValue(user.getFirstName());
        $x("//input[@id='lastName']").setValue(user.getLastName());
        $x("//input[@id='userEmail']").setValue(user.getEmail());

        switch (user.getGender()) {
            case "Male" -> $x("//input[@id='gender-radio-1']").click();
            case "Female" -> $x("//input[@id='gender-radio-2']").click();
            case "Other" -> $x("//input[@id='gender-radio-3']").click();
            default -> throw new IllegalArgumentException("Invalid gender: %s".formatted(user.getGender()));
        }

        $x("//input[@id='userNumber']").setValue(user.getMobilePhone());
        $x("//input[@id='dateOfBirthInput']").click();
        $x("//select[@class='react-datepicker__year-select']").shouldBe(visible)
                .selectOption(user.getDayOfBirth().substring(7, 11));
        $x("//select[@class='react-datepicker__month-select']")
                .selectOption(getMonth(user.getDayOfBirth().substring(3, 6)));
        $x("//div[contains(@class, 'react-datepicker__day') and contains(@aria-label, '" +
                user.getDayOfBirth().substring(0, 2) + "')]").click();
        $x("//input[@id='dateOfBirthInput']").pressTab();

        for (int i = 0; i < user.getSubjects().size(); i++) {
            $x("//input[@id='subjectsInput']").setValue(user.getSubjects().get(i));
            $$x("//div[@role='option']")
                    .findBy(Condition.text(user.getSubjects().get(i)))
                    .click();
        }

        ElementsCollection checkboxes = $$x("//input[@type='checkbox' and contains(@id, 'hobbies')]");
        for (SelenideElement checkbox : checkboxes) {
            String checkboxId = checkbox.getAttribute("id");
            String labelText = $x("//label[@for='" + checkboxId + "']").getText();

            if (user.getHobbies().contains(labelText)) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }

        $x("//input[@id='uploadPicture']").uploadFile(file);
        $x("//textarea[@id='currentAddress']").setValue(user.getCurrentAddress());

        $x("//input[@id='react-select-3-input']").setValue(user.getState());
        $$x("//div[@role='option']")
                .findBy(Condition.text(user.getState()))
                .click();

        $x("//input[@id='react-select-4-input']").setValue(user.getCity());
        $$x("//div[@role='option']")
                .findBy(Condition.text(user.getCity()))
                .click();

        return this;
    }

    @Step("Проверка результата")
    public void checkData(User user) {
        $x("//button[@id='submit']").click();
        String birthday = user.getDayOfBirth().substring(0, 2) + " " + getMonth(user.getDayOfBirth().substring(3, 6)) +
                "," + user.getDayOfBirth().substring(7, 11);

        // Проверка поля Student name
        assertEquals(user.getFirstName() + " " + user.getLastName(),
                $x("//td[text()='Student Name']/following-sibling::td").getText(),
                "Имя и фамилия не совпадают");
        assertEquals(user.getEmail(), $x("//td[text()='Student Email']/following-sibling::td").getText(),
                "Email не совпадает");
        assertEquals(user.getGender(), $x("//td[text()='Gender']/following-sibling::td").getText(),
                "Пол не совпадает");
        assertEquals(user.getMobilePhone(), $x("//td[text()='Mobile']/following-sibling::td").getText(),
                "Телефон не совпадает");
        assertEquals(birthday, $x("//td[text()='Date of Birth']/following-sibling::td").getText(),
                "День рождения не совпадает");
        assertEquals(user.getSubjects().getFirst() + ", " + user.getSubjects().get(1) + ", " + user.getSubjects().getLast(),
                $x("//td[text()='Subjects']/following-sibling::td").getText(),
                "Предметы не совпадают");
        assertEquals(user.getHobbies().getFirst() + ", " + user.getHobbies().getLast(),
                $x("//td[text()='Hobbies']/following-sibling::td").getText(),
                "Хобби не совпадают");
        assertEquals(user.getCurrentAddress(), $x("//td[text()='Address']/following-sibling::td").getText(),
                "Адрес не совпадает");
        assertEquals(user.getState() + " " + user.getCity(),
                $x("//td[text()='State and City']/following-sibling::td").getText(),
                "Штат и город не совпадают");
    }

    private String getMonth(String monthName) {
        return switch (monthName) {
            case ("Jan") -> "January";
            case ("Feb") -> "February";
            case ("Mar") -> "March";
            case ("Apr") -> "April";
            case ("May") -> "May";
            case ("Jun") -> "June";
            case ("Jul") -> "July";
            case ("Aug") -> "August";
            case ("Sep") -> "September";
            case ("Oct") -> "October";
            case ("Nov") -> "November";
            case ("Dec") -> "December";
            default -> throw new IllegalArgumentException();
        };
    }
}