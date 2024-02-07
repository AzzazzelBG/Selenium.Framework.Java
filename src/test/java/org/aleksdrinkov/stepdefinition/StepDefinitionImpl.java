package org.aleksdrinkov.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.aleksdrinkov.pageobjects.*;
import org.aleksdrinkov.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCataloguePage productCataloguePage;
    public CheckoutPage checkoutPage;

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

    @And("^Checkout (.+) and submit the order$")
    public void checkoutProductNameAndSubmitTheOrder(String productName) {
        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        cartPage.goToCheckout();

        checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("Bulgaria");
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string) {
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        OrderPage orderPage = productCataloguePage.goToOrdersPage();

        Assert.assertTrue(orderPage.verifyOrderDisplay(string));
    }
}
