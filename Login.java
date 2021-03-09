package src.test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.scene.control.RadioButton;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import src.main.java.SeleniumUtilities;
import src.main.java.TestBase;
import src.test.java.PageClasses.LoginPage;

import java.time.Duration;
import java.util.List;

public class Login {
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
   // @Test(priority=2)
    public void HomeFormFill() throws InterruptedException {
        Thread.sleep(3000);
        WebElement name=driver.findElement(By.xpath("//input[@id='name']"));
        util.waitExplicitely(name);
        name.sendKeys("Bindu");
        WebElement fatherName=driver.findElement(By.xpath("//input[@id='lname']"));
        util.waitExplicitely(fatherName);
        fatherName.sendKeys("S N");
        WebElement postAddress=driver.findElement(By.cssSelector("#postaladdress"));
        util.waitExplicitely(postAddress);
        postAddress.sendKeys("#123 shhvhdhdnds");

        //WebElement radiobutton=
                WebElement male=driver.findElement(By.xpath("//input[@value='male']"));
        util.waitExplicitely(male);
        if(male.isSelected())
        System.out.println("Male is selected");
        WebElement female=driver.findElement(By.xpath("//input[@value='female']"));
        util.waitExplicitely(female);
        female.click();
        System.out.println("Now Female is selected");
        WebElement city=driver.findElement(By.id("city"));
        util.selectByIndex(city,2);
        WebElement course=driver.findElement(By.id("course"));
        util.selectByVisibleTExt(course,"MBA");
        WebElement district=driver.findElement(By.id("district"));
        util.selectByValue(district,"mumbai");
        WebElement state=driver.findElement(By.id("state"));
        util.selectByVisibleTExt(state,"PATNS");WebElement pincode=driver.findElement(By.cssSelector("#pincode"));
        util.waitExplicitely(pincode);
        pincode.sendKeys("12345");
        WebElement emailId=driver.findElement(By.cssSelector("#emailid"));
        util.waitExplicitely(emailId);
        emailId.sendKeys("buuijij@gmail.com");

        Thread.sleep(3000);



    }
    //@Test(priority=3)
    public void SwitchTo() throws InterruptedException {
        WebElement SwitchTo=driver.findElement(By.xpath("//button[normalize-space()='Switch To']"));
        util.waitExplicitely(SwitchTo);
        util.mouseOver(SwitchTo);
        WebElement alert=driver.findElement(By.xpath("//a[normalize-space()='Alert']"));
        util.waitExplicitely(alert);
        util.mouseOver(alert);
        WebElement windowAlert=driver.findElement(By.xpath("(//button[contains(text(),'Window Alert')])[1]"));
        util.waitExplicitely(windowAlert);
        windowAlert.click();
        util.alertHandle();
       Thread.sleep(3000);
        WebElement promptAlert=driver.findElement(By.cssSelector("button[onclick='promtFunction()']"));
        util.waitExplicitely(promptAlert);
        promptAlert.click();
        util.alertHandle();
       WebElement ScrollTillWindow=driver.findElement(By.cssSelector("div[id='overflowTest'] div button[type='button']"));
        util.waitExplicitely(ScrollTillWindow);
        util.ScrollIntoView(ScrollTillWindow);
       ScrollTillWindow.click();
        util.alertHandle();
    }

    @Test(priority=4)
    public void intractions() throws InterruptedException {
        WebElement interactions=driver.findElement(By.xpath("//button[contains(text(),'Intractions')]"));
        util.waitExplicitely(interactions);
        util.mouseOver(interactions);
        WebElement dropAndDrag=driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop')]"));
        util.waitExplicitely(dropAndDrag);
        util.mouseOver(dropAndDrag);

        WebElement target1=driver.findElement(By.xpath("//div[@id='div1'][1]"));
        util.waitExplicitely(target1);
        WebElement source=driver.findElement(By.xpath("//img[@src='./assert/download.jpg']"));
        util.waitExplicitely(source);
        Actions actions1=new Actions(driver);
        actions1.clickAndHold(source).pause(Duration.ofSeconds(2)).moveToElement(target1).pause(Duration.ofSeconds(2)).release().build().perform();
        Thread.sleep(3000);
        //actions.dragAndDrop(source,target1).build().perform();//not working
        //Thread.sleep(3000);
        Actions actions2=new Actions(driver);
        WebElement target2=driver.findElement(By.xpath("//div[@id='div1'][2]"));
        util.waitExplicitely(target2);
        actions2.dragAndDrop(source,target2).build().perform();
        Thread.sleep(3000);

    }

   @AfterSuite
    public void quit()
   {
   //driver.close();
   }
}
