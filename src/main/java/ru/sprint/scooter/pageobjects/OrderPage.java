package ru.sprint.scooter.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;

    // ===================== СТРАНИЦА 1 =====================

    private static final By orderHeader =
            By.xpath("//div[contains(@class,'Order_Header') and contains(text(),'Для кого')]");

    private static final By nameField =
            By.xpath("//input[@placeholder='* Имя']");

    private static final By surnameField =
            By.xpath("//input[@placeholder='* Фамилия']");

    private static final By addressField =
            By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    private static final By metroField =
            By.xpath("//input[@placeholder='* Станция метро']");

    private static final By phoneField =
            By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    private static final By nextButton =
            By.xpath("//button[text()='Далее']");

    // ===================== СТРАНИЦА 2 =====================

    private static final By rentHeader =
            By.xpath("//div[contains(@class,'Order_Header') and contains(text(),'Про аренду')]");

    private static final By dateField =
            By.xpath("//input[@placeholder='* Когда привезти самокат']");

    private static final By rentalPeriodDropdown =
            By.className("Dropdown-control");

    private static final By blackColorCheckbox =
            By.xpath("//label[contains(text(),'чёрный жемчуг')]");

    private static final By greyColorCheckbox =
            By.xpath("//label[contains(text(),'серая безысходность')]");

    private static final By commentField =
            By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопка «Заказать» на форме — исключаем шапку и модальное окно
    private static final By orderButton =
            By.xpath("//button[text()='Заказать' and not(ancestor::*[contains(@class,'Header')])]");

    // ===================== МОДАЛЬНОЕ ОКНО ПОДТВЕРЖДЕНИЯ =====================

    // Модальное окно — ищем по тексту заголовка
    private static final By confirmModal =
            By.xpath("//div[contains(text(),'Хотите оформить заказ')]");

    // Кнопка «Да» — внутри модального окна
    private static final By confirmButton =
            By.xpath("//div[contains(@class,'Order_Modal')]//button[text()='Да']");

    // ===================== МОДАЛЬНОЕ ОКНО УСПЕШНОГО ЗАКАЗА =====================

    private static final By successMessage =
            By.xpath("//div[contains(text(),'Заказ оформлен')]");

    // ===================== КОНСТРУКТОР =====================

    public OrderPage(WebDriver driver) {
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

    // ===================== МЕТОДЫ СТРАНИЦЫ 1 =====================

    public boolean isOrderPageOpened() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public OrderPage enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public OrderPage enterSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
        return this;
    }

    public OrderPage enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
        return this;
    }

    public OrderPage selectMetroStation(String stationName) {
        driver.findElement(metroField).click();
        driver.findElement(metroField).sendKeys(stationName);
        By option = By.xpath("//div[text()='" + stationName + "']");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(option))
                .click();
        return this;
    }

    public OrderPage enterPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
        return this;
    }

    public OrderPage clickNextButton() {
        clickWithFallback(nextButton);
        return this;
    }

    public OrderPage fillFirstPage(String name, String surname, String address,
                                   String metro, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        selectMetroStation(metro);
        enterPhone(phone);
        clickNextButton();
        return this;
    }

    // ===================== МЕТОДЫ СТРАНИЦЫ 2 =====================

    public OrderPage setDate(String date) {
        WebElement input = driver.findElement(dateField);
        input.click();
        input.sendKeys(date);
        input.sendKeys(Keys.ENTER);
        return this;
    }

    public OrderPage selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        By option = By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + period + "']");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(option))
                .click();
        return this;
    }

    public OrderPage selectBlackColor() {
        driver.findElement(blackColorCheckbox).click();
        return this;
    }

    public OrderPage selectGreyColor() {
        driver.findElement(greyColorCheckbox).click();
        return this;
    }

    public OrderPage enterComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    public OrderPage clickOrderButton() {
        clickWithFallback(orderButton);
        return this;
    }

    // Подтвердить заказ — нажать «Да» в модальном окне
    public OrderPage confirmOrder() {
        // Ждём появления модального окна по тексту
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmModal));

        // Пауза для анимации
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Кликаем «Да»: сначала обычный клик, потом через Actions, потом через JS
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(confirmButton));

        try {
            button.click();
        } catch (Exception e1) {
            try {
                new Actions(driver).moveToElement(button).click().perform();
            } catch (Exception e2) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }
        }
        return this;
    }

    public OrderPage fillSecondPage(String date, String rentalPeriod,
                                    boolean black, boolean grey, String comment) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(rentHeader));

        setDate(date);
        selectRentalPeriod(rentalPeriod);
        if (black) {
            selectBlackColor();
        }
        if (grey) {
            selectGreyColor();
        }
        enterComment(comment);
        clickOrderButton();
        confirmOrder();
        return this;
    }

    // ===================== МЕТОДЫ ПРОВЕРКИ ====================

    public boolean isSuccessMessageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessageText() {
        return driver.findElement(successMessage).getText();
    }
}
