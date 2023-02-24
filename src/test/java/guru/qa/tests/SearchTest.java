package guru.qa.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class SearchTest extends TestBase {
    String searchValue = "Elephant";

    @Test
    public void searchTest() {
        step("Click button: 'Skip'", () -> {
            $(id("org.wikipedia:id/fragment_onboarding_skip_button")).click();
        });

        step("Click on field: 'Search Wikipedia'", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step("Set in search field value: '" + searchValue + "'", () -> {
            $(id("org.wikipedia:id/search_src_text")).sendKeys(searchValue);
        });

        step("Check that quantity of results more than 0", () -> {
            $$(id("org.wikipedia:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
        });
    }
}
