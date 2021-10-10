package day06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C02_Assertions {

//1) Bir class oluşturun: YoutubeAssertions
//2) https://www.youtube.com adresine gidin
//3) Aşağıdaki adları kullanarak 3 test metodu oluşturun ve gerekli testleri yapin
//○ titleTest  => Sayfa başlığının “YouTube” oldugunu test edin
//○ imageTest  => YouTube resminin görüntülendiğini (isDisplayed()) test edin
//○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
//○ wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin

    //ocate alirken cok sayida attribute value varsa eklenti kullanmak yerine  ikili xpath alarak secenekleri bir e indirebilirsnz
    //Birden fazla attribute yazabiliriz
    ////tag[@attribute1='value 1' and attribute2=//tag[@attribute1='value 1' and attribute2='value2']
    //Example: //div[@id='logo' or class='flex col logo' ]
    //sayfadaki asagida bir urune ulasmak icin actions class gorunce Keys.PAGE_DOWN ile yapabilirsnz
    ////input[@id = 'search' && name='search_query']


    static WebDriver driver;

    @BeforeClass
    public static void setup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com");
    }

    @Test
    public void titleTest () {

        String actualTitle = driver.getTitle();
        String expectedTitle = "Youtube";

        Assert.assertEquals("Test for 'Expected Title' is failed!",expectedTitle,actualTitle);
    }

    @Test
    public void imageTest () {

        Assert.assertTrue("Test for 'Image' is failed!",driver.findElement(By.xpath("//a[@class='yt-simple-endpoint style-scope ytd-topbar-logo-renderer']")).isDisplayed());
    }

    @Test
    public void searchBoxTest () {


        Assert.assertTrue("Test for 'Search Box' is failed!",driver.findElement(By.className("ytd-searchbox-spt")).isDisplayed());
    }

    @Test
    public void wrongTitleTest () {

        String unExpectedTitle = "youtube";
        String actualTitle = driver.getTitle();
        Assert.assertNotEquals("Test for 'Wrong Title' is failed!",actualTitle.equals(unExpectedTitle));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
