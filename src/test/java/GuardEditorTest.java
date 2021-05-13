import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.GuardEditorPage;
import pages.GuardListPage;

public class GuardEditorTest extends BaseTestNew{

    @Test
    public void testPicZoom(){
        homePage.waitLoading();
        GuardListPage guardListPage = homePage.clickGuardInMainMenu();
        guardListPage.waitUntilSpinnerNotPresent();
        GuardEditorPage guardEditorPage = guardListPage.clickAddGuard();
        guardEditorPage.setProfilePic(guardEditorPage.getPicAbsolutePath("pics/photo_2021-03-06_14-39-56"));
        guardEditorPage.zoomProfilePic();
        Assert.assertEquals(guardEditorPage.getZoomSliderValue(), "1.2", "Incorrectly zoomed");
    }

    @Test
    public void testPicRotate() throws InterruptedException {
        homePage.waitLoading();
        GuardListPage guardListPage = homePage.clickGuardInMainMenu();
        guardListPage.waitUntilSpinnerNotPresent();
        GuardEditorPage guardEditorPage = guardListPage.clickAddGuard();
        Thread.sleep(4000);
        guardEditorPage.setProfilePic(guardEditorPage.getPicAbsolutePath("pics/photo_2021-03-06_14-58-13"));
        guardEditorPage.rotateProfilePic(3);
        Assert.assertEquals(guardEditorPage.getRotateSliderValue(), "3", "Incorrectly rotated");
    }
}
