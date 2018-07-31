import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

import static java.lang.Thread.sleep;


public class BadCodeExample {
        public static void main (String args[]) throws InterruptedException {
        System.out.println("Hello world!!!!");
        WebDriver browser = new FirefoxDriver();
        browser.navigate().to("https://www.google.com.ua");
        WebElement queryField = browser.findElement(By.name("q"));
        queryField.sendKeys("SELENIUM");
        queryField.sendKeys(Keys.ENTER);
        //Verify that results list contain 10  elements
        //Verify every result contains searchterm
            sleep(5000);
        List<WebElement>searchResults = browser.findElements(By.xpath("//div[@class='g']"));
        System.out.println("Results count: "+searchResults.size());
        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            System.out.println(searchResultText);
        }

       // sleep(3000)
        // browser.findElement(By.name("q")).sendKeys("selenium");

        //browser.quit();
       }

   }


