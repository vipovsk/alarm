package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.cssSelector;

public class GuardListPage {
    private WebDriver driver;

    public GuardListPage(WebDriver driver) {
        this.driver = driver;
    }

    private By headerText = cssSelector("div.Header_topPanelLeftTitle__2GClZ");
    private By addButton = By.className("Table_addButton__2Y1rD");
    private By lastPageListButton = By.xpath(".//button[@aria-label=\"Последняя страница\"]");
    private By lastListItemEditButton = By.className(".//tbody/tr[last()]/td[@value=\"[object Object]\"]/div/a");


    public int getGuardCount() {
        String guardCount = driver.findElement(headerText).getText();
        guardCount = guardCount.substring(guardCount.indexOf('(') + 1, guardCount.indexOf(')'));
        return (int) Integer.valueOf(guardCount);
    }

    public GuardEditorPage clickAddGuard() {
        driver.findElement(addButton).click();
        return new GuardEditorPage(driver);
    }

    public void waitUntilSpinnerNotPresent(){
        WebDriverWait wait =  new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("Spinner_spinnerBack__12Rne")));
    }

    public void clickLastPageListButton(){
        driver.findElement(lastPageListButton).click();
    }

    public GuardEditorPage clickEditButtonForLastListItem(){
        clickLastPageListButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.attributeContains(lastPageListButton, "diasbled", null));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@aria-label=\"Первая страница\"]")));
        driver.findElement(lastListItemEditButton).click();
        return new GuardEditorPage(driver);
    }
}
