package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class Stepdefs {
    WebDriver driver = new FirefoxDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        clickLinkWithText("login", driver);
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("nonexisting username {string} and random password like {string} are given")
    public void nonExistingUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        clickLinkWithText("register new user", driver);
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernamePasswordAndConfimationIsEntered(String username, String password) {
        createUserWith(username, password, password, true);
    }

    @When("a too short username {string} and password {string} and matching password confirmation are entered")
    public void tooShortUsernameWithOkPasswordAndConfimationIsEntered(String username, String password) {
        createUserWith(username, password, password, false);
    }

    @Then("a new user is created")
    public void newUserIsTakenToMainPage() {
        pageHasContent("continue to application mainpage");
        pageHasContent("course page");
    }

    @Then("user is not created and error {string} is reported")
    public void userNotCreatedWithError(String errorMessage){
        pageHasContent(errorMessage);
    }

    @When("a valid username {string} and invalid password {string} and matching password confirmation are entered")
    public void validUsernameWithInvalidPasswordAndConfimationIsEntered(String username, String password) {
        createUserWith(username, password, password, true);
    }

    @When("a valid username {string} and non matching passwords {string} and {string} are entered")
    public void validUsernameWithNonMatchingPasswordsAreEntered(String username, String password, String passwordConfirmation) {
        createUserWith(username, password, passwordConfirmation, true);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        sleep(1);
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
    }

    private void createUserWith(String username, String password, String passwordConfimation, boolean withRandom){
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        WebElement element = driver.findElement(By.name("username"));
        if (withRandom){
            char a1 = alphabet.charAt(r.nextInt(alphabet.length()));
            char a2 = alphabet.charAt(r.nextInt(alphabet.length()));
            char a3 = alphabet.charAt(r.nextInt(alphabet.length()));
            char a4 = alphabet.charAt(r.nextInt(alphabet.length()));
            char a5 = alphabet.charAt(r.nextInt(alphabet.length()));
            element.sendKeys(username + a1 + a2 + a3 + a4 + a5);
        } else {
            element.sendKeys(username);
        }
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfimation);
        element = driver.findElement(By.name("signup"));
        sleep(1);
        element.submit();
        sleep(1);
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while( trials++<5 ) {
            try{
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch(Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
