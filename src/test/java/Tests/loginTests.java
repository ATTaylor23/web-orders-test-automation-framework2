package Tests;

import Pages.LoginPage;
import Utils.Driver;
import Utils.PropertyReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class loginTests extends TestBase {

    @Test
    public void positiveLoginTest(){

        logger.info("Navigate to URL");
        driver.get(PropertyReader.getTheProperties("url"));

        logger.info("Enter username");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(PropertyReader.getTheProperties("username"));

        logger.info(("Enter password"));
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(PropertyReader.getTheProperties("password"));

        logger.info("Click login");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();


    }

    @Test(groups = {"smoke"})
    public void positiveLoginTestUsingPageObjectModel() {


        logger.info("Navigate to URL");
        driver.get(PropertyReader.getTheProperties("url"));

        LoginPage loginPage = new LoginPage();

        logger.info("Enter username");
        loginPage.username.sendKeys(PropertyReader.getTheProperties("username"));

        logger.info("Enter password");
        loginPage.password.sendKeys(PropertyReader.getTheProperties("password"));

        logger.info("Click login");
        loginPage.loginButton.click();

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");



    }

    @Test (groups = {"smoke"})
    public void negativeLoginTestUsingPageObjectModel() {

        logger = report.createTest("negativeLoginTestUsingPageObjectModel");

        driver.get(PropertyReader.getTheProperties("url"));

        LoginPage loginPage = new LoginPage();


        loginPage.username.sendKeys(PropertyReader.getTheProperties("username"));

        loginPage.password.sendKeys(PropertyReader.getTheProperties("password"));

        loginPage.loginButton.click();

        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");

    }

}
