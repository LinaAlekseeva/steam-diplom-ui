package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class SearchPage {
    private SelenideElement searchBox = $("#store_nav_search_term");
    private SelenideElement dropdownBox = $("#searchDropdownBox");
    private SelenideElement box = $("#nav-search-bar-form");
    private SelenideElement searchButton = $("#nav-search-submit-button");
    private SelenideElement choiceProduct = $(".s-image");
    private SelenideElement results = $(".a-size-medium-plus.a-color-base.a-text-bold");
    private SelenideElement verifyResultsSearchName = $(".a-color-state.a-text-bold");
    private SelenideElement addToList = $(".a-button-text.a-text-left");
    private SelenideElement verifyResultsAll = $(".a-form-label");

    public SearchPage searchGame (String itemName) {
        searchBox.click();
        searchBox.setValue(itemName).pressEnter();


        return this;
    }

    public SearchPage setName(String itemName) {
        textBox.click();
        textBox.setValue(itemName).click();
        searchButton.click();
        return this;
    }

    public SearchPage choice() {
        choiceProduct.click();
        return this;
    }

    public SearchPage verifyResults(String itemName) {
        results.should(appear);
        verifyResultsSearchName.shouldHave(text(itemName));
        return this;
    }

    public SearchPage addToList() {
        addToList.click();
        return this;
    }

    public SearchPage verifyResultsAll() {
        verifyResultsAll.equals("Type characters");
        return this;
    }
}