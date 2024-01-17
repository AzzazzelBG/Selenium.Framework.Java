package org.aleksdrinkov.tests;

import org.aleksdrinkov.pageobjects.CartPage;
import org.aleksdrinkov.pageobjects.CheckoutPage;
import org.aleksdrinkov.pageobjects.LandingPage;
import org.aleksdrinkov.pageobjects.ProductCataloguePage;
import org.aleksdrinkov.testcomponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StandAloneTestRefactored extends BaseTest {

    @Test
    public void submitOrder() throws IOException {

        String productName = "ADIDAS ORIGINAL";

        LandingPage landingPage = launchApplication();
        landingPage.loginApplication("tozi@abv.bg", "ToziOnzi123");

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        productCataloguePage.goToCartPage();

        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        cartPage.goToCheckout();

        driver.findElement(By.cssSelector(".totalRow button")).click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("Bulgaria");
//        driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

    }
}
