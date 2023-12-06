package ru.yandex.praktikum.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutSecondPageScooter {

    private WebDriver driver;

    //локатор Когда привезти самокат
    private By calendarField = By.xpath(".//div[@class='react-datepicker__input-container']/input[@placeholder='* Когда привезти самокат']");
    //локатор для даты 31
    private By day31 = By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--031 react-datepicker__day--weekend']");
    //локатор для срока аренды
    private By leaseField = By.xpath(".//div[@class='Dropdown-placeholder']");
    //локатор для выбора срока аренды на 6 суток
    private By sixDays = By.xpath(".//div[@class='Dropdown-option' and text()='шестеро суток']");
    //локатор для выбора в чекбоксе "чёрный жемчуг"
    private By blackCheckBox = By.xpath(".//input[@id='black']");
    //локатор Комментарий для курьера
    private By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // локатор для кнопка Заказать
    private By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //локатор для модального окна подтверждения заказа
    private By modalOrderWindow = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    //локатор для кнопки подтверждения ДА
    private By yesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //локатор для финального экрана заказа
    private By finalOrderScreen = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public CheckoutSecondPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    //метод нажатия на поле календарь
    public void clickCalendarField() {
        driver.findElement(calendarField).click();
    }
    //метод выбора даты
    public void selectDay() {
        driver.findElement(day31).click();
    }
    //метод нажатия на поле аренда
    public void clickLeaseField() {
        driver.findElement(leaseField).click();
    }
    //метод выбора дней аренды
    public void selectDayLease() {
        driver.findElement(sixDays).click();
    }
    //метод выбора цвета в чекбоксе
    public void clickBlackCheckBox() {
        driver.findElement(blackCheckBox).click();
    }
    //метод ввода комментария
    public void setCommentForCourier(String comment) {
        driver.findElement(commentForCourier).sendKeys(comment);
    }
    //метод нажатия на кнопку заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //метод ожидания загрузки модального окна подтверждения заказа
    public void waitForLoadHeader(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(modalOrderWindow));
    }
    //метод подтверждения заказа
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
    //метод ожидания загрузки финального окна подтверждения заказа
    public void waitForLoadFinalOrderScreen(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(finalOrderScreen));
    }
    //метод получения текста заголовка финального окна подтверждения заказа
    public String headerFinalOrderScreen() {
        return driver.findElement(finalOrderScreen).getText();
    }
}
