package tests;

import appmanager.HttpSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));
    }
}
