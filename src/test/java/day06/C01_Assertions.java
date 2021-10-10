package day06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C01_Assertions {

//1) Bir class oluşturun: BestBuyAssertions
//2) https://www.bestbuy.com/ Adresine gidin farkli test method’lari olusturarak asagidaki testleri yapin
//○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
//○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
//○ logoTest => BestBuy logosunun görüntülendigini test edin
//○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin

    static WebDriver driver;

    @BeforeClass
    public static void setup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.bestbuy.com/");
    }

    @Test
    public void urlTest(){

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.bestbuy.com/";
        Assert.assertEquals("Test for 'URL' is failed!",expectedURL,actualURL);
    }

    @Test
    public void titleTest(){

        String actualTitle = driver.getTitle();
        String unexpectedTitlePart = "Rest";
        Assert.assertFalse("Test for 'Expected Title Part' is failed!",actualTitle.contains(unexpectedTitlePart));
    }

    @Test
    public void logoTest(){

        Assert.assertTrue("Test for 'Logo' is failed!",driver.findElement(By.xpath("(//img[@class='logo'])[1]")).isDisplayed());
    }

    @Test
    public void frenchLinkTest(){

        Assert.assertTrue("Test for 'French Link' is failed!",driver.findElement(By.xpath("//button[@lang='fr']")).isDisplayed());
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
