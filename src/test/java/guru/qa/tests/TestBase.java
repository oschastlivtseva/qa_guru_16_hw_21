package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.drivers.BrowserStackDriver;
import guru.qa.helpers.TestAttachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = BrowserStackDriver.class.getName();
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void addTestAttachments() {
        String sessionId = sessionId().toString();
//        TestAttachment.screenshotAs("Screenshot");
        TestAttachment.pageSource("Page source");
        closeWebDriver();
        TestAttachment.addVideo("Video", sessionId);
    }

}
