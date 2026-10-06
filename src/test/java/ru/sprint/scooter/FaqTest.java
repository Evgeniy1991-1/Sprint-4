package ru.sprint.scooter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FaqTest extends BaseTest {

    @Test
    public void checkCostAndPaymentAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("Сколько это стоит");
        String answer = homePage.getAccordionAnswerText("Сколько это стоит");
        assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.", answer);
    }

    @Test
    public void checkMultipleScootersAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("несколько самокатов");
        String answer = homePage.getAccordionAnswerText("несколько самокатов");
        assertEquals("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", answer);
    }

    @Test
    public void checkRentalTimeAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("рассчитывается время");
        String answer = homePage.getAccordionAnswerText("рассчитывается время");
        assertEquals("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", answer);
    }

    @Test
    public void checkSameDayOrderAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("на сегодня");
        String answer = homePage.getAccordionAnswerText("на сегодня");
        assertEquals("Только начиная с завтрашнего дня. Но скоро станем расторопнее.", answer);
    }

    @Test
    public void checkExtendOrReturnAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("продлить");
        String answer = homePage.getAccordionAnswerText("продлить");
        assertEquals("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", answer);
    }

    @Test
    public void checkChargerAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("зарядку");
        String answer = homePage.getAccordionAnswerText("зарядку");
        assertEquals("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", answer);
    }

    @Test
    public void checkCancelOrderAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("отменить");
        String answer = homePage.getAccordionAnswerText("отменить");
        assertEquals("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", answer);
    }

    @Test
    public void checkOutsideMkadAnswer() {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion("МКАД");
        String answer = homePage.getAccordionAnswerText("МКАД");
        assertEquals("Да, обязательно. Всем самокатов! И Москве, и Московской области.", answer);
    }
}
