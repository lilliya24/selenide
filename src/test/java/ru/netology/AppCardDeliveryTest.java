package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;




public class AppCardDeliveryTest {
    

    @Test
    public void shouldBeSuccessfullyCompleted() {
       open("http://localhost:9999/");
       //$$(".tab-item").last().click();
       $("[data-test-id='city'] input").setValue("Казань");
       String planningDate = generateDate(4,"dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Иван");
        $("[data-test-id='phone'] input").setValue("+79600000000");
        $("[data-test-id='agreement']").click();
        $("button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));







    }

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
}