package ru.sprint.scooter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FaqTest extends BaseTest {

    static Stream<Arguments> faqDataProvider() {
        return Stream.of(
                Arguments.of("Сколько это стоит", "400 рублей"),
                Arguments.of("несколько самокатов", "один заказ — один самокат"),
                Arguments.of("рассчитывается время", "суточная аренда закончится"),
                Arguments.of("на сегодня", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of("продлить", "позвонить в поддержку"),
                Arguments.of("зарядку", "полной зарядкой"),
                Arguments.of("отменить", "пока самокат не привезли"),
                Arguments.of("МКАД", "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest(name = "Вопрос «{0}» — открывается ответ с текстом «{1}»")
    @MethodSource("faqDataProvider")
    void accordionOpensAndShowsCorrectAnswer(String questionFragment, String expectedAnswerFragment) {
        homePage.scrollToFAQ();
        homePage.clickAccordionQuestion(questionFragment);

        assertTrue(homePage.isAccordionExpanded(questionFragment),
                "Аккордеон должен быть раскрыт после клика");

        String answer = homePage.getAccordionAnswerText(questionFragment);
        assertTrue(answer.contains(expectedAnswerFragment),
                "Ответ должен содержать: " + expectedAnswerFragment);
    }

    @Test
    void faqHasEightQuestions() {
        homePage.scrollToFAQ();
        assertEquals(8, homePage.getFaqQuestionsCount(),
                "В FAQ должно быть 8 вопросов");
    }
}