package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        // Make sure you have Geckodriver installed and moved to /usr/bin/geckodriver
        WebDriver driver = new FirefoxDriver();
        final String URL = "http://localhost:4567";

        successfulLogin(driver, URL);
        sleep(1);
        failedLogin(driver, URL);
        sleep(1);
        logOutAfterUserCreation(driver, URL);
        driver.quit();
    }

    public static void successfulLogin(WebDriver driver, String URL){
        driver.get(URL);

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();
    }

    public static void failedLogin(WebDriver driver, String URL){
        driver.navigate().to(URL);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong!");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();
        sleep(2);
    }

    public static void logOutAfterUserCreation(WebDriver driver, String URL){
        Random r = new Random();
        driver.navigate().to(URL);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("testaaja" +r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("12345");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("12345");
        element = driver.findElement(By.name("signup"));
        sleep(1);
        element.submit();
        sleep(1);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(1);
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
