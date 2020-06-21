package tests;

import appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession(); // создание новой сессии
        Assert.assertTrue(session.login("administrator", "root")); //проверка, что пользователь действительно залогинился
        Assert.assertTrue(session.isLoggedInAs("administrator"));
    }
}
