package org.aleksdrinkov.tests;

import org.aleksdrinkov.pageobjects.CartPage;
import org.aleksdrinkov.pageobjects.CheckoutPage;
import org.aleksdrinkov.pageobjects.OrderPage;
import org.aleksdrinkov.pageobjects.ProductCataloguePage;
import org.aleksdrinkov.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StandAloneTestRefactored extends BaseTest {

    String productName = "ADIDAS ORIGINAL";

    @Test
    public void submitOrder() throws IOException {

        landingPage.loginApplication("tozi@abv.bg", "ToziOnzi123");

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        productCataloguePage.goToCartPage();

        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        cartPage.goToCheckout();

//        driver.findElement(By.cssSelector(".totalRow button")).click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("Bulgaria");
//        driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        OrderPage orderPage = productCataloguePage.goToOrdersPage();

        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }
}
