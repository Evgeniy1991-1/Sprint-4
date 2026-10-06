package ru.sprint.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    // ===================== ШАПКА (HEADER) =====================

    private static final By yandexLogo =
            By.cssSelector("a[class*='LogoYandex']");

    private static final By scooterLogo =
            By.cssSelector("a[class*='LogoScooter']");

    private static final By disclaimerText =
            By.cssSelector("div[class*='Disclaimer']");

    private static final By orderButtonInHeader =
            By.xpath("(//button[text()='Заказать'])[1]");

    private static final By orderStatusButton =
            By.xpath("//button[contains(@class,'Header_Link') and text()='Статус заказа']");

    private static final By orderNumberInput =
            By.cssSelector("input[placeholder='Введите номер заказа']");

    private static final By inputErrorMessage =
            By.cssSelector("div[class*='Input_ErrorMessage']");

    private static final By goButton =
            By.xpath("//button[contains(@class,'Header_Button') and text()='Go!']");

    // ===================== ПЕРВЫЙ ЭКРАН (HERO) =====================

    private static final By heroTitle =
            By.cssSelector("div[class*='Home_Header']");

    private static final By heroSubHeader =
            By.xpath("//div[contains(@class,'Home_Header')]//div[contains(@class,'SubHeader')]");

    private static final By blueprintImage =
            By.cssSelector("img[src*='blueprint']");

    private static final By scooterImage =
            By.xpath("//div[contains(@class,'Home_Scooter')]//img");

    private static final By arrowDownImage =
            By.cssSelector("img[alt='Scroll down']");

    private static final By specModel =
            By.xpath("//div[contains(@class,'Home_Column') and contains(text(),'Toxic PRO')]");

    private static final By specMaxSpeed =
            By.xpath("//div[contains(@class,'Home_Column') and text()='40 км/ч']");

    private static final By specRange =
            By.xpath("//div[contains(@class,'Home_Column') and text()='80 км']");

    private static final By specMaxWeight =
            By.xpath("//div[contains(@class,'Home_Column') and text()='120 кг']");

    // ===================== БЛОК «КАК ЭТО РАБОТАЕТ» =====================

    private static final By howItWorksTitle =
            By.xpath("//div[contains(@class,'Home_SubHeader') and text()='Как это работает']");

    private static final By step1Title =
            By.xpath("//div[contains(@class,'Home_Status') and text()='Заказываете самокат']");

    private static final By step2Title =
            By.xpath("//div[contains(@class,'Home_Status') and text()='Курьер привозит самокат']");

    private static final By step3Title =
            By.xpath("//div[contains(@class,'Home_Status') and text()='Катаетесь']");

    private static final By step4Title =
            By.xpath("//div[contains(@class,'Home_Status') and text()='Курьер забирает самокат']");

    private static final By orderButtonInHowItWorks =
            By.xpath("(//button[text()='Заказать'])[2]");

    // ===================== БЛОК «ВОПРОСЫ О ВАЖНОМ» (FAQ) =====================

    private static final By faqTitle =
            By.xpath("//div[contains(@class,'Home_SubHeader') and text()='Вопросы о важном']");

    private static final By faqCostAndPayment =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'Сколько это стоит')]");

    private static final By faqMultipleScooters =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'несколько самокатов')]");

    private static final By faqRentalTime =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'рассчитывается время')]");

    private static final By faqSameDayOrder =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'на сегодня')]");

    private static final By faqExtendOrReturn =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'продлить')]");

    private static final By faqCharger =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'зарядку')]");

    private static final By faqCancelOrder =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'отменить')]");

    private static final By faqOutsideMkad =
            By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'МКАД')]");

    private static final By allAccordionHeadings =
            By.cssSelector("div[data-accordion-component='AccordionItemButton']");

    private static final By allAccordionPanels =
            By.cssSelector("div[data-accordion-component='AccordionItemPanel']");

    // ===================== КУКИ =====================

    private static final By cookieButton = By.id("rcc-confirm-button");

    // ===================== URL =====================

    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    // ===================== КОНСТРУКТОР =====================

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // ===================== ВСПОМОГАТЕЛЬНЫЙ МЕТОД =====================

    private void clickWithFallback(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(locator))
                    .click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    // ===================== БАЗОВЫЕ МЕТОДЫ =====================

    public HomePage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void acceptCookiesIfPresent() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(cookieButton))
                    .click();
        } catch (Exception e) {
            // Баннер отсутствует — игнорируем
        }
    }

    public HomePage scrollToFAQ() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});",
                        driver.findElement(faqTitle));
        return this;
    }

    public HomePage scrollToHowItWorks() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});",
                        driver.findElement(howItWorksTitle));
        return this;
    }

    // ===================== МЕТОДЫ ШАПКИ =====================

    public void clickOrderButtonInHeader() {
        clickWithFallback(orderButtonInHeader);
    }

    public void clickOrderStatusButton() {
        clickWithFallback(orderStatusButton);
    }

    public HomePage enterOrderNumber(String number) {
        driver.findElement(orderNumberInput).sendKeys(number);
        return this;
    }

    public void clickGoButton() {
        clickWithFallback(goButton);
    }

    public String getInputErrorMessage() {
        return driver.findElement(inputErrorMessage).getText();
    }

    public boolean isYandexLogoDisplayed() {
        return driver.findElement(yandexLogo).isDisplayed();
    }

    public boolean isScooterLogoDisplayed() {
        return driver.findElement(scooterLogo).isDisplayed();
    }

    public String getDisclaimerText() {
        return driver.findElement(disclaimerText).getText();
    }

    public boolean isOrderNumberInputDisplayed() {
        return driver.findElement(orderNumberInput).isDisplayed();
    }

    // ===================== МЕТОДЫ ПЕРВОГО ЭКРАНА ====================

    public String getHeroTitleText() {
        return driver.findElement(heroTitle).getText();
    }

    public boolean isHeroSubHeaderDisplayed() {
        return driver.findElement(heroSubHeader).isDisplayed();
    }

    public String getSpecModelText() {
        return driver.findElement(specModel).getText();
    }

    public String getSpecMaxSpeedText() {
        return driver.findElement(specMaxSpeed).getText();
    }

    public String getSpecRangeText() {
        return driver.findElement(specRange).getText();
    }

    public String getSpecMaxWeightText() {
        return driver.findElement(specMaxWeight).getText();
    }

    // ===================== МЕТОДЫ БЛОКА «КАК ЭТО РАБОТАЕТ» =====================

    public String getHowItWorksTitleText() {
        return driver.findElement(howItWorksTitle).getText();
    }

    public String getStepText(int stepNumber) {
        By locator;
        switch (stepNumber) {
            case 1:
                locator = step1Title;
                break;
            case 2:
                locator = step2Title;
                break;
            case 3:
                locator = step3Title;
                break;
            case 4:
                locator = step4Title;
                break;
            default:
                throw new IllegalArgumentException("Шаг должен быть от 1 до 4");
        }
        return driver.findElement(locator).getText();
    }

    public void clickOrderButtonInHowItWorks() {
        clickWithFallback(orderButtonInHowItWorks);
    }

    public boolean isOrderButtonInHowItWorksDisplayed() {
        return driver.findElement(orderButtonInHowItWorks).isDisplayed();
    }

    // ===================== МЕТОДЫ FAQ =====================

    public String getFaqTitleText() {
        return driver.findElement(faqTitle).getText();
    }

    public int getFaqQuestionsCount() {
        return driver.findElements(allAccordionHeadings).size();
    }

    public void clickAccordionQuestion(String questionText) {
        By locator = By.xpath(
                "//div[@data-accordion-component='AccordionItemButton' and contains(text(),'"
                        + questionText + "')]"
        );
        clickWithFallback(locator);
    }

    public boolean isAccordionExpanded(String questionText) {
        String ariaExpanded = driver.findElement(
                By.xpath("//div[@data-accordion-component='AccordionItemButton' "
                        + "and contains(text(),'" + questionText + "')]")
        ).getAttribute("aria-expanded");
        return "true".equals(ariaExpanded);
    }

    public String getAccordionAnswerText(String questionText) {
        String panelId = driver.findElement(
                By.xpath("//div[@data-accordion-component='AccordionItemButton' "
                        + "and contains(text(),'" + questionText + "')]")
        ).getAttribute("aria-controls");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(panelId)));

        return driver.findElement(By.id(panelId)).getText();
    }
}
