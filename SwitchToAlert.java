package src.main.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.nashorn.internal.ir.SwitchNode;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SwitchToAlert {
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
    @BeforeMethod
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
    public void alert() throws InterruptedException {
        Actions actions=new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[normalize-space()='Switch To']"))).build().perform();
        WebElement al=driver.findElement(By.xpath("//a[normalize-space()='Alert']"));
        actions.moveToElement(al).click().build().perform();
        WebElement windowAlert=driver.findElement(By.xpath("(//button[normalize-space()='Window Alert'])[1]"));
        util.waitExplicitely(windowAlert);
        actions.moveToElement(windowAlert).click().build().perform();
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
        WebElement promptAlert=driver.findElement(By.xpath("//button[normalize-space()='Promt Alert']"));
        util.waitExplicitely(promptAlert);
        actions.moveToElement(promptAlert).click().build().perform();
        Alert al1= driver.switchTo().alert();
        al1.sendKeys("Bindu");
        al1.accept();
        WebElement scrollIntoView=driver.findElement(By.xpath("//div[@id='overflowTest']//div//button[@type='button'][normalize-space()='Window Alert']"));
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollIntoView);
        actions.moveToElement(scrollIntoView).click().build().perform();
        Thread.sleep(3000);
        Alert al2=driver.switchTo().alert();
        String message=al2.getText();
        al2.accept();
        System.out.println(message);
        Thread.sleep(3000);
    }
    @AfterMethod
    public void quit()
    {
        driver.quit();
    }
}
