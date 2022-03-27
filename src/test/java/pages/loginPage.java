package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


public class loginPage {
    public static WebDriver driver;

    public loginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath="//input[@id='emailAddress']")
    public WebElement userEmail;

    @FindBy (xpath="//input[@id='password']")
    public WebElement userPassword;

    @FindBy (xpath="//h6[contains(text(),'Sign In')]")
    public WebElement signInButton;



}
