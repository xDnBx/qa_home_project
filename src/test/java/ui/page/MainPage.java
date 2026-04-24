package ui.page;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    @Step("Открыть страницу с формами")
    public FormPage openFormPage() {
        Selenide.open("/");
        $x("//div[@class='card-body']/h5[text()='Forms']").click();
        return new FormPage();
    }

    @Step("Открыть страницу с элементами")
    public ElementPage openElementPage() {
        Selenide.open("/");
        $x("//div[@class='card-body']/h5[text()='Elements']").click();
        return new ElementPage();
    }
}