import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTestNew {
    private EventFiringWebDriver driver;
    //private static WebDriver simpledriver;
    private String link ="https://release.alarm-navigator.ru/";

    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        // driver.register(new EventReporter());
        // driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //неявное ожидание
        goHome();

        loginPage = new LoginPage(driver);
        //driver.get(link);
        loginPage.inputLogin("operator");
        loginPage.inputPassword("skeleton");
        loginPage.clickLoginButton();
        homePage = new HomePage(driver);
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void goHome(){
        driver.get(link);
    }

    /*public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }*/

    public void takeScreenshot(ITestResult result) throws IOException {
        TakesScreenshot camera = (TakesScreenshot)driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        Files.move(screenshot, new File("src/test/resources/screenshots/screenshot_" + result.getName()+ ".png"));
    }

    @AfterMethod
    public void recordFailure(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            takeScreenshot(result);
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); // w/o infobar
        //chromeOpt.setHeadless(true);  // w/o browser setup

        chromeOpt.addArguments("--disable-extensions");
        chromeOpt.addArguments("test-type");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOpt.setExperimentalOption("prefs", prefs);

        return chromeOpt;
    }

}

