package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("language")
@Epic("Language change ")
@Owner("Lina Alekseeva")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Language")
public class LanguageСhange extends TestBase {

    @ParameterizedTest
    @CsvSource(value = {
            "简体中文 (упрощенный китайский)",
            "한국어 (корейский)",
            "English (английский)",
            "Français (французский)",
            "Svenska (шведский)"})
    void languageTestForm(
            String itemName) {
        step("Fill in the form fields and click the button", () -> {
            open("/");
            languagePage
                    .menu()
                    .getName(itemName);
        });
    }
}
