package day05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C02_BeforeNotasyonu {

    // Amazon sayfasina gidip 3 farkli test methodu hazirlayin
    // Her mothod'da farkli aramalar yapalim

    static WebDriver driver;
    WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
    @BeforeClass
    public static void setup (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com");
    }
    // Method'dan yahut class'dan once calistiklari icin class'in calismasi icin gerekli ayarlamalari saglarlar.
    // Bu yuzde isimleri genel olarak setup olur.

    @Test
    public void test01(){
        searchBox.clear();
        searchBox.sendKeys("Nutella" + Keys.ENTER);

    }

    @Test
    public void test02(){
        searchBox.clear();
        searchBox.sendKeys("bycicle" + Keys.ENTER);

    }

    @Test
    public void test03(){
        searchBox.clear();
        searchBox.sendKeys("java" + Keys.ENTER);

    }

    @AfterClass
    public static void tearDown(){

        driver.quit();
    }
}
