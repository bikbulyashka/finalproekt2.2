package ru.yandex.praktikum.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutFirstPageScooter {
    private WebDriver driver;

    //поле Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //поле Фамилия
    private By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //поле Адрес
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле Станция
    private By stationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //поле Телефон
    private By numberPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");
    //окно про аренду
    private By leaseWindow = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Про аренду']");


    public CheckoutFirstPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    //ввод поля имя
    public void setNameField(String username) {
        driver.findElement(nameField).sendKeys(username);
    }

    //ввод поля фамилия
    public void setLastNameField(String lastname) {
        driver.findElement(lastNameField).sendKeys(lastname);
    }

    //ввод поля адрес
    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //ввод поля станция
    public void setStationField(String station) {
        driver.findElement(stationField).sendKeys(station, Keys.DOWN, Keys.ENTER);
    }

    //ввод поля телефон
    public void setNumberPhone(String phone) {
        driver.findElement(numberPhone).sendKeys(phone);
    }

    //нажатие на кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
    //метод ожидания прогрузки 2 страницы про аренду
    public void waitForLoadLeaseWindow() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(leaseWindow).getText() != null
                && !driver.findElement(leaseWindow).getText().isEmpty()
        ));
    }
}
