package ua.org.autotest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class AutoTest {

    private static WebDriver driver;

    @BeforeClass
    public static void Enter() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com");
    }

    @Test
    public void validLogIn() {
        WebElement loginField = driver.findElement(By.cssSelector("#identifierId"));
        loginField.sendKeys("malaktest01");
        WebElement nextButton1 = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span"));
        nextButton1.click();
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("TestTest");
        WebElement nextButton2 = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span"));
        nextButton2.click();

        WebElement verifyButton = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[2]/" +
                "c-wiz/div/div/div[5]/div[2]/c-wiz/div/div[1]/div/div[2]"));
        String verifyString = verifyButton.getText();
        Assert.assertEquals("Добро пожаловать, Андрей Малахов!", verifyString);
        WebElement button1 = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a/span"));
        button1.click();
        WebElement button2 = driver.findElement(By.xpath("//*[@id=\"gb_71\"]"));
        button2.click();
    }

    @Test
    public void invalidLogIn() {
        WebElement button1 = driver.findElement(By.xpath("//*[@id=\"view_container\"]/form/div[2]/div/div/div/ul[1]/li[1]/div/div[2]/p[1]"));
        button1.click();
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        passwordField.sendKeys("something");
        WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span"));
        nextButton.click();
        WebElement invalidButton = driver.findElement(By.xpath("//*[@id=\"forgotPassword\"]/content/span"));
        String verifyString = invalidButton.getText();
        Assert.assertEquals("Забыли пароль?", verifyString);
    }
}
