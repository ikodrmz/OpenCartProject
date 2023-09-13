package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

public class BaseClass {
    public Logger logger;  //for logging
    public WebDriver driver;

    public ResourceBundle rb;

    @BeforeClass(groups = {"Master","Sanity","Regression"})
    @Parameters("browser")
    public void setup(String br){

        rb=ResourceBundle.getBundle("config"); //load config.properties file

        logger= LogManager.getLogger(this.getClass());

        if (br.equals("edge")){
            driver=new EdgeDriver();
        }
        else if (br.equals("firefox")){
            driver=new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(rb.getString("appURL"));
        driver.get("http://localhost/opencart/upload/index.php?route=common/home&language=en-gb");
    }

    @AfterClass(groups = {"Master","Sanity","Regression"})
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String generatedString= RandomStringUtils.randomAlphabetic(5);
        return(generatedString);
    }
    public String randomNumber(){
        String generatedString2= RandomStringUtils.randomNumeric(10);
        return(generatedString2);


    }
    public String randomAlphaNumeric(){
        String st= RandomStringUtils.randomAlphanumeric(5);
        String num= RandomStringUtils.randomNumeric(10);
        return(st+num);
    }

//    public String captureScreen(String tname) throws IOException {
//
//        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
//
//        try {
//            FileUtils.copyFile(source, new File(destination));
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return destination;
//
//    }

}
