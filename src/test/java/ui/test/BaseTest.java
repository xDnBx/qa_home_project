package ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    @AfterEach()
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}