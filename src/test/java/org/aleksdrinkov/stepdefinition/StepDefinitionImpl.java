package org.aleksdrinkov.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.aleksdrinkov.pageobjects.LandingPage;
import org.aleksdrinkov.pageobjects.ProductCataloguePage;
import org.aleksdrinkov.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCataloguePage productCataloguePage;

    @Given("I landed on E-commerce page")
    public void I_landed_on_E_commerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) from Cart$")
    public void i_add_product_from_cart(String productName) {
        productCataloguePage = new ProductCataloguePage(driver);
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
    }
}
