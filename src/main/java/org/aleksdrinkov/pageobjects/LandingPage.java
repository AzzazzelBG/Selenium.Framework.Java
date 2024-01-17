package org.aleksdrinkov.pageobjects;

import org.aleksdrinkov.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(xpath="//input[@formcontrolname='userPassword']")
    WebElement passwordEle;
    @FindBy(css="#login")
    WebElement submit;
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage() {

        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
    }
}
