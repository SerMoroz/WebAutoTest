import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

    @Test
    public void successfulLoginTest() {

        linkedinLoginPage.login("XXXX", "XXXX");
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Home page is not loaded");


    }

    @Test
    public void negativeLoginTest() {

        linkedinLoginPage.login("ok.com", "Test!");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");

    }


}

