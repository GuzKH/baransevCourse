package tests;

import appmanager.HttpSession;
import appmanager.PasswordHelper;
import model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangingPasswordTest extends TestBase {

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        //Given
     //   long now = System.currentTimeMillis();
        String user = "user";
        String password = "password";
        // String email = "user%d@localhost.localdomain";
      //  app.james().createUser(user, password);

        //When
        HttpSession session = app.newSession();
        session.login(user, password);
        app.passwordHelper().changePassword();

        //Then
        // Assert.assertTrue(app.newSession().login(user,password));
    }

}
