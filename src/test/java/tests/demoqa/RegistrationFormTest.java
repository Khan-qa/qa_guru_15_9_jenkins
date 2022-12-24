package tests.demoqa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTest extends TestBase {
    @Test
    void firstTest() {
        step("Open reg form", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });

        step("Fill form", () -> {
            $("#firstName").setValue("Хан");
            $("#lastName").setValue("Ханов");
            $("#userEmail").setValue("han@hanov.com");
            $("[for=gender-radio-1]").click();
            $("#userNumber").setValue("7123456789");

            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("May");
            $(".react-datepicker__year-select").selectOption("1999");
            $(".react-datepicker__day--017").click();

            $("#subjectsInput").setValue("Physics").pressEnter();

            $("[for=hobbies-checkbox-3]").click();
            $("#uploadPicture").uploadFromClasspath("1.png");
            $("#currentAddress").setValue("Han KZ");

            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
            $("#submit").click();
        });

        step("Chek results form", () -> {
            $(".modal-dialog").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive table").shouldHave(text("Хан"), text("Ханов"),
                    text("han@hanov.com"), text("7123456789"));
        });
    }

}
