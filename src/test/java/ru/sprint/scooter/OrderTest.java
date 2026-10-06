package ru.sprint.scooter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.sprint.scooter.pageobjects.OrderPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest extends BaseTest {

    static Stream<Arguments> orderDataProvider() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "ул. Тверская, д. 1",
                        "Черкизовская", "+79991234567",
                        "сутки", true, false, "Тестовый заказ №1"),
                Arguments.of("Анна", "Смирнова", "пр. Мира, д. 10",
                        "Сокольники", "+79997654321",
                        "двое суток", false, true, "Позвонить за час")
        );
    }

    private String getTomorrowDate() {
        return LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @ParameterizedTest(name = "Заказ через верхнюю кнопку: {0} {1}")
    @MethodSource("orderDataProvider")
    void orderFromHeaderButton(String name, String surname, String address,
                               String metro, String phone,
                               String rentalPeriod, boolean black, boolean grey,
                               String comment) {
        homePage.clickOrderButtonInHeader();
        completeOrder(name, surname, address, metro, phone,
                rentalPeriod, black, grey, comment);
    }

    @ParameterizedTest(name = "Заказ через нижнюю кнопку: {0} {1}")
    @MethodSource("orderDataProvider")
    void orderFromHowItWorksButton(String name, String surname, String address,
                                   String metro, String phone,
                                   String rentalPeriod, boolean black, boolean grey,
                                   String comment) {
        homePage.scrollToHowItWorks();
        homePage.clickOrderButtonInHowItWorks();
        completeOrder(name, surname, address, metro, phone,
                rentalPeriod, black, grey, comment);
    }

    private void completeOrder(String name, String surname, String address,
                               String metro, String phone,
                               String rentalPeriod, boolean black, boolean grey,
                               String comment) {
        OrderPage orderPage = new OrderPage(driver);
        assertTrue(orderPage.isOrderPageOpened(),
                "Страница заказа должна открыться");

        orderPage.fillFirstPage(name, surname, address, metro, phone);
        orderPage.fillSecondPage(getTomorrowDate(), rentalPeriod,
                black, grey, comment);

        assertTrue(orderPage.isSuccessMessageDisplayed(),
                "Должно появиться окно с сообщением «Заказ оформлен»");
    }
}