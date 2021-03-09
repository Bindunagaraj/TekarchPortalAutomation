package src.main.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

public class CommonUtilities {
    WebDriver driver = null;

    @BeforeSuite
    public void initialize() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-tekarch.firebaseapp.com/");
        driver.manage().window().maximize();

    }
}
