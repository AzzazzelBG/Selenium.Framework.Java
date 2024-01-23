package org.aleksdrinkov.tests;

import org.aleksdrinkov.pageobjects.CartPage;
import org.aleksdrinkov.pageobjects.CheckoutPage;
import org.aleksdrinkov.pageobjects.OrderPage;
import org.aleksdrinkov.pageobjects.ProductCataloguePage;
import org.aleksdrinkov.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandAloneTestRefactored extends BaseTest {

//    String productName = "ADIDAS ORIGINAL";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException {

        landingPage.loginApplication(input.get("email"), input.get("password"));

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(input.get("productName"));
        productCataloguePage.goToCartPage();

        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        cartPage.goToCheckout();

//        driver.findElement(By.cssSelector(".totalRow button")).click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.selectCountry("Bulgaria");
//        driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

    }

    @Test(dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
    public void orderHistoryTest(String productName) {

        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        OrderPage orderPage = productCataloguePage.goToOrdersPage();

        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\org\\aleksdrinkov\\data\\PurchaseOrder.json");

        return new Object [][] {
                {data.get(0)},
                {data.get(1)}
        };
    }
}
