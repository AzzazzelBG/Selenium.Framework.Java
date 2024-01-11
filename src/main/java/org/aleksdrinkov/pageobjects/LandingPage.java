package org.aleksdrinkov.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(xpath="//input[@formcontrolname='userPassword']")
    WebElement password;
    @FindBy(css="#login")
    WebElement submit;
}
