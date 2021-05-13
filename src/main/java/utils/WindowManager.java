package utils;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WindowManager {
    private WebDriver driver;
    private WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver){
        this.driver = driver;
        navigate = driver.navigate();
    }

    public void goBack(){
        driver.navigate().back();
    }

    public void goForvard(){
        navigate.forward();
    }

    public void refresh(){
        navigate.refresh();
    }

    public void goToURL(String url){
        navigate.to(url);
    }

    public void closeCurrentPage(){
        driver.close();
    }

    public void switchToTab(String tabTitle){
        Set<String> windows = driver.getWindowHandles();

        for(String window: windows){

            driver.switchTo().window(window);

            if(tabTitle.equals(driver.getTitle())){
                break;
            }
        }
    }

    public void switchToNewlyOpenedTab(){
        Set<String> windows = driver.getWindowHandles();
        List<String> windowStrings = new ArrayList<>(windows);
        String newWindow = windowStrings.get(windowStrings.size()-1);
        driver.switchTo().window(newWindow);
    }
}
