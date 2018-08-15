import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage {

    private WebDriver browser;

    private WebElement userEmailField;
    private WebElement userPasswordField ;
    private WebElement signInButton ;

    public LinkedinLoginPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }
    private void initElements(){
        userEmailField = browser.findElement(By.xpath("//*[@id='login-email']"));
        userPasswordField = browser.findElement(By.xpath("//*[@id='login-password']"));
        signInButton = browser.findElement(By.xpath("//*[@id='login-submit']"));
    }
    public void login(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getCurrentPageTitle() {
        return browser.getTitle();
    }

    public String getCurrentPageUrl() {
        return browser.getCurrentUrl();

    }
    public boolean isLoaded() {
        return userEmailField.isDisplayed() && getCurrentPageTitle().contains("LinkedIn: Log In or Sign Up");
    }

}


