package InterviewQuestions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import src.main.java.SeleniumUtilities;

import java.util.List;

public class SelctClassQuestions {
    WebDriver driver=null;
    SeleniumUtilities util=null;
    @BeforeSuite
    public void initialize()
    {
        WebDriverManager.chromedriver().setup();//lanuch the browser
        driver=new ChromeDriver();
        util=new SeleniumUtilities(driver);
        driver.get("https://qa-tekarch.firebaseapp.com/");//we navigate to the UrL of the application
        //the we do functionality testing of application
        WebElement Username=driver.findElement(By.xpath("//input[@id='email_field']"));
        Username.sendKeys("admin123@gmail.com");
        WebElement Password=driver.findElement(By.cssSelector("#password_field"));
        Password.sendKeys("admin123");
        WebElement loginButton=driver.findElement(By.cssSelector("button[onclick='login()']"));
        util.waitExplicitely(loginButton);
        loginButton.click();
    }
    //@Test
    public void CheckIfDropDownSelectsMultipleValues()
    {
      WebElement city=driver.findElement(By.xpath("//select[@id='city']"));
      util.waitExplicitely(city);
      Select dropdown=new Select(city);
        //Alwaya have a practice of selectiong something by visible text, its is easy
      dropdown.selectByVisibleText("GOA");
      System.out.println(dropdown.isMultiple());//true if it takes multiple value or else false


    }
    //@Test
    public void displayAllElementsInDropDown()
    {
        WebElement city=driver.findElement(By.xpath("//select[@id='city']"));
        util.waitExplicitely(city);
        Select dropdown=new Select(city);
        List<WebElement> Options= dropdown.getOptions();
        System.out.println("Size of the dropdown is "+ (Options.size()-1));//-1 bcz it had select in first position, I don't want to count it
        for(int i=1;i<Options.size();i++)
        { //We don't want 0th position
            System.out.println(Options.get(i).getText());
        }
    }
    @Test
    public void SelectMumbaiWithoutSelectClass()
    {



        List<WebElement> cities=driver.findElements(By.xpath("//select[@id='city']//option"));
        System.out.println("Total number of cities are "+(cities.size()-1));
      // for(int i=0;i<cities.size();i++)
        for(int i =1 ;i< cities.size(); i++)
              {
                  if(cities.get(i).getAttribute("value").equals("patna")) {
                    //  ((JavascriptExecutor) driver).executeScript("arguments[0].click();",cities.get(i));
                    //  cities.get(i).click();--->this was not working for me
;                      break;}
            System.out.println(cities.get(i).getAttribute("value"));//.getText Was not working for me so used Attribute value\

             }

        //   System.out.println(cities.get(i).getText());
           /*
           }*/


    }

}
