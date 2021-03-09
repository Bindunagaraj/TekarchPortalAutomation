package src.main.java;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtilities {
    WebDriver driver=null;

    public SeleniumUtilities(WebDriver driver) {
        this.driver=driver;
    }

    public void ScrollIntoView(WebElement ele)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",ele);
    }
    public void alertHandle()
    {
        Alert alert1=driver.switchTo().alert();
        alert1.accept();
        driver.switchTo().defaultContent();
    }
    public void mouseOver(WebElement ele)
    {
        Actions action=new Actions(driver);
        action.moveToElement(ele).click().build().perform();

    }
    public void selectByIndex(WebElement ele , int n)
    {

        Select select=new Select(ele);
        select.selectByIndex(n);
    }
    public void selectByVisibleTExt(WebElement ele, String text)
    {
        Select select=new Select(ele);
        select.selectByVisibleText(text);
    }
    public void selectByValue(WebElement ele,String value)
    {
        Select select=new Select(ele);
        select.selectByValue(value);
    }
    public void waitExplicitely(WebElement ele)
    {
        WebDriverWait wait=new WebDriverWait(driver , 20);
        wait.until(ExpectedConditions.visibilityOf(ele));


    }
}
