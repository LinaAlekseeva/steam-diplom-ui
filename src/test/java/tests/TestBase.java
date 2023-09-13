package tests;
import com.codeborne.selenide.Configuration;

import config.RemoteConfig;
import config.WebDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import org.openqa.selenium.remote.DesiredCapabilities;
import pages.AuthorizationPage;
import pages.LanguagePage;
import pages.SearchPage;

import java.net.*;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;


public class TestBase {

    public SearchPage searchPage = new SearchPage();
    public LanguagePage languagePage = new LanguagePage();
    public AuthorizationPage authorizationPage = new AuthorizationPage();
    String email="testemailforjob153@gmail.com";
    String password = "cZ96X3!!!";

    private static final WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    public static RemoteConfig remoteConfig = ConfigFactory.create(RemoteConfig.class, System.getProperties());


    @BeforeAll
    static void beforeAll() {

        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = webDriverConfig.baseUrl();
        Configuration.browser = webDriverConfig.browser();
        Configuration.browserVersion = webDriverConfig.browserVersion();
        Configuration.browserSize = webDriverConfig.browserSize();
        Configuration.timeout = 10000;
        try {
            // instantiate CookieManager
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            CookieStore cookieJar =  manager.getCookieStore();

            // create cookie
            HttpCookie cookie = new HttpCookie("UserName", "John Doe");

            // add cookie to CookieStore for a
            // particular URL
            URL url = new URL("https://store.steampowered.com");
            cookieJar.add(url.toURI(), cookie);
            System.out.println("Added cookie using cookie handler");
        } catch(Exception e) {
            System.out.println("Unable to set cookie using CookieHandler");
            e.printStackTrace();
        }

        if (remoteConfig.url() != null && remoteConfig.password() != null && remoteConfig.login() != null) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub", remoteConfig.login(), remoteConfig.password(), remoteConfig.url());
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
        clearBrowserCookies();
    }

    @BeforeEach
    void addBefore() {
        open("https://store.steampowered.com");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @AfterEach
    void addAfter() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (remoteConfig.url() != null && remoteConfig.password() != null && remoteConfig.login() != null) {
            Attach.addVideo();
        }
        closeWebDriver();
        sleep(1000);
    }

}