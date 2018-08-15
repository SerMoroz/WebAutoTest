import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class LinkedinLoginTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                {"mail@example.com", "Password"},
                {"MAIL@example.com", "Password"},

        };
    }


    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Home page is not loaded");

    }

    @DataProvider
    public Object[][] emptyFieldsCombination() {
        return new Object[][]{
                {"", ""},
                {"", "P@ssword123"},
                {"someone@domain.com", ""}

        };
    }


    @Test(dataProvider = "emptyFieldsCombination")
    public void validateEmptyUserEmailAndUserPass(String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on login page.");

    }

    @Test
    public void validateShortUserEmailAndPassword() {
        linkedinLoginPage.login("a", "a");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page");

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(), "Слишком короткий текст (минимальная длина – 3 симв., введено – 1 симв.).", "userEmail field has wrong validation message text");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(), "Пароль должен содержать не менее 6 символов.", "userPass field has wrong validation message text");

    }


    @Test //CorrectEmailAndWrongPassword
    public void negativeLoginTest5() {

        linkedinLoginPage.login("mail@example.com", "Test1234567890");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");
    }


}