package ru.sprint.scooter;

import org.junit.jupiter.api.Test;
import ru.sprint.scooter.pageobjects.OrderPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest extends BaseTest {

    private String getTomorrowDate() {
        return LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
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

        assertTrue(orderPage.isConfirmModalDisplayed(),
                "Должно появиться окно «Хотите оформить заказ?»");
    }

    @Test
    public void orderFromHeaderButtonFirstUser() {
        homePage.clickOrderButtonInHeader();
        completeOrder("Иван", "Иванов", "ул. Тверская, д. 1",
                "Черкизовская", "+79991234567",
                "сутки", true, false, "Тестовый заказ №1");
    }

    @Test
    public void orderFromHeaderButtonSecondUser() {
        homePage.clickOrderButtonInHeader();
        completeOrder("Анна", "Смирнова", "пр. Мира, д. 10",
                "Сокольники", "+79997654321",
                "двое суток", false, true, "Позвонить за час");
    }

    @Test
    public void orderFromHowItWorksButtonFirstUser() {
        homePage.scrollToHowItWorks();
        homePage.clickOrderButtonInHowItWorks();
        completeOrder("Иван", "Иванов", "ул. Тверская, д. 1",
                "Черкизовская", "+79991234567",
                "сутки", true, false, "Тестовый заказ №1");
    }

    @Test
    public void orderFromHowItWorksButtonSecondUser() {
        homePage.scrollToHowItWorks();
        homePage.clickOrderButtonInHowItWorks();
        completeOrder("Анна", "Смирнова", "пр. Мира, д. 10",
                "Сокольники", "+79997654321",
                "двое суток", false, true, "Позвонить за час");
    }
}
