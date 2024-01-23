package org.aleksdrinkov.tests;

import org.aleksdrinkov.pageobjects.CartPage;
import org.aleksdrinkov.pageobjects.ProductCataloguePage;
import org.aleksdrinkov.testcomponents.BaseTest;
import org.aleksdrinkov.testcomponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    //add attribute to retry a flaky test
    @Test(retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException {

        String productName = "ADIDAS ORIGINAL";

        landingPage.loginApplication("tozi1@abv.bg", "ToziOnzi123");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws IOException {

        String productName = "ADIDAS ORIGINAL";

        landingPage.loginApplication("tozi@abv.bg", "ToziOnzi123");

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        productCataloguePage.goToCartPage();

        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.verifyProductDisplay("ADIDAS NON-ORIGINAL");
        Assert.assertTrue(match);


    }
}
