import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class ChangeDateTest {
    private final LocalDateTime now = LocalDateTime.now();


    private Keys getControl() {
        String os = System.getProperty("os.name");

        if (os.contains("Mac")) {
            return Keys.COMMAND;
        } else {
            return Keys.CONTROL;
        }
    }


    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldSuggestNewDateOnExistingAppointment() {
        DeliveryInfo deliveryInfo = DataGenerator.Delivery.generate("ru", 4);
        $("[data-test-id='city'] .input__control").setValue(deliveryInfo.getCity());
        $("[data-test-id='date'] .input__control").click();
        $("[data-test-id='date'] .input__control").sendKeys(getControl(), "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(deliveryInfo.getDate());
        $("[data-test-id='name'] .input__control").setValue(deliveryInfo.getName());
        $("[data-test-id='phone'] .input__control").setValue(deliveryInfo.getPhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $x("//div[contains(text(), 'Успешно')]").shouldBe(Condition.visible, Duration.ofSeconds(15));

        Selenide.refresh();

        $("[data-test-id='city'] .input__control").setValue(deliveryInfo.getCity());
        $("[data-test-id='date'] .input__control").click();
        $("[data-test-id='date'] .input__control").sendKeys(getControl(), "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(DataGenerator.Delivery.getData(7));
        $("[data-test-id='name'] .input__control").setValue(deliveryInfo.getName());
        $("[data-test-id='phone'] .input__control").setValue(deliveryInfo.getPhone());
        $("[data-test-id='agreement'] .checkbox__box").click();
        $("button .button__text").click();
        $x("//div[contains(text(), 'Перепланировать')]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content .button__text").click();
        $x("//div[contains(text(), 'Успешно')]").shouldBe(Condition.visible, Duration.ofSeconds(15));



    }

}
