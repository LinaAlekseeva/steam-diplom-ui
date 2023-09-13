package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LanguagePage {
    private SelenideElement openMenu = $("#language_pulldown");
    private SelenideElement language = $(".popup_body popup_menu");
    private SelenideElement choice= $(".popup_menu_item tight");

    public LanguagePage menu() {
        openMenu.click();
        return this;
    }

    public LanguagePage getName(String itemName) {
        language.$(byText(itemName)).click();
        choice.click();
        return this;
    }

}