package org.aleksdrinkov.testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aleksdrinkov.pageobjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException {

        // If you want to use several browserst use these properties
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(new File("E:\\Programming\\Selenium.Framework.Java\\src\\main\\java\\org\\aleksdrinkov\\resources\\globaldata.properties"));
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        String jsonContent = FileUtils.readFileToString(new File(filePath));
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {

        WebDriver driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();

        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
