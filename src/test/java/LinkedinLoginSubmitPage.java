import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LinkedinLoginSubmitPage {

    private WebDriver browser;
    //WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));

    private WebElement alertBox;
    private WebElement userEmailValidationText;
    private WebElement userPasswordValidationText;


    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }
    private void initElements(){
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        userEmailValidationText = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
        userPasswordValidationText = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));

    }


    public String getAlertBoxText() {
        return alertBox.getText();

    }
    public String getUserEmailValidationText() {
        return userEmailValidationText.getText();

    }

    public String getUserPasswordValidationText() {
        return userPasswordValidationText.getText();

    }


    public boolean isLoaded(){
        return alertBox.isDisplayed(); // && getCurrentPageTitle().contains("Войти в LinkedIn") && getCurrentPageUrl().contains("/feed/");

    }


}


