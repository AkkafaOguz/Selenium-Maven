package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class C01_Maven {

//1- https://www.amazon.com/ sayfasina gidelim
//2- arama kutusunu locate edelim
//3- “Samsung headphones” ile arama yapalim
//4- Bulunan sonuc sayisini yazdiralim
//5- Ilk urunu tiklayalim
//6- Sayfadaki tum basliklari yazdiralim

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Class'in basinda implicitlyWait komutu yazilirsa bu test boyunca her locator icin driver maximum yazilan zaman kadar bekler.
        //Boylelikle her locator oncesi tek tek Thread.sleep() methodu kullanmak zorunda kalmayiz.
        //Locator girilen sureden daha hizli bulunur ise beklenilen sure kadar beklemeden surec islemeye devam eder.

        driver.get("https://www.amazon.com/");

        //<input type="text" id="twotabsearchtextbox" value="" name="field-keywords" autocomplete="off" placeholder="" class="nav-input nav-progressive-attribute" dir="auto" tabindex="0" aria-label="Search">
        WebElement aramaKutusu = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        aramaKutusu.sendKeys("Samsung headphones"+ Keys.ENTER);

        /*<div class="a-section a-spacing-small a-spacing-top-small">
                <span dir="auto">1-16 of 170 results for</span><span dir="auto"> </span><span class="a-color-state a-text-bold" dir="auto">"Samsung headphones"</span>
        </div>*/

        //WebElement bulunanElemanSayisi = driver.findElement(By.cssSelector(".a-section a-spacing-small a-spacing-top-small"));
        //By.className ile locate ettigimiz webele

        WebElement bulunanElemanSayisi = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        System.out.println(bulunanElemanSayisi.getText());

        //WebElement ilkUrun = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]"));
        //ilkUrun.click();
        //Sadece click() yapacaksak WebElement olusturmaya ve assign etmeye gerek yok. Direkt locate edip sonra click() yapabiliriz.
        driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();

        driver.navigate().back();

        List <WebElement> urunleriListele = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
        urunleriListele.stream().forEach(t-> System.out.println(t.getText().substring(0,20)));

        driver.close();

    }
}
