package Tests;

import Pages.AllOrdersPage;
import Pages.LoginPage;
import Utils.Driver;
import Utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class allOrdersTest extends TestBase {

    @Test(groups = {"smoke"})
    public void verifyCheckAllButton() throws IOException {

        driver.get(PropertyReader.getTheProperties("url"));
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(PropertyReader.getTheProperties("username"), Keys.TAB, "test", Keys.ENTER);

        driver.findElement(By.id("ctl00_MainContent_btnCheckAll")).click();
        List<WebElement> checkboxes = Driver.getDriver().findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

    }

    @Test (groups = {"smoke"})
    public void verifyCheckAllButtonUsingPageObjectModel() throws IOException {

        driver.get(PropertyReader.getTheProperties("url"));

        //login page
        LoginPage loginPage = new LoginPage();

        loginPage.username.sendKeys(PropertyReader.getTheProperties("username"));
        loginPage.password.sendKeys(PropertyReader.getTheProperties("password"));
        loginPage.loginButton.click();

        // all orders page
        AllOrdersPage allOrdersPage = new AllOrdersPage();

        allOrdersPage.checkAllButton.click();

        for (WebElement checkbox : allOrdersPage.checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

    }

    @Test
    public void verifyUnCheckAllButton()  {

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester", Keys.TAB, "test", Keys.ENTER);

        driver.findElement(By.id("ctl00_MainContent_btnCheckAll")).click();
        driver.findElement(By.id("ctl00_MainContent_btnUncheckAll")).click();

        List<WebElement> checkboxes = Driver.getDriver().findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

    }

    @Test
    public void verifyDeleteSelectedButton() throws InterruptedException {

        driver.get(PropertyReader.getTheProperties("url"));

        new LoginPage().loginWithValidCredentials();

        AllOrdersPage allOrdersPage = new AllOrdersPage();

        allOrdersPage.checkAllCheckboxes();
        allOrdersPage.deleteSelectedButton.click();

        Assert.assertTrue(driver.getPageSource().contains("List of orders is empty. In order to add new order use"));

    }

}
