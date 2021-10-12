package yusufHocaSoru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class project01 {

// Navigate to "https://react-shopping-cart-67954.firebaseapp.com/"
// List all the elements on the website
// Create a String Arraylist and put Product Names into the ArrayList
// Choose 5 items randomly, add to cart and print the names of items
// (Each product cannot be added more then 1 time!)

// CHECK THE PRICES!
// Sum each item's price and compare your result with the website
// If results are matching print success message
// Get string price values from Website, convert them to double and add to <Double> ArrayList and print the price list
// Click Checkout


    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
    }

    @Test
    public void test() throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.className("shelf-item__title"));
        ArrayList<String> elementsInList = new ArrayList<>();
        elements.stream().forEach(t -> elementsInList.add(t.getText()));

        List<String> randomlySelectedItems = new ArrayList<>();

        while (randomlySelectedItems.size() != 5) {

            int randomNumber = (int) (Math.random() * (elementsInList.size()));

            if (!randomlySelectedItems.contains(elementsInList.get(randomNumber))) {
                randomlySelectedItems.add(elementsInList.get(randomNumber));
                String item = "//p[text()='" + randomlySelectedItems.get(randomlySelectedItems.size() - 1) + "']";
                driver.findElement(By.xpath(item)).click();
                Thread.sleep(250);
                driver.findElement(By.xpath("//div[@class='float-cart__close-btn']")).click();
            }
        }
        Thread.sleep(250);
        driver.findElement(By.xpath("//span[@class='bag bag--float-cart-closed']")).click();
        randomlySelectedItems.stream().forEach(t -> System.out.println("-> " + t));

        double totalAmount = 0;
        ArrayList<Double> totalAmountList = new ArrayList<>();

        for (int i = 1; i < (randomlySelectedItems.size() + 1); i++) {

            Thread.sleep(250);
            String elementAmount = "(//div[@class='shelf-item__price']/p)[" + i + "]";
            String amount = driver.findElement(By.xpath(elementAmount)).getText();
            amount = amount.replace("$ ", "");
            double money = Double.parseDouble(amount);
            totalAmountList.add(money);
            totalAmount += money;
        }

        DecimalFormat df = new DecimalFormat(".00");
        String strDouble = df.format(totalAmount);
        strDouble = strDouble.replace(",",".");


        if ((driver.findElement(By.className("sub-price__val")).getText().replace("$ ", "")).equals(strDouble)) {

            System.out.println("Test for 'Total Amount' is passed!");
        } else {
            System.out.println("Test for 'Total Amount' is failed!");
        }
        totalAmountList.stream().forEach(t -> System.out.println("$ " + t));

        System.out.println("Total amount is: " + strDouble);
        driver.findElement(By.className("buy-btn")).click();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
