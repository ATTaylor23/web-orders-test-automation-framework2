package Pages;

import Utils.Driver;
import Utils.PropertyReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Instance Variables
    @FindBy(id = "ctl00_MainContent_username")
    public WebElement username;

    @FindBy (name = "ctl00$MainContent$password")
    public WebElement password;

    @FindBy (xpath = "//input[@value='Login']")
    public WebElement loginButton;


    // Instance Methods
    public void loginWithValidCredentials(){

        username.sendKeys(PropertyReader.getTheProperties("username"));
        this.password.sendKeys(PropertyReader.getTheProperties("password"));
        loginButton.click();

    }

}
