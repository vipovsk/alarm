import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testOperatorAuth() throws InterruptedException {
        loginPage.inputLogin("+375291598290");
        loginPage.inputPassword("skeleton");
        HomePage homePage = LoginPage.loginAsOperator();
        Thread.sleep(2000);
        Assert.assertEquals(homePage.getHeader(), "Oбъекты", "Wrong header or might be wrong page");
    }

    @Test
    public void testWrongUsername() {
        loginPage.inputLogin("+375");
        loginPage.inputPassword("skeleton");
        loginPage.clickLoginButton();
        Assert.assertEquals(LoginPage.getErrorText(), "Пользователь не найден\n" +
                "Ок", "Wrong Error Text");
    }

    @Test
    public void testWrongPassword() {
        loginPage.inputLogin("+375291598290");
        loginPage.inputPassword("baltika0");
        ;
        loginPage.clickLoginButton();
        Assert.assertEquals(LoginPage.getErrorText(), "Неправильный пароль\n" +
                "Ок", "Wrong Error Text");
    }

    @Test
    public void testEmptyUsername() {
        loginPage.inputLogin("");
        loginPage.inputPassword("baltika0");
        Assert.assertEquals(LoginPage.checkDisabledLoginButton(), false, "The Login Button is enabled provided the Username field is empty");
    }

    @Test
    public void testEmptyPW() {
        loginPage.inputLogin("+375291598290");
        loginPage.inputPassword("");
        Assert.assertEquals(LoginPage.checkDisabledLoginButton(), false, "The Login Button is enabled provided the Password field is empty");
    }
}
