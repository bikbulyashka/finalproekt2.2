package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import ru.yandex.praktikum.page_object.HomePageScooter;
import ru.yandex.praktikum.page_object.CheckoutFirstPageScooter;
import ru.yandex.praktikum.page_object.CheckoutSecondPageScooter;

@RunWith(Parameterized.class)
public class TestOrderScooter {
    private WebDriver driver;
    private String username;
    private String lastname;
    private String address;
    private String station;
    private String phone;
    private String comment;
    private String exectedHeaderFinalOrderScreen = "Заказ оформлен";

    public TestOrderScooter(String username, String lastname, String address, String station, String phone, String comment) {
        this.username = username;
        this.lastname = lastname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] setDataForOdrer() {
        return new Object[][]{
                {"Екатерина", "Бикбулатова", "Сосновоборск", "Красносельская", "89374562233", "Проверка1"},
                {"Екатерина", "Зименкова", "Пенза", "Лубянка", "89374563344", "Проверка2"},
        };
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1800, 1000));
    }

    @Test
    public void orderThroughUpperButton() {
        //создаем объект класса главной страницы
        HomePageScooter objHomePage = new HomePageScooter(driver);
        //переходим на сайт
        objHomePage.open();
        //ждем загрузку главной страницы
        objHomePage.waitForMainPage();
        //отжимаем куки
        objHomePage.clickCookieButton();
        //нажимаем на верхнюю кнопку Заказать
        objHomePage.clickUpperOrderButtonOnHomePageScooter();
        //ждем прогрузку 1 страницы оформления заказа
        objHomePage.waitForLoadCheckoutWindow();

        //создаем объект класса первой страницы оформления заказа
        CheckoutFirstPageScooter objCheckoutFirstPage = new CheckoutFirstPageScooter(driver);
        //вводим имя
        objCheckoutFirstPage.setNameField(username);
        //вводим фамилию
        objCheckoutFirstPage.setLastNameField(lastname);
        //вводим адрес
        objCheckoutFirstPage.setAddressField(address);
        //вводим станцию
        objCheckoutFirstPage.setStationField(station);
        //вводим телефон
        objCheckoutFirstPage.setNumberPhone(phone);
        //нажимаем кнопку Далее
        objCheckoutFirstPage.clickNextButton();
        //ждем прогрузку 2 страницы оформления заказа
        objCheckoutFirstPage.waitForLoadLeaseWindow();

        //создаем объект класса второй страницы оформления заказа
        CheckoutSecondPageScooter objCheckoutSecondPage = new CheckoutSecondPageScooter(driver);
        //нажимаем на календарь
        objCheckoutSecondPage.clickCalendarField();
        //выбираем день в календаре
        objCheckoutSecondPage.selectDay();
        //нажимаем на поле аренды
        objCheckoutSecondPage.clickLeaseField();
        //выбираем срок аренды
        objCheckoutSecondPage.selectDayLease();
        //нажимаем на чекбокс с цветом
        objCheckoutSecondPage.clickBlackCheckBox();
        //вводим комментарий
        objCheckoutSecondPage.setCommentForCourier(comment);
        //нажимаем кнопку заказать
        objCheckoutSecondPage.clickOrderButton();
        //ждем прогрузку модального окна
        objCheckoutSecondPage.waitForLoadHeader();
        //нажимаем Да на модальном окне
        objCheckoutSecondPage.clickYesButton();
        //ждем прогрузку финального окна
        objCheckoutSecondPage.waitForLoadFinalOrderScreen();
        Assert.assertTrue("Ошибка",
                objCheckoutSecondPage.headerFinalOrderScreen().contains(exectedHeaderFinalOrderScreen));
    }

    @Test
    public void orderThroughLowerButton() {
        //создаем объект класса главной страницы
        HomePageScooter objHomePage = new HomePageScooter(driver);
        //переходим на сайт
        objHomePage.open();
        //ждем загрузку главной страницы
        objHomePage.waitForMainPage();
        //отжимаем куки
        objHomePage.clickCookieButton();
        //скроллим дло кнопки
        objHomePage.searchLowerOrderButton();
        //ждем прогрузку 1 страницы оформления заказа
        objHomePage.waitForLoadCheckoutWindow();

        //создаем объект класса первой страницы оформления заказа
        CheckoutFirstPageScooter objCheckoutFirstPage = new CheckoutFirstPageScooter(driver);
        //вводим имя
        objCheckoutFirstPage.setNameField(username);
        //вводим фамилию
        objCheckoutFirstPage.setLastNameField(lastname);
        //вводим адрес
        objCheckoutFirstPage.setAddressField(address);
        //вводим станцию
        objCheckoutFirstPage.setStationField(station);
        //вводим телефон
        objCheckoutFirstPage.setNumberPhone(phone);
        //нажимаем кнопку Далее
        objCheckoutFirstPage.clickNextButton();
        //ждем прогрузку 2 страницы оформления заказа
        objCheckoutFirstPage.waitForLoadLeaseWindow();

        //создаем объект класса второй страницы оформления заказа
        CheckoutSecondPageScooter objCheckoutSecondPage = new CheckoutSecondPageScooter(driver);
        //нажимаем на календарь
        objCheckoutSecondPage.clickCalendarField();
        //выбираем день в календаре
        objCheckoutSecondPage.selectDay();
        //нажимаем на поле аренды
        objCheckoutSecondPage.clickLeaseField();
        //выбираем срок аренды
        objCheckoutSecondPage.selectDayLease();
        //нажимаем на чекбокс с цветом
        objCheckoutSecondPage.clickBlackCheckBox();
        //вводим комментарий
        objCheckoutSecondPage.setCommentForCourier(comment);
        //нажимаем кнопку заказать
        objCheckoutSecondPage.clickOrderButton();
        //ждем прогрузку модального окна
        objCheckoutSecondPage.waitForLoadHeader();
        //нажимаем Да на модальном окне
        objCheckoutSecondPage.clickYesButton();
        //ждем прогрузку финального окна
        objCheckoutSecondPage.waitForLoadFinalOrderScreen();
        Assert.assertTrue("Ошибка",
                objCheckoutSecondPage.headerFinalOrderScreen().contains(exectedHeaderFinalOrderScreen));
    }

    @After
    public void tearDown() {
        //закрываем браузер
        driver.quit();
    }
}
