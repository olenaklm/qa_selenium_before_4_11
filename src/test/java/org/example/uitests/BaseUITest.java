package org.example.uitests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class BaseUITest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void start() {
        //System.setProperty("webdriver.gecko.driver", new File("driver/geckodriver.exe").getAbsolutePath());

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        driver.get("https://www.google.com/?hl=en-US");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Accept all']"))).click();

        WebElement webElement = driver.findElement(By.name("q"));
        webElement.sendKeys("webdriver");
        driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']/center/input[@name='btnK']")).click();

        Assert.assertEquals(driver.getTitle(), "webdriver - Google Search");
    }

    @AfterTest
    public void stop() {
        driver.quit();
    }
}
