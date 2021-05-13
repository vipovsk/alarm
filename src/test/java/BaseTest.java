import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import utils.WindowManager;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private EventFiringWebDriver driver;
    // private static WebDriver driver;
    private String link ="https://release.alarm-navigator.ru/login/";

    protected LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        // driver.register(new EventReporter());
        //driver= new ChromeDriver();
        driver.manage().window().maximize();
        //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //неявное ожидание
        goHome();
        //setLocalStorage();
        loginPage = new LoginPage(driver);
        //driver.get(link);

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @BeforeMethod
    public void goHome(){
        driver.get(link);
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

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

   /* private void setLocalStorage(){
        LocalStorage local = ((WebStorage) driver).getLocalStorage();
        //local.setItem("vipovsk", "228");
        System.out.print(local.getItem("authState"));
    }*/
}

