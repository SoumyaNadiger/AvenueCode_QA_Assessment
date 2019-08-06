package com.ac.qa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class BaseClass {
	
	public static WebDriver driver;
    public static Properties prop;
    
    public BaseClass()
    {
        try
        {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("C:\\Users\\Rohit\\Desktop\\AvenueCode_QaAssessment\\AC_MyTasks\\src\\main\\java\\com.ac.qa.config\\config.properties");
            prop.load(fis);
        }catch(IOException e)
        {
            e.getMessage();
        }
       
    }
    
    public static void initializeBrowser()
    {
        String browserName = prop.getProperty("browser");

        if(browserName.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rohit\\Desktop\\AvenueCode_QaAssessment\\Chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            //driver.get("https://qa-test.avenuecode.com");
        }
        else if(browserName.equals("Firefox"))
        {
            System.setProperty("webdriver.gecko.driver","C:\\Users\\Rohit\\Desktop\\AvenueCode_QaAssessment\\geckodriver-v0.24.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            //driver.get("https://qa-test.avenuecode.com");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TimeLoad.PAGE_LOAD, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TimeLoad.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }

    public static void quit()
    {
        driver.quit();
    }

}



