package src.main.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.swing.*;

public class RightClickAndDoubleClick {
    WebDriver driver = null;
    SeleniumUtilities util=null;

    @BeforeSuite
    public void initialize() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        util=new SeleniumUtilities(driver);
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        driver.manage().window().maximize();

    }
    @Test
    public void RightClick() throws InterruptedException {
        WebElement rightClick=driver.findElement(By.xpath("//span[text()='right click me']"));
        util.waitExplicitely(rightClick);
        Actions actions=new Actions(driver);
        actions.contextClick(rightClick).perform();//comtext click for right click
        WebElement copy=driver.findElement(By.xpath("//span[text()='Copy']"));
        copy.click();
       Alert al= driver.switchTo().alert();
       al.accept();
       Thread.sleep(3000);

    }
    @Test
    public void doubleClick() throws InterruptedException {
        WebElement doubleClick=driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
        util.waitExplicitely(doubleClick);
        Actions action=new Actions(driver);
        action.doubleClick(doubleClick).click().perform();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(3000);

    }

    @AfterSuite
    public void quit(){
        driver.quit();
    }

}
