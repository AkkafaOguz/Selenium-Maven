package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C03_Test02 {

//1. http://zero.webappsecurity.com sayfasina gidin
//2. Signin buttonuna tiklayin
//3. Login alanine  “username” yazdirin
//4. Password alanine “password” yazdirin
//5. Sign in buttonuna tiklayin
//6. Pay Bills sayfasina gidin
//7. amount kismina yatirmak istediginiz herhangi bir miktari yazin
//8. tarih kismina “2020-09-10” yazdirin
//9. Pay buttonuna tiklayin
//10. “The payment was successfully submitted.” mesajinin ciktigini control edin


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://zero.webappsecurity.com");

        //<button id="signin_button" type="button" class="signin btn btn-info"> <i class="icon-signin"></i>Signi </button>

        driver.findElement(By.cssSelector("#signin_button")).click();

        driver.findElement(By.cssSelector("#user_login")).sendKeys("username");

        driver.findElement(By.cssSelector("#user_password")).sendKeys("password");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // Burada guvenlik hatasi verdigi icin iki secenegimiz var.(Bu secenekler yalnizca bu site uzerinde gecerli)
        // 1.Yol: navigate().back() ile geriye donmek ki bu giris islemimizi sifirlamiyor.
        // 2.Yol: Sunulan guvenlik seceneginden 'gelismis'e tiklayip siteye devam et secenegine tiklamak.
        // Ikinci yol daha komplike oldugu icin o yolu tercih ediyorum.

        driver.findElement(By.cssSelector("#details-button")).click();

        driver.findElement(By.linkText("zero.webappsecurity.com sitesine ilerle (güvenli değil)")).click();

        driver.findElement(By.xpath("//a[@href='/bank/redirect.html?url=pay-bills.html']")).click();

        //Burada linkText uzerinden de locator olusturabilirdim.
        //Fakat sonrasinda sitede guncelleme yapilma ihtimalinde linkText'in degistirilmesi ihtimalinin link'in kendisinin degistirilmesinden daha yuksek oldugunu dusundugumden bu yolu tercih ettim.

        driver.findElement(By.cssSelector("#sp_amount")).sendKeys("12000");

        driver.findElement(By.cssSelector("#sp_date")).sendKeys("2020-09-10");

        driver.findElement(By.cssSelector(".btn-primary")).click();

        //Burada className uzerinden locator bulmak istedim ve html kodunda class name olarak atanan "btn btn-primary"i girdim.
        //Lakin bu className icerisinde bosluk barindirdigi icin kod calismadi. Sonrasinda className'in bosluk sonrasi kisminin unique olup olmadigini kontrol ettim ve kullandim.

        String confirmationMessage = driver.findElement(By.cssSelector("#alert_content")).getText();
        String expectedConfirmationMessage = "The payment was successfully submitted.";

        if (confirmationMessage.equalsIgnoreCase(expectedConfirmationMessage)) {
            System.out.println("Test for '" + expectedConfirmationMessage + "' is pass!");
        } else {
            System.out.println("Test for " + expectedConfirmationMessage + " is failed!");
            System.out.println("The actual message is: " + confirmationMessage);
        }

        driver.close();

    }

}
