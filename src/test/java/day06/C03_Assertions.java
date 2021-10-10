package day06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C03_Assertions {

//1.Bir Class olusturalim YanlisEmailTesti
//2.http://automationpractice.com/index.php sayfasina gidelim
//3.Sign in butonuna basalim
//4.Email kutusuna @isareti olmayan bir mail yazip enter’a bastigimizda “Invalid email address” uyarisi ciktigini test edelim

    static WebDriver driver;

    @BeforeClass
    public static void setup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void InvalidEmailTestTest () {
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys("alican" + Keys.ENTER);
        Assert.assertTrue("Test for 'Invalid Email Address' is failed!" , driver.findElement(By.xpath("//*[text()='Invalid email address.']")).isDisplayed());
    }


    @AfterClass
    public static void tearDown () {
        driver.quit();
    }

}
