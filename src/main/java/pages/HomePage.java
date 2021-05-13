package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.By.cssSelector;


public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, HomePage.class);
    }

    private By headerText = cssSelector("div.Header_topPanelLeftTitle__2GClZ");
    private By operatorListLink = cssSelector("a[title=\"Операторы\"]");
    private By facilityListLink = cssSelector("a[title=\"Объекты\"]");
    private By supervisorListLink = cssSelector("a[title=\"Начальники объектов\"]");
    private By guardListLink = cssSelector("a[title=\"Сотрудники\"]");
    private By sosListLink = cssSelector("a[title=\"Список SOS\"]");
    private By reportListLink = cssSelector("a[title=\"Отчеты\"]");
    private By logoutLink = cssSelector("a[title=\"Выход\"]");

  /*  @FindBy(css = ".Table_addButton__2Y1rD")
    public static WebElement addButton;*/

    public String getHeader() {
        return driver.findElement(headerText).getText();
    }

    /*
    Main Menu Navigation
     */

    public GuardListPage clickGuardInMainMenu() {
        driver.findElement(guardListLink).click();
        return new GuardListPage(driver);
    }

    public SupervisorListPage clickSupervisorInMainMenu() {
        driver.findElement(supervisorListLink).click();
        return new SupervisorListPage(driver);
    }

    public SosListPage clickSOSInMainMenu() {
        driver.findElement(sosListLink).click();
        return new SosListPage(driver);
    }

    public ReportPageList clickReportInMainMenu() {
        driver.findElement(reportListLink).click();
        return new ReportPageList(driver);
    }

    public LoginPage clickLogoutInMainMenu() {
        driver.findElement(logoutLink).click();
        return new LoginPage(driver);
    }

    public HomePage clickFacilityInMainMenu() {
        driver.findElement(facilityListLink).click();
        return new HomePage(driver);
    }

    public OperatorListPage clickOperatorInMainMenu() {
        driver.findElement(operatorListLink).click();
        return new OperatorListPage(driver);
    }

    public void waitLoading(){
        WebDriverWait wait =  new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("Spinner_spinnerBack__12Rne")));
    }

}
