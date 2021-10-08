package day04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C03_Test03 {

//1. “https://www.saucedemo.com” Adresine gidin
//2. Username kutusuna “standard_user” yazdirin
//3. Password kutusuna “secret_sauce” yazdirin
//4. Login tusuna basin
//5. Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
//6. Add to Cart butonuna basin
//7. Alisveris sepetine tiklayin
//8. Sectiginiz urunun basarili olarak sepete eklendigini control edin
//9. Sayfayi kapatin

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://www.saucedemo.com");

        driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".submit-button")).click();

        WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])[1]"));

        String firstElementText = firstProduct.getText();

        firstProduct.click();

        //Bir elementin uzerinde birden fazla islem gerceklestirecegimi bildigim icin bir container'a atadim ve boylelikle dublication yapmadim.

        driver.findElement(By.xpath("//*[@data-test='add-to-cart-sauce-labs-backpack']")).click();

        WebElement shoppingCart = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        shoppingCart.click();

        String theProductAtInventory = driver.findElement(By.cssSelector(".inventory_item_name")).getText();

        if (driver.findElement(By.cssSelector(".cart_quantity")).isEnabled()) {

            //Sepetteki urunun sepete gonderdigim urun ile ayni olup olmadigini kotnrol etmeden once, sepete urunu gonderebilmis miyim diye kotrol etmem lazim.

            if (theProductAtInventory.equalsIgnoreCase(firstElementText)) {
                System.out.println("The test for 'ADD TO CART' is pass!");

            } else {
                System.out.println("The test for 'ADD TO CART' is failed!");
                System.out.println("The product in the shopping cart is: " + theProductAtInventory);
            }
        } else {

            System.out.println("The test for 'ADD TO CART' is failed!");
            System.out.println("Product can not be sent to shopping cart!");

        }

        driver.close();
    }
}
