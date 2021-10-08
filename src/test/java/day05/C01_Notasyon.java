package day05;

import org.junit.Test;

public class C01_Notasyon {

    // Java'daki gibi Selenium'da da method'lar olusturabiliriz.
    // Ancak bu method'lar call edilmedikce calismazlar.



    //JUnit ile notasyon kullanarak siradan method'lari test method'una donusturabiliriz.
    // Bir test method'u bagimsiz olarak calisabilen test yapmamizi saglar.
    // @Test notasyonu sayesinde main method()'a mecburiyet ortadan kalkiyor.

    public void ilkTestMethodu(){
        System.out.println("Ilk test methodumuz");
    }

    @Test
    public void notasyonliIlkTestMethodu(){
        System.out.println("Notasyonlu ilk test methodumuz");
    }
}
