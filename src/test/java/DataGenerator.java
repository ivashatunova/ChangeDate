import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}
    public static class Delivery {
        private final static String datePattern = "dd.MM.yyyy";

        public static String getData(int days) {
            return LocalDateTime.now()
                    .plusDays(days)
                    .format(DateTimeFormatter.ofPattern(datePattern));
        }
        private Delivery() {}
        public static DeliveryInfo generate(String locale, int days) {
            Faker faker = new Faker(new Locale(locale));
            return new DeliveryInfo(
                    faker.address().city(),
                    getData(days),
                    faker.name().fullName(),
                    faker.phoneNumber().cellPhone()
            );
        }
    }
}
