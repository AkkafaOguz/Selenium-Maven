package day05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Odev {

//1-Test01 isimli bir class olusturun
//2- https://www.amazon.com/ adresine gidin
//3- Browseri tam sayfa yapin
//4-Sayfayi “refresh” yapin
//5- Sayfa basliginin “Amazon” ifadesi icerdigini control edin
//6-Sayfa basliginin “Amazon.com. Spend less. Smile more.” a esit oldugunu control ediniz
//7- URL in amazon.com icerdigini control edin
//8-”Nutella” icin arama yapiniz
//9- Kac sonuc bulundugunu yaziniz
//10-Sayfayi kapatin

    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test01() {

        driver.get("https://www.amazon.com/");
        driver.navigate().refresh();

    }

    //--> @Test'ler kendi aralarinda random olarak calistiklari icin titleTest() method'u test01() methodundan once calisiyor. Bu yuzden 51. satirda getTitle() methodu hiclik donduruyor.
    //--> Bunun onune gecmek icin en sagliklisi hepsini ayni method icerisinde yazmak. (Fakat boyle birakiyorum ki hatami daha sonra gorebileyim)

    @Test
    public void titleTest() {

        String expectedTitlePart = "Amazon";
        String actualTitle = driver.getTitle();

        if (actualTitle.contains(expectedTitlePart)) {
            System.out.println("Test for 'Expected Title Part' is pass!");
        } else {
            System.out.println("Test for 'Expected Title Part' is failed!");
            System.out.println("The actual title is: " + actualTitle);
        }

        String expectedTitle = "Amazon.com. Spend less. Smile more.";

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Test for 'Ecpected Title' is pass!");
        } else {
            System.out.println("Test for 'Ecpected Title' is failed!");
            System.out.println("The actual title is: " + actualTitle);
        }

    }

    @Test
    public void urlTest() {

        String expectedURLPart = "amazon.com";
        String actualURL = driver.getCurrentUrl();

        if (actualURL.contains(expectedURLPart)) {
            System.out.println("Test for 'Expected URL Part' is pass!");
        } else {
            System.out.println("Test for 'Expected URL Part' is failed!");
            System.out.println("The actual URL is: " + actualURL);
        }
    }

    @Test
    public void searchTest() {

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella" + Keys.ENTER);
        String numberOfResults = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']")).getText();
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
