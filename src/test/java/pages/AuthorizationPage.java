package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {

    private SelenideElement getButtonSignIn= $(".global_action_link");
    private SelenideElement signInEmail = $(".newlogindialog_TextInput_2eKVn");
    private SelenideElement signInPassword = $("#ap_password");
    private SelenideElement remember = $("#base");
    private SelenideElement buttonSignIn = $(".newlogindialog_SubmitButton_2QgFE");

    public AuthorizationPage authorization(String email, String password) {
        getButtonSignIn.click();
        signInEmail.shouldHave(Condition.text("Войти, используя имя аккаунта")).setValue(email).pressTab();
        signInPassword.shouldHave(Condition.text("Войти, используя имя аккаунта")).setValue(password);
        remember.click();
        buttonSignIn.click();
        return this;
    }
}