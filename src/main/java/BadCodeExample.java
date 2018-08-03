import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.naming.directory.SearchResult;
import java.util.List;
import static java.lang.Thread.sleep;


public class BadCodeExample {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello world!!!!");
        WebDriver browser = new FirefoxDriver();
        browser.navigate().to("https://www.google.com.ua");
        WebElement queryField = browser.findElement(By.name("q"));
        //Enter text in search field
        queryField.sendKeys("SELENIUM");
        queryField.sendKeys(Keys.ENTER);
        sleep(5000);
        //Search result quantity
        List<WebElement> searchResults = browser.findElements(By.xpath("//div[@class='g']"));
        System.out.println("Results count: " + searchResults.size());

        //Verify that results list contain 10  elements
        if (searchResults.size() == 10) {
            System.out.println("Result count is correct");
        }
        else {
            System.out.println("Result count is incorrect");
        }
        //Print list with results
        //Verify that each  result item  contains searchterm
        for (WebElement searchResult : searchResults) {
            String searchResultText = searchResult.getText();
            System.out.println(searchResultText);
            if (searchResultText.contains("Selenium")){
                System.out.println("SearchTerm found");
            }
            else{
                System.out.println("SearchTerm not found");
            }

        }
        //browser.close();
    }
}


