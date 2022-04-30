package Pages;

import Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllOrdersPage {

    public AllOrdersPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAllButton;

    @FindBy(xpath = "//input[@type='checkbox']")
    public List<WebElement> checkboxes;

    @FindBy(linkText = "View all orders")
    public WebElement allOrdersLink;

    @FindBy(partialLinkText = "all products")
    public WebElement allProductsLink;

    @FindBy(linkText = "Order")
    public WebElement singleOrderLink;

    @FindBy(name = "ctl00$MainContent$btnDelete")
    public WebElement deleteSelectedButton;


    public void checkAllCheckboxes(){
        for (WebElement checkbox : this.checkboxes) {
            if(!checkbox.isSelected())
                checkbox.click();
        }
    }
//nice job

}
