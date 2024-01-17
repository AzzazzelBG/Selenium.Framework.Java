package org.aleksdrinkov.pageobjects;

import org.aleksdrinkov.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(css = ".action__submit")
    WebElement submit;
    @FindBy(xpath = "//button[contains(@class,'ta-item')])[1]")
    WebElement selectBulgaria;
    By results = By.cssSelector(".ta-results");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName) {
        Actions action = new Actions(driver);
        action.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectBulgaria.click();
    }

    public void submitOrder() {
       submit.click();
    }
}
