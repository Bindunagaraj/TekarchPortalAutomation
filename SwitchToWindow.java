package src.main.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwitchToWindow {
    WebDriver driver = null;
    SeleniumUtilities util=null;

    @BeforeSuite
    public void initialize() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        util=new SeleniumUtilities(driver);
        driver.get("https://qa-tekarch.firebaseapp.com/");
        driver.manage().window().maximize();

    }
    @BeforeClass
    public void login() throws InterruptedException {
        WebElement Username=driver.findElement(By.xpath("//input[@id='email_field']"));
        Username.sendKeys("admin123@gmail.com");
        WebElement Password=driver.findElement(By.cssSelector("#password_field"));
        Password.sendKeys("admin123");
        WebElement loginButton=driver.findElement(By.cssSelector("button[onclick='login()']"));
        util.waitExplicitely(loginButton);
        loginButton.click();
        Thread.sleep(3000);
    }
    @Test
    public void SwitcTabs() throws InterruptedException {
        Actions actions=new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[normalize-space()='Switch To']"))).build().perform();
        WebElement al=driver.findElement(By.xpath("//a[normalize-space()='Windows']"));
        util.waitExplicitely(al);
        actions.moveToElement(al).click().build().perform();
        WebElement windows=driver.findElement(By.xpath("(//button[@onclick='myFunction()'])[1]"));
        util.waitExplicitely(windows);
        actions.moveToElement(windows).click().build().perform();
        ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println("in tab 1 total tabs are "+tabs.size());
        Thread.sleep(3000);
        driver.close();
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(3000);

    }

    @Test(priority = 2)
    public void SwitchToWindows() throws InterruptedException {
        Actions actions=new Actions(driver);
        WebElement windows=driver.findElement(By.xpath("(//button[@onclick='myFunction()'])[2]"));
        util.waitExplicitely(windows);
        actions.moveToElement(windows).click().build().perform();
        ArrayList<String> window1=new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(window1.get(1));
        System.out.println("in window 1 total windows are "+window1.size());
        Thread.sleep(3000);
        driver.switchTo().window(window1.get(0));

    }
    @AfterSuite
    public void quit()
    {
        driver.quit();
    }
}
