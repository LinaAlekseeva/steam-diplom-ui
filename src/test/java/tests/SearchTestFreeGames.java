package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("search")
@Epic("Search and adding to basket ")
@Feature("Search a item by typing")
@Owner("Lina Alekseeva")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Search")
public class SearchTestFreeGames extends TestBase {

    @ParameterizedTest
    @CsvSource({
            "Warframe",
            " ",
            " ",
            " "})
    void searchTestForm(
            String itemName) {
        step("Fill in the form fields and click the button", () -> {
            open("");
            searchPage
                    .searchGame(itemName);
        });
        step("Request Validation", () -> {
            searchPage.verifyResults(itemName);

        });
        step("Selecting the found product and adding it to the List", () -> {
            searchPage.choice()
                    .addToList();
            authorizationPage.authorization(email, password);
        });
        step("Request Validation", () -> {
            searchPage.verifyResultsAll();
        });
    }
}