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
    private boolean profileNavigationItemDisplayed;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

    @Test
    public void successfulLoginTest() throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        linkedinLoginPage.login("XXX", "XXX");

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        linkedinHomePage.isProfileNavigationItemDisplayed();

        sleep(3000);
        String pageTitle = browser.getTitle();
        Assert.assertEquals(pageTitle, "LinkedIn", "Home page title is wrong.");

        String checkURL = browser.getCurrentUrl();
        Assert.assertEquals(checkURL, "https://www.linkedin.com/feed/", "Home page URL is wrong.");

        Assert.assertTrue( linkedinHomePage.isProfileNavigationItemDisplayed(), "'profileNavigationItem' is not displayed on Home page");


    }


    @Test
    public void negativeLoginTest() {
        WebElement userEmailField = browser.findElement(By.xpath("//*[@id='login-email']"));
        WebElement userPasswordField = browser.findElement(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = browser.findElement(By.xpath("//*[@id='login-submit']"));
        userEmailField.sendKeys("a@b.c");
        userPasswordField.sendKeys("wrong");
        signInButton.click();

        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");

    }


}

