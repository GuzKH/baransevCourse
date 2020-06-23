package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class ChangingPasswordTest extends TestBase {


    @Test
    public void testChangePassword() throws IOException, MessagingException {
        //Given
        long now = System.currentTimeMillis();
        String user = String.format("user%d", now);
        String oldPassword = "password";
        String email = String.format("user%d@localhost", now);

        app.passwordHelper().createUser(user, oldPassword, email);

        String newPassword = "passwordNew";


        //When
        app.passwordHelper().goToLoginPage();
        app.passwordHelper().loginAsAdmin();
        app.passwordHelper().chooseUser();
        app.passwordHelper().resetPassword();

        String confirmationLink = app.passwordHelper().getConfirmationLink(user, oldPassword, email);
        app.registration().finish(confirmationLink, newPassword);

        //Then
        // Assert.assertTrue(app.newSession().login(user,password));
        Assert.assertTrue(app.newSession().login(user, newPassword));

//        app.james().deleteUser();
    }


}
