package ui.page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ElementPage {
    @Step("Открыть страницу с таблицами")
    public WebTablePage openWebTablePage() {
        $x("//span[@class='text' and text()='Web Tables']").click();
        return new WebTablePage();
    }
}