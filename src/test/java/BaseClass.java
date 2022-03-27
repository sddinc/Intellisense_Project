import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.loginPage;
import utilities.ReusableMethods;
import java.util.List;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    loginPage loginPage = new loginPage();

    WebDriver mydriver;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/Driver/chromedriver.exe");
        mydriver = new ChromeDriver();

        mydriver.manage().window().maximize();
        mydriver.manage().deleteAllCookies();
        mydriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        mydriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        mydriver.get("https://reference-test.intellisense.io/#!/id/dashboards/554");
    }


    @Test
    public void loginTest() {
        String str= mydriver.getTitle();
        System.out.println(str);

        WebElement userEmail= mydriver.findElement(By.xpath("//input[@id='emailAddress']"));
        userEmail.sendKeys("menna+testproject@intellisense.io");

        WebElement userPassword= mydriver.findElement(By.xpath("//input[@id='password']"));
        userPassword.sendKeys("AutMaNewTest1#");

        WebElement signInButton= mydriver.findElement(By.xpath("//h6[contains(text(),'Sign In')]"));
        signInButton.click();

        WebElement afterLoginTitle= mydriver.findElement(By.xpath("//h1[contains(text(),'Search')]"));
        WebElement toolsDateTime= mydriver.findElement(By.xpath("//header/div[1]/div[3]/button[3]"));
        WebElement toolsDateTimeHistoric= mydriver.findElement(By.xpath("//body/div[5]/div[1]/div[3]/nav[1]/div[1]/a[2]"));

        WebElement calendarFrom= mydriver.findElement(By.xpath("(//div[@class='control-toggle'])[1]"));
        WebElement calendarFrom2= mydriver.findElement(By.xpath("(//div[@selection='from']//span)[2]"));
        WebElement calendarBeforeArrow= mydriver.findElement(By.xpath("(//button[@class='previous btn btn-text'])[1]"));
        WebElement calendarBeforeToArrow= mydriver.findElement(By.xpath("(//button[@class='previous btn btn-text'])[2]"));

        WebElement calendarTo= mydriver.findElement(By.xpath("(//div[@class='control-toggle'])[2]"));
        WebElement calendarTo2= mydriver.findElement(By.xpath("(//div[@selection='to']//span)[2]"));

        //List<WebElement> listOfcalendarFromDays= (List<WebElement>) mydriver.findElement(By.xpath("//div[@selection='from']//div"));

        //List<WebElement> listOfcalendarToDays= (List<WebElement>) mydriver.findElement(By.xpath("//div[@selection='to']//div"));


        WebElement calendarFromHour= mydriver.findElement(By.xpath("(//div[@class='time-container']//select)[1]"));
        WebElement calendarFromMinutes= mydriver.findElement(By.xpath("(//div[@class='time-container']//select)[2]"));

        WebElement calendarToHour= mydriver.findElement(By.xpath("(//div[@class='time-container']//select)[3]"));
        WebElement calendarToMinutes= mydriver.findElement(By.xpath("(//div[@class='time-container']//select)[4]"));

        WebElement calendarSubmitButton= mydriver.findElement(By.xpath("(//button[@class='btn btn-sm btn-primary'])[2]"));

        String actualTitleAfterLogin= afterLoginTitle.getText();
        System.out.println(actualTitleAfterLogin);
        String expectedTitleAfterLogin="Search";
        Assert.assertEquals(actualTitleAfterLogin,expectedTitleAfterLogin);
        toolsDateTime.click();
        toolsDateTimeHistoric.click();
        calendarFrom.click();

        ReusableMethods.selectByValue(calendarFromHour,"11");
        ReusableMethods.selectByValue(calendarFromMinutes,"35");

        String valStr=calendarFrom2.getText();
        System.out.println("title calendar ="+valStr);

        while(!valStr.equals("Nov 2020")){
            calendarBeforeArrow.click();
            // mydriver.wait(5);
            valStr=calendarFrom2.getText();
            System.out.println("title calendar ="+valStr);
        }

        for (int i = 1; i <35 ; i++) {
            String days="(//div[@selection='from']//div)["+i+"]";

            if(mydriver.findElement(By.xpath(days)).getText().equals("2")){
                mydriver.findElement(By.xpath(days)).click();
            }
        }

        calendarTo.click();
        String valStr2=calendarTo2.getText();
        while(!valStr2.equals("Feb 2022")){
            calendarBeforeToArrow.click();
            // mydriver.wait(5);
            valStr2=calendarTo2.getText();
            System.out.println("title calendar ="+valStr2);
        }

        for (int i = 1; i <35 ; i++) {
            String days="(//div[@selection='to']//div)["+i+"]";

            if(mydriver.findElement(By.xpath(days)).getText().equals("14")){
                mydriver.findElement(By.xpath(days)).click();
            }
        }

        ReusableMethods.selectByValue(calendarToHour,"12");
        ReusableMethods.selectByValue(calendarToMinutes,"35");

        calendarSubmitButton.click();


         WebElement  setDateFrom=mydriver.findElement(By.xpath("(//span[@class='date-range'])[1]"));
         WebElement  setDateTo=mydriver.findElement(By.xpath("(//span[@class='date-range'])[2]"));
         WebElement  menuAlert=mydriver.findElement(By.xpath("//i[@class='fa fa-warning icon-alert']"));
         WebElement  alertTest=mydriver.findElement(By.xpath("  (//li[@data-ng-repeat='item in newAlerts'])[1]"));

         mydriver.get("https://reference-test.intellisense.io/#!/id/dashboards/1622");


      menuAlert.click();
      alertTest.click();

         Assert.assertEquals(setDateFrom,"Nov 2, 2020");
         Assert.assertEquals(setDateTo,"Feb 14, 2022");

    }

    @AfterMethod
    public void tearDown(){
      //  mydriver.quit();
    }

}
