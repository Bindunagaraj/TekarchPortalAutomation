package src.main.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
/*There r 3 different ways
1.using actions class dragAndDrop method
2.Using actions class Clickand hold , moveToelemet,Release methods
3.Using Robot class Keybord actions

*/



public class DragAndDrop {
    WebDriver driver = null;
    SeleniumUtilities util=null;

    @BeforeSuite
    public void initialize() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        util=new SeleniumUtilities(driver);
        driver.get("https://jqueryui.com/draggable/");
        driver.manage().window().maximize();

    }
 //   @Test
    public void dragAndDrop()
    {
//drag from souce and drop to destination
        WebElement frame=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(frame);
        WebElement source=driver.findElement(By.xpath("//div[@id='draggable']"));
        util.waitExplicitely(source);
        WebElement target=driver.findElement(By.xpath("//div[@id='droppable']"));
        util.waitExplicitely(target);
        Actions actions=new Actions(driver);
        //1.actions.clickAndHold(source).moveToElement(target).release().build().perform();//if u get any error here add pause

       //2. actions.clickAndHold(source).pause(Duration.ofSeconds(2)).moveToElement(target).pause(Duration.ofSeconds(2)).release().build().perform();
        actions.dragAndDrop(source,target).build().perform();//3
        driver.switchTo().defaultContent();
    }
    @Test
    public void draggable() throws InterruptedException {
        //just drag from source but u can drop drop anywhere in a page on x and y axis using dragAndDropBy() method
        WebElement frame=driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(frame);
        WebElement Draggable=driver.findElement(By.xpath("//div[@id='draggable']"));
        Actions actions=new Actions(driver);
        actions.dragAndDropBy(Draggable,100,150).perform();//just try on website u will see x and y with element in DOM
       //
        Thread.sleep(3000);
        driver.switchTo().defaultContent();

    }
    @AfterSuite
    public void quit()
    {
        driver.close();
    }
}
