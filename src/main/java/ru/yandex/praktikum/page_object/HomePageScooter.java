package ru.yandex.praktikum.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageScooter {
    private WebDriver driver;

    //верхняя кнопка "Заказать"
    private By upperOrderButtonOnHomePageScooter = By.className("Button_Button__ra12g");
    //нижняя кнопка "Заказать"
    private By lowerOrderButtonOnHomePageScooter = By.xpath(".//button[@class='Button_Button__ra12g']");
    //окно оформления заказа
    private By checkoutWindow = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");
    //выпадающий список вопросов
    private By accordionQuestion = By.xpath(".//div[@class='accordion__heading']");
    //список ответов
    private By accordionAnswer = By.xpath(".//div[contains(@id,'accordion__panel')]/p");
    //адрес сайта
    private String site = "https://qa-scooter.praktikum-services.ru/";
    //главное окно сайта
    private By mainPage = By.className("Home_Header__iJKdX");
    //куки, с которым пришлось повозиться
    private By cookieButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");
    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    //переходим на сайт
    public void open() {
        driver.get(site);
    }
    //метод ожидания прогрузки окна сайта
    public void waitForMainPage() {
        new WebDriverWait(driver, 60).until(driver -> (driver.findElement(mainPage).getText() != null
                && !driver.findElement(mainPage).getText().isEmpty()
        ));
    }
    //метод нажатия на верхнюю кнопку заказать
    public void clickUpperOrderButtonOnHomePageScooter() {
        driver.findElement(upperOrderButtonOnHomePageScooter).click();
    }
    //метод скролла до нижней кнопки Заказать и нажатие на нее
    public void searchLowerOrderButton() {
        WebElement lowerOrderButtonElement = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(lowerOrderButtonOnHomePageScooter));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lowerOrderButtonElement);
        WebElement lowerOrderButtonElementClick = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(lowerOrderButtonOnHomePageScooter));
        lowerOrderButtonElementClick.click();
    }

    //метод ожидания прогрузки окна оформления заказа
    public void waitForLoadCheckoutWindow() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(checkoutWindow).getText() != null
                && !driver.findElement(checkoutWindow).getText().isEmpty()
        ));
    }
    //метод скролла до вопроса и нажатие на него
    public void searchAccordionQuestion(int index) {
        WebElement accordionQuestionElement = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(accordionQuestion));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordionQuestionElement);
        WebElement questionElementIndex = driver.findElements(accordionQuestion).get(index);
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(questionElementIndex));
        questionElementIndex.click();
    }

    //метод получения списка вопросов
    public String getAcordionQuestion(int index) {
        WebElement questionElement = driver.findElements(accordionQuestion).get(index);
        return questionElement.getText();
    }

    //метод получения списка ответов
    public String getAccordionAnswer(int index) {
        WebElement answerElement = driver.findElements(accordionAnswer).get(index);
        return answerElement.getText();
    }
    //метод нажатия на куки
    public void clickCookieButton() {
        WebElement cookie = driver.findElement(cookieButton);
        if (cookie.isDisplayed()) {
            cookie.click();
        }
    }
}
