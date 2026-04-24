package ui.page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class FormPage {
    @Step("Открыть страницу с практическими формами")
    public PracticeFormPage openFormPracticePage() {
        $x("//span[@class='text' and text()='Practice Form']").click();
        return new PracticeFormPage();
    }
}