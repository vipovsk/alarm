package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class GuardEditorPage implements ProfileEditorPage {
    private WebDriver driver;
    private By supervisorSelect = By.xpath("(.//div[@class=\"Editor_fieldContainer__yeBsy\"]/div/select)[5]");


    GuardEditorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void inputLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    @Override
    public void inputFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    @Override
    public void inputMiddleName(String middleName) {
        driver.findElement(middleNameField).sendKeys(middleName);
    }

    @Override
    public void inputPhone() {

        int max = 9;
        int min = 1;
        int range = max - min + 1;
        String phone = "37555";

        for (int i = 0; i < 7; i++) {
            int rand = (int) (Math.random() * range) + min;
            phone = phone.concat(String.valueOf(rand));
        }

        driver.findElement(phoneField).sendKeys(phone);
    }

    @Override
    public void setProfilePic(String picLink) {
        driver.findElement(profilePic).click();
        driver.findElement(picPickButton).click();
        driver.findElement(picUploadInput).sendKeys(picLink);
        driver.findElement(picSaveButton).click();
    }

    public void zoomProfilePic() {
        driver.findElement(profilePic).click();
        moveZoomSliderRight();
    }

    public void rotateProfilePic(int time) {
        driver.findElement(profilePic).click();
        driver.findElement(picSliderRotateButton).click();
        for (int i = 1; i <= time; i++) {
            moveRotateSliderRight();
        }
    }

    private Select findQualificationSelect() {
        return new Select((driver.findElement(qualificationSelect)));
    }

    public void setQualification(String qualification) {
        findQualificationSelect().selectByVisibleText(qualification);
    }

    public List<String> getSelectedOption() {
        List<WebElement> selectedElements = findQualificationSelect().getAllSelectedOptions();
        return selectedElements.stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    public void moveZoomSliderRight() {
        Actions action = new Actions(driver);
        action.clickAndHold(driver.findElement(picSliderZoomButton));
        action.moveByOffset(20, 0).release().build().perform();

    }

    public String getPicAbsolutePath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        File pic = null;
        try {
            pic = new File(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return pic.getAbsolutePath();
    }

    public void moveRotateSliderRight() {
        driver.findElement(picSliderRotateButton).sendKeys(Keys.RIGHT);

    }

    private Select findSupervisorSelect() {
        return new Select((driver.findElement(supervisorSelect)));
    }

    public void setSupervisor(int fsId) {
        findSupervisorSelect().selectByIndex(fsId);
    }

    public GuardListPage clickSaveButton() {
        driver.findElement(saveButton).click();
        return new GuardListPage(driver);
    }

    public GuardListPage clickCancelButton() {
        driver.findElement(cancelButton).click();
        return new GuardListPage(driver);
    }

    public String getZoomSliderValue() {
        return driver.findElement(picZoomSliderValue).getAttribute("value");
    }

    public String getRotateSliderValue() {
        return driver.findElement(picSliderRotateButton).getAttribute("aria-valuenow");
    }


    public GuardListPage deleteProfile(){
        driver.findElement(deleteButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(verifyDeleteButton));
        driver.findElement(verifyDeleteButton).click();
        return new GuardListPage(driver);
    }
}
