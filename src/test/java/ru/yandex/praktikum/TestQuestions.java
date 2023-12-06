package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.praktikum.page_object.HomePageScooter;

@RunWith(Parameterized.class)
public class TestQuestions {
    private WebDriver driver;
    private int numberItemList;
    private String expectedQuestion;
    private String expectedAnswer;

    public TestQuestions(int numberItemList, String expectedQuestion, String expectedAnswer) {
        this.numberItemList = numberItemList;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters()
    public static Object[][] info() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void verificationAccordion() {
        //создаем объект класса главной страницы
        HomePageScooter objHomePage = new HomePageScooter(driver);
        //переходим на сайт
        objHomePage.open();
        //ждем загрузку главной страницы
        objHomePage.waitForMainPage();
        //скроллим до вопросов
        objHomePage.searchAccordionQuestion(numberItemList);
        //получаем список вопросов
        objHomePage.getAcordionQuestion(numberItemList);
        Assert.assertEquals("Вопрос не совпадает", expectedQuestion, objHomePage.getAcordionQuestion(numberItemList));
        Assert.assertEquals("Ответ не совпадает", expectedAnswer, objHomePage.getAccordionAnswer(numberItemList));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
