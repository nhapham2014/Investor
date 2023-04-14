package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class EmailAlertPage extends PageObjectPage {
    public WebDriver driver;
    @FindBy(id = "iframe-e-mail-alert")
    public WebElement iframe;
    @FindBy(xpath = "//div[@class=\"announcement-group\"]/div[4]/div/span/input")
    public WebElement cbxSECAlert;
    @FindBy(xpath = "//div[@class=\"announcement-group\"]/div[3]/div/span/input")
    public WebElement cbxHKEXAlert;
    @FindBy(xpath = "//div[contains(@class,\"service-table\")]/div[2]/div/span/input")
    public WebElement cbxWebcasts;
    @FindBy(xpath = "//div[contains(@class,\"service-table\")]/div[3]/div/span/input")
    public WebElement cbxNews;
    @FindBy(xpath = "//div[contains(@class,\"service-table\")]/div[4]/div/span/input")
    public WebElement cbxEvent;
    @FindBy(id = "txtEmail")
    public WebElement txbEmail;
    @FindBy(id = "txtSurName")
    public WebElement txbLastName;
    @FindBy(id = "txtFirstName")
    public WebElement txbFirstName;
    @FindBy(id = "txtCompany")
    public WebElement txbCompany;
    @FindBy(xpath = "//button[text()=\"Subscribe\"]")
    public WebElement btnSubscribe;
    /*@FindBy(xpath = "//*[@id=\\\"dialogModal\\\"]/div")
    public WebElement modal;*/
    @FindBy(xpath = "//*[@id=\"wrapper\"]/div[1]/div[6]/div[2]/div/div[2]/button[2]")
    public WebElement btnAgree;
    public By lcmodal = By.xpath("//*[@id=\"dialogModal\"]/div");
    public By lcAgree= By.xpath("//*[@id=\"dialogModal\"]/div/div/div[2]/div/div[2]/button[2]");
    public By lctextConfirm= By.xpath("//*[@id=\"dialogModal\"]/div/div/div[2]/div/div[2]");

    By lcErrorNullData = By.xpath("//div[contains(@class,\"error-message alert alert-danger\")]");
    By lcErrorEmail = By.className("validator-error");


    public EmailAlertPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getErrorWhenEmptyData() {
        WebElement txtErrorNullData = this.getDynamicElement(lcErrorNullData);
        return txtErrorNullData.getText();
    }

    public String getErrorWhenEmptyEmail() {
        WebElement txtErrorEmail = this.getDynamicElement(lcErrorEmail);
        return txtErrorEmail.getText();
    }

    public void switchtoIfame() {
        this.driver.switchTo().frame(iframe);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean CheckExistErrorEmail() {

        if (driver.findElement(lcErrorEmail).getAttribute("aria-hidden").isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean CheckPopupAfterInputData() {
        if(driver.findElements(lcmodal).size()>0){
    return true;
        }
        return false;

    }
//    public String GettextPopupAfterAgree() throws InterruptedException {
//        /*//WebElement modal= driver.findElement(lcmodal);
//        WebElement btnAgree=modal.findElement(By.xpath("//*[@id=\"dialogModal\"]/div/div/div[2]/div/div[2]/button[2]"));
//        btnAgree.click();
//        Thread.sleep(2000);
//        WebElement text=modal.findElement(By.xpath("//*[@id=\"dialogModal\"]/div/div/div[2]/div/div[2]"));
//        return text.getText();*/
//
//
//    }

}
