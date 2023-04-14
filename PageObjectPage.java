package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObjectPage {
    public WebDriver driver;
    public WebDriverWait waiter;
    public PageObjectPage(WebDriver driver){
        this.driver=driver;
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public WebElement getDynamicElement(By locate){
        return this.waiter.until(ExpectedConditions.presenceOfElementLocated(locate));
    }

}
