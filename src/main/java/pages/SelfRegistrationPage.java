package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SelfRegistrationPage {
    private WebDriver driver;
    private By lastName = By.cssSelector("input[placeholder=\"Фамилия\"]");
    private By firstName = By.cssSelector("input[placeholder=\"Имя\"]");
    private By middleName = By.cssSelector("input[placeholder=\"Отчество\"]");
    private By position = By.cssSelector("input[placeholder=\"Должность\"]");
    private By email = By.cssSelector("input[placeholder=\"E-Mail\"]");
    private By password = By.cssSelector("input[placeholder=\"Пароль\"]");
    private By confirmPassword = By.cssSelector("input[placeholder=\"Повторите пароль\"]");
    private By phone = By.cssSelector("input[placeholder=\"Телефон\"]");
    private By offerCheckbox = By.id("id_offer");
    private By registrationButton = By.cssSelector("div.SelfRegistrationForm_formRow__2TLka.SelfRegistrationForm_formRowRegistration__2mYQD > button");
    private By cancelButton = By.xpath(".//a[text()=\"Отмена\"]");
    private By offerLink = By.xpath(".//a[@href=\"/offer.html\"]");
    private By smsCodeField = By.cssSelector("[placeholder=\"Проверочный код\"]");
    private By errorField = By.cssSelector("div.MuiInput-underline.Mui-error");
    private By inputField = By.cssSelector("input.MuiInputBase-input");
    private By pwErrorMsg = By.xpath(".//div[@class=\"SelfRegistrationForm_formRowItem__2m4RT\"][1]//p/span");
    private By confirmPwErrorMsg = By.xpath(".//div[@class=\"SelfRegistrationForm_formRowItem__2m4RT\"][2]//p/span");


    public SelfRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputFirstName(String firstNameStr) {
        driver.findElement(firstName).sendKeys(firstNameStr);
    }

    public void inputLastName(String lastNameStr) {
        driver.findElement(lastName).sendKeys(lastNameStr);
    }

    public void inputPosition(String positionStr) {
        driver.findElement(position).sendKeys(positionStr);
    }

    public void inputMiddleName(String middleNameStr) {
        driver.findElement(middleName).sendKeys(middleNameStr);
    }

    public void inputPhone() {

        int max = 9;
        int min = 1;
        int range = max - min + 1;
        String phoneStr = "37555";

        for (int i = 0; i < 7; i++) {
            int rand = (int) (Math.random() * range) + min;
            phoneStr = phoneStr.concat(String.valueOf(rand));
        }

        driver.findElement(phone).sendKeys(phoneStr);
    }

    public void inputEmail(String emailStr) {
        driver.findElement(email).sendKeys(emailStr);
    }

    public void inputPassword(String pwStr) {
        driver.findElement(password).sendKeys(pwStr);
    }

    public void inputConfirmPassword(String confirmPwStr) {
        driver.findElement(confirmPassword).sendKeys(confirmPwStr);
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void checkOfferCheckbox() {
        driver.findElement(offerCheckbox).click();
    }

    public void clickCancelButton() {
        driver.findElement(cancelButton).click();
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public OfferPage clickOfferLink() {
        driver.findElement(offerLink).click();
        return new OfferPage(driver);
    }

    public boolean checkSMSCodePresent() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(smsCodeField));
        return driver.findElement(smsCodeField).isDisplayed();
    }

    public boolean checkRegistrationButtonEnabled(){
        return driver.findElement(registrationButton).isEnabled();
    }

    public boolean checkRedErrorField(){
        return driver.findElement(errorField).isDisplayed();
    }

    public List<WebElement> getInputsList(){
        return driver.findElements(inputField);
    }

    public String getInputText(WebElement input){
            return input.getText();
    }

    public String getPWErrorMsgText(){
        return driver.findElement(pwErrorMsg).getText();
    }

    public String getConfirmPWErrorMsgText(){
        return driver.findElement(confirmPwErrorMsg).getText();
    }

    public class OfferPage{
        private WebDriver driver;
        private By offerHeader = By.xpath(".//div[text()=\"Договор Оферты\"]");

        OfferPage(WebDriver driver){
            this.driver = driver;
        }

        public boolean checkOfferHeader(){
            return driver.findElement(offerHeader).isDisplayed();
        }

    }
}
