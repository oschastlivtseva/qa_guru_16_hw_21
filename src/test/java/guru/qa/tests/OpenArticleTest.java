package guru.qa.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;

public class OpenArticleTest extends TestBase {
    String searchValue = "Elephant";

    @Test
    public void openArticle() {
        step("Search for articles containing text: '" + searchValue + "'", () -> {
            $(id("org.wikipedia:id/fragment_onboarding_skip_button")).click();
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia:id/search_src_text")).sendKeys(searchValue);
            $$(id("org.wikipedia:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
        });

        step("Click the article with title: '" + searchValue + "'", () -> {
            $$(id("org.wikipedia:id/page_list_item_title")).findBy(text(searchValue)).click();
        });

        step("Check that page with the article is opened", () -> {
            $(id("org.wikipedia:id/page_contents_container")).shouldBe(visible);
        });
    }
}
