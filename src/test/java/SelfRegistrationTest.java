import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SelfRegistrationPage;

import java.util.List;

public class SelfRegistrationTest extends BaseTest {
    @Test
    public void testSuccessfulSelfReg() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        selfRegPage.inputLastName("Мужный");
        selfRegPage.inputFirstName("Кузьма");
        selfRegPage.inputMiddleName("Отчествович");
        selfRegPage.inputPosition("Леший");
        selfRegPage.inputEmail("vasya.pupkin@crazyMegaHell.ua");
        selfRegPage.inputPhone();
        selfRegPage.inputPassword("lole228");
        selfRegPage.inputConfirmPassword("lole228");
        selfRegPage.checkOfferCheckbox();
        selfRegPage.clickRegistrationButton();
        Assert.assertEquals(selfRegPage.checkSMSCodePresent(), true, "SMS code has not been sent");
    }

    @Test
    public void testSelfRegUncheckOfferCheckbox() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        selfRegPage.inputLastName("Мужный");
        selfRegPage.inputFirstName("Кузьма");
        selfRegPage.inputMiddleName("Отчествович");
        selfRegPage.inputPosition("Леший");
        selfRegPage.inputEmail("vasya.pupkin@crazyMegaHell.ua");
        selfRegPage.inputPhone();
        selfRegPage.inputPassword("lole228");
        selfRegPage.inputConfirmPassword("lole228");
        Assert.assertEquals(selfRegPage.checkRegistrationButtonEnabled(), false, "Offer acceptance is not mandatory");
    }

    @Test
    public void testSelfRegCancelButton() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        selfRegPage.inputLastName("Мужный");
        selfRegPage.inputFirstName("Кузьма");
        selfRegPage.inputMiddleName("Отчествович");
        selfRegPage.inputPosition("Леший");
        selfRegPage.inputEmail("vasya.pupkin@crazyMegaHell.ua");
        selfRegPage.inputPhone();
        selfRegPage.inputPassword("lole228");
        selfRegPage.inputConfirmPassword("lole228");
        selfRegPage.checkOfferCheckbox();
        selfRegPage.clickCancelButton();
        Assert.assertEquals(selfRegPage.getPageURL(), "https://release.alarm-navigator.ru/login", "Cancel button won't work");
    }

    @Test
    public void testSelfRegCancelButtonThenReturn() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        selfRegPage.inputLastName("Мужный");
        selfRegPage.inputFirstName("Кузьма");
        selfRegPage.inputMiddleName("Отчествович");
        selfRegPage.inputPosition("Леший");
        selfRegPage.inputEmail("vasya.pupkin@crazyMegaHell.ua");
        selfRegPage.inputPhone();
        selfRegPage.inputPassword("lole228");
        selfRegPage.inputConfirmPassword("lole228");
        selfRegPage.checkOfferCheckbox();
        selfRegPage.clickCancelButton();
        SelfRegistrationPage selfRegPage2 = loginPage.ckickSelfRegistration();
        List<WebElement> inputs = selfRegPage2.getInputsList();

        for (WebElement input : inputs) {
            Assert.assertEquals(selfRegPage2.getInputText(input), "", "The fields are not empty after returning to the Self-registration page");
        }
    }

    @Test
    public void testErrorRedField() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        selfRegPage.inputLastName("Мужный");
        selfRegPage.inputFirstName("Кузьма");
        selfRegPage.inputMiddleName("Отчествович");
        selfRegPage.inputPosition("Леший");
        selfRegPage.inputEmail("vasya.pupkin");
        Assert.assertEquals(selfRegPage.checkRedErrorField(), true, "The error field is not underlined in red");
    }

    @Test
    public void testOfferLink() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        SelfRegistrationPage.OfferPage offerPage = selfRegPage.clickOfferLink();
        getWindowManager().switchToNewlyOpenedTab();
        Assert.assertEquals(offerPage.checkOfferHeader(), true, "Wrong header in offer.html or wrong html file");
    }

    @Test
    public void testInvalidPwErrorMsg() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        selfRegPage.inputPassword("vladik");
        Assert.assertEquals(selfRegPage.getPWErrorMsgText(), "Пароль должен быть не менее 6 символов, содержать латинские буквы и не менее одной цифры", "Wrong Error Msg to not secure pw");
    }

    @Test
    public void testInvalidConfirmPW() {
        SelfRegistrationPage selfRegPage = loginPage.ckickSelfRegistration();
        selfRegPage.inputPassword("pelmen1");
        selfRegPage.inputConfirmPassword("pelmen2");
        Assert.assertEquals(selfRegPage.getConfirmPWErrorMsgText(), "Пароли не совпадают", "Wrong Error Msg to not matching pw");
    }
}

