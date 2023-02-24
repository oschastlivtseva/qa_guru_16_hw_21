package guru.qa.drivers;

import com.codeborne.selenide.WebDriverProvider;
import guru.qa.configs.AuthConfig;
import guru.qa.configs.MobileTestConfig;
import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.URL;

public class BrowserStackDriver implements WebDriverProvider {

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
        MobileTestConfig mobileTestConfig = ConfigFactory.create(MobileTestConfig.class, System.getProperties());

        MutableCapabilities caps = new MutableCapabilities();
        caps.merge(capabilities);

        // Set your access credentials
        caps.setCapability("browserstack.user", authConfig.getBrowserStackUser());
        caps.setCapability("browserstack.key", authConfig.getBrowserStackKey());

        // Set URL of the application under test
        caps.setCapability("app", mobileTestConfig.getApp());
        caps.setCapability("browserstack.networkLogs", mobileTestConfig.browserStackNetworkLogs());

        // Specify device and os_version for testing
        caps.setCapability("device", mobileTestConfig.getDeviceName());
        caps.setCapability("os_version", mobileTestConfig.getOSVersion());

        // Set other BrowserStack capabilities
        caps.setCapability("project", mobileTestConfig.getProjectName());
        caps.setCapability("build", mobileTestConfig.getBuildName());
        caps.setCapability("name", mobileTestConfig.getTestName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and mutable capabilities defined above

//        return new AppiumDriver(new URL(mobileTestConfig.getRemoteURL()), caps);
        return new RemoteWebDriver(new URL(mobileTestConfig.getRemoteURL()), caps) {

            @Override
            public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
                return super.getScreenshotAs(outputType);
            }
        };
    }
}
