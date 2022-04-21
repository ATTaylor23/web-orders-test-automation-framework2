package Tests;

import Pages.AllOrdersPage;
import Pages.AllProductsPage;
import Pages.LoginPage;
import Utils.Driver;
import Utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class allProductsTest extends TestBase {

    @Test
    public void verifyColumnNames() throws IOException {

        driver.get(PropertyReader.getTheProperties("url"));
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(PropertyReader.getTheProperties("username"), Keys.TAB, PropertyReader.getTheProperties("password"), Keys.ENTER);

        driver.findElement(By.linkText("View all products")).click();

        List<WebElement> columns = Driver.getDriver().findElements(By.xpath("//table[@class='ProductsTable']//tr[1]/th"));

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(columns.get(0).getText(), "Product name");
        softAssert.assertEquals(columns.get(1).getText(), "Price");
        softAssert.assertEquals(columns.get(2).getText(), "Discount");

        softAssert.assertAll();
        // used to keep executing and find out the error IF one of them fails


    }

    @Test
    public void verifyProductNames()  {

        driver.get(PropertyReader.getTheProperties("url"));

        LoginPage loginPage = new LoginPage();

        loginPage.username.sendKeys(PropertyReader.getTheProperties("username"));
        loginPage.password.sendKeys(PropertyReader.getTheProperties("password"));
        loginPage.loginButton.click();


        AllOrdersPage allOrdersPage = new AllOrdersPage();

        allOrdersPage.allProductsLink.click();

        AllProductsPage allProductsPage = new AllProductsPage();

        Assert.assertEquals(allProductsPage.productNamesList.get(0).getText(), "MyMoney");
        Assert.assertEquals(allProductsPage.productNamesList.get(1).getText(), "FamilyAlbum");
        Assert.assertEquals(allProductsPage.productNamesList.get(2).getText(), "ScreenSaver");

    }

}
