package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C02_Test01 {

//1-Test01 isimli bir class olusturun
//2- https://www.walmart.com/ adresine gidin3- Browseri tam sayfa yapin
//4-Sayfayi “refresh” yapin
//5- Sayfa basliginin “Save” ifadesi icerdigini control edin
//6-Sayfa basliginin “Walmart.com | Save Money.Live Better” a esit oldugunu control ediniz
//7- URL in walmart.com icerdigini control edin
//8-”Nutella” icin arama yapiniz
//9- Kac sonuc bulundugunu yaziniz
//10-Sayfayi kapatin

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://www.walmart.com/");
        //driver.navigate().refresh();

        String baslik = driver.getTitle();
        String expectedTitlePart = "Save";

        System.out.println("Baslik "+ expectedTitlePart  +" iceriyor mu? "+baslik.contains(expectedTitlePart));

        String expectedTitle = driver.getTitle();

        System.out.println("Baslik "+ expectedTitle  +" mi? " + baslik.equals(expectedTitle));

        String URL = driver.getCurrentUrl();
        String expectedURLPart = "walmart.com";

        System.out.println("URL "+ expectedURLPart  +" iceriyor mu? "+URL.contains(expectedURLPart));

        WebElement ara = driver.findElement(By.xpath("//input[@type='search']"));
        ara.sendKeys("Nutella" + Keys.ENTER);

        WebElement sonuc = driver.findElement(By.xpath("//div[@class='inline-flex mv3-xl mt3 ml3 ml4-xl mr1 db']"));

        System.out.println(sonuc.getText());

        driver.close();






    }
}
