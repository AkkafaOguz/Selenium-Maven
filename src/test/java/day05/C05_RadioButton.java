package day05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class C05_RadioButton {

//1. Bir class oluşturun : RadioButtonTest
//2. Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın.
//-https://www.facebook.com adresine gidin
//-“Create an Account” button’una basin
//-“radio buttons” elementlerini locate edin
//-Secili degilse cinsiyet butonundan size uygun olani secin


    WebDriver driver;

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void test01() {
        driver.get("https://www.facebook.com");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        WebElement gender = driver.findElement(By.xpath("(//span[@class='_5k_2 _5dba'])[2]"));
        if (!gender.isSelected()) {
            gender.click();

        }
    }

    @After
    public void tearDown () {
        driver.quit();
    }
}
