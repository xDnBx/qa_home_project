package ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.data.User;

import java.io.File;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
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
        $x("//input[@id='dateOfBirthInput']").setValue(user.getDayOfBirth()).pressEnter();

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
    public void checkData(String expected) {
        $x("//button[@id='submit']").click();

        // Проверка поля Student name
        assertEquals(expected, $x("//td[text()='Student Name']/following-sibling::td").getText(),
                "Имя и фамилия не совпадают");
    }
}