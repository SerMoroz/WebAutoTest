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
import static org.testng.Assert.*;


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
        assertTrue(linkedinHomePage.isLoaded(), "Home page is not loaded");

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
        assertTrue(linkedinLoginPage.isLoaded(), "User is not on login page.");

    }




    @DataProvider
     public Object[][] shortUserEmailAndPassword() {
         return new Object[][]{
              {"++", "++++++","Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
              {"++@com", "++++++", "Укажите действительный адрес эл. почты.", ""},
              {"77", "777777", "Обязательно включите в номер значок «+» и код своей страны.", ""},
              {"7", "77", "","Пароль должен содержать не менее 6 символов."},
              {"z", "zz", "Слишком короткий текст (минимальная длина – 3 симв., введено – 1 симв.).", "Пароль должен содержать не менее 6 символов."},
              {"zz", "zz","Слишком короткий текст (минимальная длина – 3 симв., введено – 2 симв.).", "Пароль должен содержать не менее 6 символов." },
            //{"1X129", "", "Слишком длинный текст: максимальная длина – 128 симв., введено 129 симв.", ""},
            //{"", "1X401", "","Пароль должен содержать не более 400 символов"},
           // {"ok.android2015@gmail.com", "wrongpass", "", "Это неверный пароль. Повторите попытку или измените пароль."}

         };
     }
    @Test(dataProvider = "shortUserEmailAndPassword")
    public void validateShortUserEmailAndPassword(String userEmail, String userPass, String confirmationEmailText, String confirmationPassText) {
        linkedinLoginPage.login(userEmail, userPass );
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        assertEquals( linkedinLoginSubmitPage.getUserPasswordValidationText(), confirmationPassText, "userPass field has wrong validation message text");
        assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(), confirmationEmailText, "userEmail field has wrong validation message text");

       // Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page");
       //  Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");

    }


    @Test
    public void negativeLoginTest() {

        linkedinLoginPage.login("mail@example.com", "Test1234567890");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        assertEquals(linkedinLoginSubmitPage.getAlertBoxText(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert box has incorrect message");
    }


}