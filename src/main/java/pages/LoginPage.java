package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static WebDriver driver;
    private static By loginInput = By.xpath(".//input[@type=\"text\"]");
    private static By passwordInput = By.xpath(".//input[@type=\"password\"]");
    private static By loginButton = By.cssSelector("div.LoginForm_loginContainerLoginForm__1-U9D >button");
    private static By errorMsg = By.className("PopupDialog_popupContainer__pw8gS");
    private static By selfRegLink = By.cssSelector("div [href=\"/self-registration\"]");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public static void inputLogin(String login){
        driver.findElement(loginInput).sendKeys(login);
    }

    public static void inputPassword(String pw){
        driver.findElement(passwordInput).sendKeys(pw);
    }

    public static void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public static HomePage loginAsOperator(){
        clickLoginButton();
        return new HomePage(driver);
    }

    public static String getErrorText(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(errorMsg));
        return driver.findElement(errorMsg).getText();
    }

    public static boolean checkDisabledLoginButton(){
        return driver.findElement(loginButton).isEnabled();
    }

    public SelfRegistrationPage ckickSelfRegistration(){
       driver.findElement(selfRegLink).click();
       return new SelfRegistrationPage(driver);
    }
}
