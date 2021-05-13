import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.GuardEditorPage;
import pages.GuardListPage;
import pages.HomePage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GuardListTest extends BaseTestNew{
    protected WebDriver driver;

    @Test
    public void testAddGuardAndCancel() throws InterruptedException {
        homePage.waitLoading();
        GuardListPage guardListPage = homePage.clickGuardInMainMenu();
        guardListPage.waitUntilSpinnerNotPresent();
        int guardCountBeforeAdding = guardListPage.getGuardCount();
        GuardEditorPage guardEditorPage = guardListPage.clickAddGuard();
        guardEditorPage.inputLastName("Моржовый");
        guardEditorPage.inputFirstName("Мистер");
        guardEditorPage.inputMiddleName("Алексеич");
        guardEditorPage.inputPhone();
        guardEditorPage.setQualification("3-й разряд");
        guardEditorPage.setProfilePic(guardEditorPage.getPicAbsolutePath("pics/photo_2021-03-06_14-51-27.jpg"));
        Thread.sleep(4000);
        guardEditorPage.setSupervisor(1);
        guardListPage = guardEditorPage.clickCancelButton();
        Assert.assertEquals(guardListPage.getGuardCount(), guardCountBeforeAdding, "Guard count has changed");
    }

    @Test
    public void testSuccessfulAddGuard() throws InterruptedException {
        homePage.waitLoading();
        GuardListPage guardListPage = homePage.clickGuardInMainMenu();
        guardListPage.waitUntilSpinnerNotPresent();
        int guardCountBeforeAdding = guardListPage.getGuardCount();
        GuardEditorPage guardEditorPage = guardListPage.clickAddGuard();
        guardEditorPage.inputLastName("Моржовый");
        guardEditorPage.inputFirstName("Мистер");
        guardEditorPage.inputMiddleName("Алексеич");
        guardEditorPage.inputPhone();
        guardEditorPage.setQualification("3-й разряд");
        guardEditorPage.setProfilePic(guardEditorPage.getPicAbsolutePath("pics/photo_2021-03-06_14-51-27.jpg"));
        guardEditorPage.setSupervisor(1);
        Thread.sleep(2000);
        guardListPage = guardEditorPage.clickSaveButton();
        Thread.sleep(6000);
        Assert.assertEquals(guardListPage.getGuardCount(), guardCountBeforeAdding+1, "Guard count has changed");
    }

    @Test
    public void testSuccessfulDeleteGuard() throws InterruptedException {
        homePage.waitLoading();
        GuardListPage guardListPage = homePage.clickGuardInMainMenu();
        guardListPage.waitUntilSpinnerNotPresent();
        int guardCountBeforeAdding = guardListPage.getGuardCount();
        GuardEditorPage guardEditorPage = guardListPage.clickEditButtonForLastListItem();
        guardListPage = guardEditorPage.deleteProfile();
        Thread.sleep(6000);
        Assert.assertEquals(guardListPage.getGuardCount(), guardCountBeforeAdding-1, "Guard count has changed");
    }


}