package vn.camautomation;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.EmailAlertPage;

import java.util.Set;

/**
 * Unit test for simple App.
 */
public class TestEmailAlert {
    public WebDriver driver;
    EmailAlertPage EmailAlertPage;

    @Before
    public void setup() {
        this.driver = new ChromeDriver();
        driver.get("https://ir.bilibili.com/en/investor-resources/?tab=e-mail-alert#e-mail-alert");
        driver.manage().window().maximize();
        this.EmailAlertPage = new EmailAlertPage(driver);
    }

    @Test
    public void Test_ErrorWhenEmptyData() throws InterruptedException {
        Thread.sleep(2000);
        this.driver.switchTo().frame(EmailAlertPage.iframe);
        Thread.sleep(5000);
        if (EmailAlertPage.cbxSECAlert.isSelected()) {
            EmailAlertPage.cbxSECAlert.click();
        }
        if (EmailAlertPage.cbxHKEXAlert.isSelected()) {
            EmailAlertPage.cbxHKEXAlert.click();
        }
        if (EmailAlertPage.cbxWebcasts.isSelected()) {
            EmailAlertPage.cbxWebcasts.click();
        }
        if (EmailAlertPage.cbxNews.isSelected()) {
            EmailAlertPage.cbxNews.click();
        }
        if (EmailAlertPage.cbxNews.isSelected()) {
            EmailAlertPage.cbxNews.click();
        }
        EmailAlertPage.txbEmail.clear();
        EmailAlertPage.txbLastName.clear();
        EmailAlertPage.txbFirstName.clear();
        EmailAlertPage.txbCompany.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", EmailAlertPage.btnSubscribe);
        Thread.sleep(2000);
        EmailAlertPage.btnSubscribe.click();
        Assert.assertEquals("Please select at least one option below.", EmailAlertPage.getErrorWhenEmptyData());

    }

    @Test
    public void Test_CheckExistErrorMessage() throws InterruptedException {
        EmailAlertPage.switchtoIfame();
        Thread.sleep(2000);
        if (!EmailAlertPage.cbxSECAlert.isSelected()) {
            EmailAlertPage.cbxSECAlert.click();
        }
        if (!EmailAlertPage.cbxHKEXAlert.isSelected()) {
            EmailAlertPage.cbxHKEXAlert.click();
        }
        if (!EmailAlertPage.cbxWebcasts.isSelected()) {
            EmailAlertPage.cbxWebcasts.click();
        }
        if (!EmailAlertPage.cbxNews.isSelected()) {
            EmailAlertPage.cbxNews.click();
        }
        if (!EmailAlertPage.cbxNews.isSelected()) {
            EmailAlertPage.cbxNews.click();
        }
        EmailAlertPage.scrollToElement(EmailAlertPage.btnSubscribe);
        Thread.sleep(2000);
        EmailAlertPage.btnSubscribe.click();
        Thread.sleep(2000);
        Assert.assertEquals("", EmailAlertPage.getErrorWhenEmptyData());
    }

    @Test
    public void Test_GetErrorWhenEmptyEmail() throws InterruptedException {
        EmailAlertPage.switchtoIfame();
        Thread.sleep(2000);
        EmailAlertPage.txbEmail.clear();
        EmailAlertPage.scrollToElement(EmailAlertPage.btnSubscribe);
        Thread.sleep(2000);
        EmailAlertPage.btnSubscribe.click();
        Thread.sleep(2000);
        Assert.assertEquals("This is a required field.", EmailAlertPage.getErrorWhenEmptyEmail());
    }

    @Test
    public void Test_CheckExistErrorEmailWhenInputValidData() throws InterruptedException {
        EmailAlertPage.switchtoIfame();
        Thread.sleep(2000);
        EmailAlertPage.txbEmail.clear();
        EmailAlertPage.scrollToElement(EmailAlertPage.btnSubscribe);
        Thread.sleep(2000);
        EmailAlertPage.btnSubscribe.click();
        EmailAlertPage.txbEmail.sendKeys("nha@gmail.com");
        EmailAlertPage.scrollToElement(EmailAlertPage.btnSubscribe);
        Thread.sleep(2000);
        EmailAlertPage.btnSubscribe.click();
        Thread.sleep(2000);
        Assert.assertEquals(false, EmailAlertPage.CheckExistErrorEmail());
    }

    @Test
    public void Test_CheckExistPopup() throws InterruptedException {
        EmailAlertPage.switchtoIfame();
        Thread.sleep(2000);
        if (!EmailAlertPage.cbxNews.isSelected()) {
            EmailAlertPage.cbxNews.click();
        }
        EmailAlertPage.txbEmail.clear();
        EmailAlertPage.txbEmail.sendKeys("nha@gmail.com");
        EmailAlertPage.scrollToElement(EmailAlertPage.btnSubscribe);
        Thread.sleep(2000);
        EmailAlertPage.btnSubscribe.click();
        Thread.sleep(2000);
        Assert.assertEquals(true, EmailAlertPage.CheckPopupAfterInputData());
    }

    @Test
    public void Test_DisplayConfirmPopup() throws InterruptedException {
        EmailAlertPage.switchtoIfame();
        Thread.sleep(2000);
        if (!EmailAlertPage.cbxNews.isSelected()) {
            EmailAlertPage.cbxNews.click();
        }
        EmailAlertPage.txbEmail.clear();
        EmailAlertPage.txbEmail.sendKeys("nha@gmail.com");
        EmailAlertPage.scrollToElement(EmailAlertPage.btnSubscribe);
        Thread.sleep(2000);
        EmailAlertPage.btnSubscribe.click();
        Thread.sleep(2000);
        WebElement modal= driver.findElement(EmailAlertPage.lcmodal);
        WebElement btnAgree = modal.findElement(EmailAlertPage.lcAgree);
        btnAgree.click();
        Thread.sleep(2000);
        WebElement txtConfirm = modal.findElement(EmailAlertPage.lctextConfirm);
        Assert.assertEquals("You have received an email to confirm your subscription. Your subscription will take effect after you have clicked on the link in the email.",txtConfirm.getText());

    }

}
